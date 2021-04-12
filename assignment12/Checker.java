package com.shpp.p2p.cs.vsamchenko.exam.assignment12;

/**
 * The class that checks whether the name of the picture
 * they are working with comes to the entrance.
 */
public class Checker {
    /**
     * Default file path.
     */
    private static String defaultFileWay = "assets/test.jpg";

    /**
     * The folder where the file is located.
     */
    private static final String  FILE_PATHS = "assets/";

    /**
     * A private constructor so that objects of this class cannot be created.
     */
    private Checker() {
    }

    /**
     * The function checks whether at least one argument is entered,
     * and passes argument 0 as the name of the picture.
     *
     * @return returns the path to the image.
     */
    static String checkInputParameters(String[] args) {
        if (args.length > 0) {
            defaultFileWay = FILE_PATHS + args[0];
        }
        return defaultFileWay;
    }
}
