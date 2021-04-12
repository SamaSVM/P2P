package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment15;

import com.shpp.p2p.cs.vsamchenko.exam.assignment17.*;
import com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment16.*;

import java.io.*;
import java.nio.file.Files;

/**
 * The class that works with the file. Archives or unzips it.
 */
class DataProcessing {

    private DataProcessing(){}

    /**
     * Start of the program start countdown.
     */
    private static final long START_PROGRAM_TIME = System.currentTimeMillis();

    /**
     * Files location folder.
     */
    private static final String FOLDER_PATH = "assets/";

    /**
     * A method that compresses data using the Huffman method.
     */
    static void compress(String nameInputFile, String nameOutputFile) {
        byte[] byteInputFile = readFile(FOLDER_PATH + nameInputFile);

        // Check the length of the input file.

        Checker.checkCompressFileSize(byteInputFile.length);

        // Forming a table is how often bytes are repeated in a file.
        MyHashMap<Byte, Integer> freqMap = buildTableBytes(byteInputFile);                                              // HashMap

        // Add all the nodesTree in Queue.
        MyPriorityQueue<TreeNode> nodesTree = buildNodesQueue(freqMap);                                                 // PriorityQueue


        // Check that there are more than two items.
        Checker.checkTheNumberOfItems(freqMap.size());

        TreeNode rootTreeHuffman = buildTreeHuffman(nodesTree);

        // Forming a table which binary code corresponds to which element.
        MyHashMap<Byte, String> tableBitCodes = buildBitCodeTable(freqMap, rootTreeHuffman);                            // HashMap

        // Encrypt the tree to write it to a file.
        TreeNode.getStringFromTree(rootTreeHuffman);
        StringBuilder treeStructure = TreeNode.getTreeStructure();
        MyArrayList<Byte> codedElements = TreeNode.getCodedElements();                                                  // ArrayList

        // Encoding a file relative to a tree.
        StringBuilder outputString = new StringBuilder();
        for (byte b : byteInputFile) {
            outputString.append(tableBitCodes.get(b));
        }
        // Save the file and get its size.
        int sizeOutputFile = saveOutputFileForCompress
                (FOLDER_PATH + nameOutputFile, outputString, treeStructure, codedElements);

        Informer.finalMessage(byteInputFile.length, sizeOutputFile, START_PROGRAM_TIME,  nameInputFile, nameOutputFile);
    }

    /**
     * A method that builds a row of tree leaves, from the smallest to the largest.
     */
    private static MyPriorityQueue<TreeNode> buildNodesQueue(MyHashMap<Byte, Integer> freqMap) {                        // PriorityQueue HashMap
        MyPriorityQueue<TreeNode> nodesTree = new MyPriorityQueue<>();                                                  // PriorityQueue
        for (Byte b : freqMap.keySet()) {
            nodesTree.add(new TreeNode(b, -freqMap.get(b)));
        }
        return nodesTree;
    }

    /**
     * A method that forming a table which binary code corresponds to which element.
     */
    private static MyHashMap<Byte, String> buildBitCodeTable
    (MyHashMap<Byte, Integer> freqMap, TreeNode rootTreeHuffman) {                                                      // HashMap
        MyHashMap<Byte, String> bitCodeTable = new MyHashMap<>();                                                       // HashMap
        for (Byte b : freqMap.keySet()) {
            bitCodeTable.put(b, rootTreeHuffman.getCodeForByte(b, ""));
        }
        return bitCodeTable;
    }


    /**
     * A method that decompresses data using the Huffman method.
     */
    static void decompress(String nameInputFile, String nameOutputFile) {
        // Reading and processing the file.
        File inputFile = new File(FOLDER_PATH + nameInputFile);

        byte[] compressedData = new byte[0];
        int encodedStringSize = 0;

        MyArrayList<Byte> codedElements = new MyArrayList<>();                                                          // ArrayList
        StringBuilder treeStructure = new StringBuilder();
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream
                    (new FileInputStream(inputFile)));
            int treeSize = dataInputStream.readInt();
            encodedStringSize = dataInputStream.readInt();
            // Obtaining the structure of the tree/
            byte[] treeStructureByteArray = new byte[(int) Math.ceil(treeSize / (double) Byte.SIZE)];
            for (int i = 0; i < treeStructureByteArray.length; i++) {
                treeStructureByteArray[i] = dataInputStream.readByte();
            }
            treeStructure = castByteToString(treeStructureByteArray, treeSize);
            // Obtaining coded elements.
            for (int i = 0; i < (int) Math.ceil(treeSize / 2.0); i++) {
                codedElements.add(dataInputStream.readByte());
            }

            compressedData = dataInputStream.readAllBytes();
        } catch (IOException e) {
            System.err.println("Unable to read file : " + nameInputFile);
        }

        TreeNode treeHuffman = new TreeNode().getTreeFromString(treeStructure, codedElements);

        StringBuilder encodedFile = castByteToString(compressedData, encodedStringSize);

        Byte[] bytesOutputFile = decoding(treeHuffman, encodedFile);

        saveOutputFileForDecompress(bytesOutputFile,FOLDER_PATH + nameOutputFile);

        Informer.finalMessage(compressedData.length, bytesOutputFile.length,
                START_PROGRAM_TIME, nameInputFile, nameOutputFile);
    }

    /**
     * A method that reads a file and converts it to a byte array.
     *
     * @return formed a byte array.
     */
    private static byte[] readFile(String nameInputFile) {
        File myFile = new File(nameInputFile);

        byte[] byteInputFile = new byte[0];

        try {
            byteInputFile = Files.readAllBytes(myFile.toPath());
        } catch (IOException e) {
            System.err.println("The file cannot be read!");
        }
        return byteInputFile;
    }

    /**
     * A method that stores an decompressed file.
     */
    private static void saveOutputFileForDecompress(Byte[] bytesOutputFile, String nameOutputFile) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(nameOutputFile));
            for (byte b : bytesOutputFile) {
                dataOutputStream.writeByte(b);
            }
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (IOException e) {
            System.err.println("Failed to save file!");
        }
    }

    /**
     * A method that converts a byte array into a string in binary code.
     *
     * @return string in binary code obtained after conversion.
     */
    private static StringBuilder castByteToString(byte[] zipBytes, int encodedStringSize) {
        StringBuilder result = new StringBuilder();
        int numberOfBitsPerByte = Byte.SIZE;
        int lastCharacter = encodedStringSize % Byte.SIZE;
        for (int i = 0; i < zipBytes.length; i++) {
            StringBuilder archivedData = new StringBuilder(Integer.toBinaryString(zipBytes[i] & 0xFF));
            if (i == zipBytes.length - 1 && lastCharacter != 0) {
                numberOfBitsPerByte = lastCharacter;
            }
            while (archivedData.length() < numberOfBitsPerByte) {
                archivedData.insert(0, "0");
            }
            if (archivedData.length() > numberOfBitsPerByte) {
                archivedData = new StringBuilder(archivedData.substring((archivedData.length() -
                        numberOfBitsPerByte - 1), archivedData.length() - 1));
            }
            result.append(archivedData);
        }
        return result;
    }

    /**
     * A method that decodes a file by passing through a Huffman tree.
     *
     * @return an array of bytes obtained after decoding.
     */
    private static Byte[] decoding(TreeNode treeHuffman, StringBuilder encodedFile) {
        MyArrayList<Byte> result = new MyArrayList<>();                                                                 // ArrayList
        TreeNode node = treeHuffman;
        for (int i = 0; i < encodedFile.length(); i++) {
            node = encodedFile.charAt(i) == '0' ? node.leftBranch : node.rightBranch;
            if (node.value != null) {
                result.add(node.value);
                node = treeHuffman;
            }
        }

        Byte[] myResult = new Byte[result.size()];
        for (int i = 0; i < result.size(); i++) {
            myResult[i] = result.get(i);
        }

        return myResult;
    }

    /**
     * A method that saves a file after compression, and returns the file size.
     * 4 byte - tree size.
     * 4 byte - the number of characters used for encoding.
     * X byte - the structure of the tree.
     * Y byte - coded elements.
     * Z byte - compressed data.
     *
     * @return file size.
     */
    private static int saveOutputFileForCompress(String nameOutputFile, StringBuilder outputString,
                                                 StringBuilder treeStructure, MyArrayList<Byte> codedElements) {        // ArrayList
        int sizeOutputFile = 0;
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(nameOutputFile));
            // tree size.
            dataOutputStream.writeInt(treeStructure.length());
            sizeOutputFile += Integer.BYTES;
            // the number of characters used for encoding.
            dataOutputStream.writeInt(outputString.length());
            sizeOutputFile += Integer.BYTES;
            // the structure of the tree.
            byte[] treeStructureArray = castStringToByte(treeStructure);
            for (byte b : treeStructureArray) {
                dataOutputStream.writeByte(b);
                sizeOutputFile += Byte.BYTES;
            }
            // coded elements
            for (int i = 0; i < codedElements.size(); i++) {
                dataOutputStream.writeByte(codedElements.get(i));
                sizeOutputFile += Byte.BYTES;
            }
            // compressed data.
            byte[] outputByte = castStringToByte(outputString);
            for (byte b : outputByte) {
                dataOutputStream.writeByte(b);
                sizeOutputFile += Byte.BYTES;
            }
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (IOException e) {
            System.err.println("Failed to save file!");
        }
        return sizeOutputFile;
    }

    /**
     * A method that converts a string of compressed data into a byte array.
     *
     * @return byte array of compressed data.
     */
    private static byte[] castStringToByte(StringBuilder outputString) {
        double numberOfCycles = Math.ceil(outputString.length() / (double) Byte.SIZE);
        byte[] arrayResults = new byte[(int) numberOfCycles];
        for (int i = 0; i < numberOfCycles; i++) {
            StringBuilder result = new StringBuilder();
            if (i == numberOfCycles - 1) {
                result.append(outputString.substring(Byte.SIZE * i));
            } else {
                result.append(outputString.substring(Byte.SIZE * i, Byte.SIZE + (Byte.SIZE * i)));
            }
            result.reverse();
            int outputByte = 0;
            for (int j = 0; j < result.length(); j++) {
                outputByte += Math.pow(2, j) * (result.charAt(j) == '1' ? 1 : 0);
                arrayResults[i] = (byte) outputByte;
            }
        }
        return arrayResults;
    }

    /**
     * A function that generates a TreeMap that shows how often a byte is used.
     *
     * @return formed by TreeMap.
     */
    private static MyHashMap<Byte, Integer> buildTableBytes(byte[] bytesFile) {                                         // HashMap
        MyHashMap<Byte, Integer> freqMap = new MyHashMap<>();                                                           // HashMap
        for (Byte key : bytesFile) {
            Integer count = freqMap.get(key);
            freqMap.put(key, count != null ? count + 1 : 1);
        }
        return freqMap;
    }

    /**
     *  A method that in turn builds the root of the Huffman tree.
     * @return root Huffman tree.
     */
    private static TreeNode buildTreeHuffman(MyPriorityQueue<TreeNode> nodes) {                                         // PriorityQueue
        while (nodes.size() > 1) {
            TreeNode leftBranch = nodes.poll();
            TreeNode rightBranch = nodes.poll();
            nodes.add(new TreeNode(null, rightBranch.weight + leftBranch.weight, leftBranch, rightBranch));
        }
        return nodes.poll();
    }
}