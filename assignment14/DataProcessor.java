package com.shpp.p2p.cs.vsamchenko.exam.assignment14;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The class that works with the file. Archives or unzips it.
 */
public class DataProcessor {

    /**
     * Private class constructor so that objects of this class cannot be created.
     */
    private DataProcessor() {
    }

    /**
     * The time when the program began its work.
     */
    private static final long START_TIME = System.currentTimeMillis();

    /**
     * The path to the folder where the file is located.
     */
    private static final String FOLDER_PATH = "assets/";

    /**
     * Byte index when the table begins.
     * 4 bytes - the size of the table in bytes
     * 8 bytes - the size of uncompressed data in bytes
     */
    private static final int BEGINNING_TABLE = 12;

    /**
     * The cell from which the size of the data in the byte array begins.
     */
    private static int startDataSize = 4;

    /**
     * The cell from which the size of the table in the byte array begins.
     */
    private static int startTableSize = 0;

    /**
     * StringBuilder which stores decrypted data.
     */
    private static final StringBuilder BYTES_OUTPUT_DATA = new StringBuilder();

    /**
     * A method that archives a file. Takes the name of the file to be processed and writes as:
     * 4 bytes - the size of the table in bytes
     * 8 bytes - the size of uncompressed data in bytes
     * X bytes - table
     * Y byte - the data itself
     *
     * @param nameInputFile  the name of the file to be processed.
     * @param nameOutputFile the name of the file you want to save.
     */
    public static void compress(String nameInputFile, String nameOutputFile) {

        byte[] byteInputFile = readAllBytes(nameInputFile);

        ArrayList<Byte> byteOutputFile = new ArrayList<>();

        HashMap<Byte, Integer> freqMap = characterCounting(byteInputFile);

        int tableSize = freqMap.size();

        addTableSize(tableSize, byteOutputFile);

        long uncompressedData = byteInputFile.length;

        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(uncompressedData);
        byte[] bytes = buffer.array();

        for (byte b : bytes) {
            byteOutputFile.add(b);
        }

        Byte[] table = doTable(freqMap);

        Collections.addAll(byteOutputFile, table);

        formationOfEncryptedData(byteInputFile, tableSize, table);

        recordEncryptedData(byteOutputFile);

        saveResult(byteOutputFile, nameOutputFile);

        Informer.finalMessage(byteInputFile.length, byteOutputFile.size(), START_TIME, nameInputFile, nameOutputFile);
    }

    /**
     * A method that unzips a file. Accepts file as:
     * 4 bytes - the size of the table in bytes
     * 8 bytes - the size of uncompressed data in bytes
     * X bytes - table
     * Y bytes - the data itself
     *
     * @param nameInputFile  the name of the file to be processed.
     * @param nameOutputFile the name of the file you want to save.
     */
    static void decompress(String nameInputFile, String nameOutputFile) {
        byte[] byteInputFile = readAllBytes(nameInputFile);

        int tableSize = readTableSize(byteInputFile);

        long dataSize = readDataSize(byteInputFile);

        byte[] byteOutputFileArchived = readByteOutputFileArchive(byteInputFile, tableSize);

        byte[] byteOutputFile = new byte[(int) dataSize];

        generatesDecryptedData(byteOutputFile, byteOutputFileArchived, tableSize, dataSize, byteInputFile);

        writeOutputFile(byteOutputFile, nameOutputFile);

        Informer.finalMessage(byteInputFile.length, byteOutputFile.length, START_TIME, nameInputFile, nameOutputFile);
    }

    /**
     * The method that writes the source file.
     *
     * @param byteOutputFile an array of output bits to write.
     * @param outputFile the name of the source file.
     */
    private static void writeOutputFile(byte[] byteOutputFile, String outputFile) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FOLDER_PATH + outputFile);
            fileOutputStream.write(byteOutputFile);
            fileOutputStream.close();
        } catch (IOException e) {
            System.err.println("Failed to save file!");
        }
    }

    /**
     * A method that generates encrypted data.
     *
     * @param byteOutputFile an array of bytes of the source file.
     * @param byteOutputFileArchived an array of bytes of the source file that are archived.
     * @param tableSize table size.
     * @param dataSize the size of uncompressed data.
     * @param byteInputFile an array of bytes of the input file.
     */
    private static void generatesDecryptedData(byte[] byteOutputFile, byte[] byteOutputFileArchived,
                                               int tableSize, long dataSize, byte[] byteInputFile) {
        StringBuilder res = new StringBuilder();
        int numberOfBitsPerByte = Byte.SIZE;
        int bitInTheChar = numberOfBits(tableSize);
        int lastCharacter = (int) ((dataSize * bitInTheChar) % Byte.SIZE);
        for (int i = 0; i < byteOutputFileArchived.length; i++) {
            String archivedData = Integer.toBinaryString(byteOutputFileArchived[i] & 0xFF);
            if (i == byteOutputFileArchived.length - 1 && lastCharacter != 0) {
                numberOfBitsPerByte = lastCharacter;
            }
            while (archivedData.length() < numberOfBitsPerByte) {
                archivedData = "0" + archivedData;
            }
            if (archivedData.length() > numberOfBitsPerByte) {
                archivedData = archivedData.substring((archivedData.length() - numberOfBitsPerByte - 1),
                        archivedData.length() - 1);
            }

            res.append(archivedData);
        }
        byte[] table = readTable(byteInputFile, tableSize);

        bitInTheChar = numberOfBits(tableSize);
        for (int i = 0; i < byteOutputFile.length; i++) {
            int result = 0;
            String fres = res.substring((bitInTheChar * i), bitInTheChar + (bitInTheChar * i));
            fres = new StringBuffer(fres).reverse().toString();
            for (int j = 0; j < fres.length(); j++) {
                result += Math.pow(2, j) * (fres.charAt(j) == '1' ? 1 : 0);
            }
            byteOutputFile[i] = table[result];
        }
    }

    /**
     * A method that reads an archive of source byte files.
     *
     * @param byteInputFile an array of bytes of the input file.
     * @param tableSize data table size.
     * @return an array of bytes of the archived file.
     */
    private static byte[] readByteOutputFileArchive(byte[] byteInputFile, int tableSize) {
        byte[] byteOutputFileArchived = new byte[byteInputFile.length - BEGINNING_TABLE - tableSize];
        for (int i = 0; i < byteOutputFileArchived.length; i++) {
            byteOutputFileArchived[i] = byteInputFile[byteInputFile.length - byteOutputFileArchived.length + i];
        }
        return byteOutputFileArchived;
    }

    /**
     * A method that reads the size of uncompressed data initially in a file.
     *
     * @param byteInputFile an array of bytes of the input file.
     * @return the size of the uncompressed data first in the file.
     */
    private static long readDataSize(byte[] byteInputFile) {
        return ((0xFF & byteInputFile[startDataSize++]) << 56) | ((0xFF & byteInputFile[startDataSize++]) << 48)
                | ((0xFF & byteInputFile[startDataSize++]) << 40) | ((0xFF & byteInputFile[startDataSize++]) << 32)
                | ((0xFF & byteInputFile[startDataSize++]) << 24) | ((0xFF & byteInputFile[startDataSize++]) << 16)
                | ((0xFF & byteInputFile[startDataSize++]) << 8) | (0xFF & byteInputFile[startDataSize++]);
    }

    /**
     * A method that reads a table (byte - reduction in bits) from the input file.
     *
     * @param byteInputFile an array of bytes of the input file.
     * @param tableSize data table size.
     * @return an array of byte values that are encoded under a specific number.
     */
    private static byte[] readTable(byte[] byteInputFile, int tableSize) {
        byte[] table = new byte[tableSize];

        for (int i = 0; i < tableSize; i++) {
            table[i] = byteInputFile[i + BEGINNING_TABLE];
        }
        return table;
    }

    /**
     * A method that reads the size of a table from an input file.
     *
     * @param byteInputFile an array of bytes of the input file.
     * @return the size of the table that read the method.
     */
    private static int readTableSize(byte[] byteInputFile) {
        return ((0xFF & byteInputFile[startTableSize++]) << 24) | ((0xFF & byteInputFile[startTableSize++]) << 16)
                | ((0xFF & byteInputFile[startTableSize++]) << 8) | (0xFF & byteInputFile[startTableSize++]);
    }

    /**
     * A method that stores a file that came out after data compression.
     *
     * @param byteOutputFile an array of bytes of the source file.
     * @param nameOutputFile the name of the file you want to save.
     */
    private static void saveResult(ArrayList<Byte> byteOutputFile, String nameOutputFile) {
        byte[] result = new byte[byteOutputFile.size()];
        for (int i = 0; i < byteOutputFile.size(); i++) {
            result[i] = byteOutputFile.get(i);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FOLDER_PATH + nameOutputFile);
            fileOutputStream.write(result);
            fileOutputStream.close();
        } catch (IOException e) {
            System.err.println("Failed to save file!");
        }
    }

    /**
     * A method that writes encrypted data to ArrayList.
     *
     * @param byteOutputFile an ArrayList of bytes of the source file.
     */
    private static void recordEncryptedData(ArrayList<Byte> byteOutputFile) {
        double numberOfCycles = Math.ceil(BYTES_OUTPUT_DATA.length() / Byte.SIZE);
        String res;
        for (int i = 0; i < numberOfCycles; i++) {
            int result = 0;
            if (i == numberOfCycles - 1) {
                res = BYTES_OUTPUT_DATA.substring((Byte.SIZE * i));
            } else {
                res = BYTES_OUTPUT_DATA.substring(((Byte.SIZE * i)),
                        (Byte.SIZE) + Byte.SIZE * i);
            }
            res = new StringBuffer(res).reverse().toString();
            for (int j = 0; j < res.length(); j++) {
                result += Math.pow(2, j) * (res.charAt(j) == '1' ? 1 : 0);
            }
            byteOutputFile.add((byte) result);
        }
    }

    /**
     * A method that generates bit sequences for archiving a file.
     * @param byteInputFile an array of bytes of the input file.
     * @param tableSize data table size.
     * @param table table (bit - ordinal number).
     */
    private static void formationOfEncryptedData(byte[] byteInputFile, int tableSize, Byte[] table) {
        int bitsInTheSign = numberOfBits(tableSize);

        for (int i = 0; i < byteInputFile.length; i++) {
            int mask = 0;
            for (int j = 0; j < table.length; j++) {
                if (table[j].equals(byteInputFile[i])) {
                    mask = j;
                    break;
                }
            }
            String result = Integer.toBinaryString(mask & 0xFF);
            while (result.length() < bitsInTheSign) {
                result = "0" + result;
            }
            if (result.length() > bitsInTheSign) {
                result = result.substring(result.length() - 1 - bitsInTheSign, result.length() - 1);
            }

            BYTES_OUTPUT_DATA.append(result);
        }
    }

    /**
     * A method that writes the size of a table to the source file.
     *
     * @param tableSize table size.
     * @param byteOutputFile an ArrayList of bytes of the source file.
     */
    private static void addTableSize(int tableSize, ArrayList<Byte> byteOutputFile) {
        byteOutputFile.add((byte) (tableSize >> 24));
        byteOutputFile.add((byte) (tableSize >> 16));
        byteOutputFile.add((byte) (tableSize >> 8));
        byteOutputFile.add((byte) (tableSize & 0xFF));
    }

    /**
     * A method that reads the input file and returns the array of bytes that make up the file.
     *
     * @param nameInputFile the name of the input file.
     * @return an array of bytes.
     */
    private static byte[] readAllBytes(String nameInputFile) {
        byte[] byteInputFile = new byte[0];
        File myFile = new File(FOLDER_PATH + nameInputFile);
        try {
            byteInputFile = Files.readAllBytes(myFile.toPath());
        } catch (IOException e) {
            System.err.println("Failed to read input file!");
        }
        return byteInputFile;
    }

    /**
     * A method that determines the number of bits to compress a file.
     *
     * @param tableSize table size.
     * @return the number of bits to compress a file.
     */
    private static int numberOfBits(int tableSize) {
        if (tableSize == 0) {
            return 1;
        }
        return (int) (Math.log(tableSize) / Math.log(2)) + 1;
    }

    /**
     * A method that generates a table of how often bytes are used in a file.
     */
    private static Byte[] doTable(HashMap<Byte, Integer> freqMap) {
        Byte[] result = new Byte[freqMap.size()];
        int index = 0;
        for (Byte b : freqMap.keySet()) {
            result[index++] = (byte) (b & 0xFF);
        }
        return result;
    }

    /**
     * A function that generates a TreeMap that shows how often a byte is used.
     *
     * @param byteFile an array of bytes of the input file.
     * @return formed by TreeMap.
     */
    private static HashMap<Byte, Integer> characterCounting(byte[] byteFile) {
        HashMap<Byte, Integer> freqMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < byteFile.length; i++) {
            byte key = byteFile[i];
            if (!freqMap.containsKey(key)) {
                freqMap.put(key, count++);
            }
        }
        return freqMap;
    }
}
