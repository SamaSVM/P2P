package com.shpp.p2p.cs.vsamchenko.exam.assignment14;


import java.nio.file.Files;
import java.nio.file.Path;

import static com.shpp.p2p.cs.vsamchenko.exam.assignment14.DataProcessor.*;


/**
 * A class that determines what the user wants from the program.
 */
public class Checker {

    /**
     * The default name of the input file.
     */
    private static final String DEFAULT_NAME_OF_INPUT_FILE = "test.txt";

    /**
     * The default name of the source file.
     */
    private static final String DEFAULT_NAME_OF_OUTPUT_FILE = "test.txt.par";

    /**
     * Files location folder.
     */
    private static final String FOLDER_PATH = "assets/";

    /**
     * Private class constructor so that objects of this class cannot be created.
     */
    private Checker() {
    }

    /**
     * A method that analyzes input arguments and determines whether to archive or unzip the file
     * and the names of the input and output files.
     * "-a" - explicit archiving instruction.
     * "-u" - explicit indication of unpacking.
     */
    static void checkArgsAndStartDataProcessor(String[] args) {
        checkFlags(args);

        switch (args.length) {
            case 0:
                compress(DEFAULT_NAME_OF_INPUT_FILE, DEFAULT_NAME_OF_OUTPUT_FILE);
                break;
            case 1: {
                if (!checkFolderAndFile(args[0])) {
                    return;
                }
                if (args[0].endsWith(".par")) {
                    if (args[0].substring(0, args[0].lastIndexOf(".")).contains(".")) {
                        decompress(args[0], args[0].substring(0, args[0].lastIndexOf(".")));
                    } else {
                        decompress(args[0], args[0].substring(0, args[0].lastIndexOf(".")) + ".uar");
                    }
                } else {
                    compress(args[0], args[0] + ".par");
                }
            }
            break;
            case 2: {
                if (!checkFolderAndFile(args[0])) {
                    return;
                }
                if (args[0].substring(args[0].lastIndexOf(".")).equals(".par")) {
                    decompress(args[0], args[1]);
                } else {
                    compress(args[0], args[1]);
                }
            }
            break;
            case 3: {
                if (!checkFolderAndFile(args[1])) {
                    return;
                }
                if (args[0].equals("-a")) {
                    compress(args[1], args[2]);
                } else if (args[0].equals("-u")) {
                    decompress(args[1], args[2]);
                } else {
                    System.out.println("Either you entered too many arguments or you specified an incorrect flag.");
                }
            }
            break;
            default:
                System.out.println("You have entered too many arguments!");
        }
    }

    private static void checkFlags(String[] args) {
        if (args.length > 0 && (args[0].equals("-a") || args[0].equals("-u")) && args.length != 3) {
            System.out.println("If explicitly specified \"" + args[0] +
                    "\" - you need to specify the archive and unzip files.");
        }
    }

    /**
     * A method that checks if a file exists and the path to it.
     */
    static boolean checkFolderAndFile(String nameFile) {
        if (Files.notExists(Path.of(FOLDER_PATH))) {
            System.err.println("There is no such folder : " + FOLDER_PATH);
            return false;
        }
        if (!Files.exists(Path.of(FOLDER_PATH + nameFile))) {
            System.err.println("There is no such file : " + FOLDER_PATH + nameFile);
            return false;
        }
        return true;
    }
}
