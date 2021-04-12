package com.shpp.p2p.cs.vsamchenko.exam.assignment14;

/**
 * A class that works to return information about file sizes, names, and runtime.
 */
class Informer {

    /**
     * Private constructor of the Informer class, so that objects of this class cannot be created.
     */
    private Informer() {}

    /**
     * A method that displays a message with information about the size of the input and output file,
     * the time of the program, and the efficiency of the program.
     */
    static void finalMessage(double sizeInputFile, double sizeOutputFile, long starProgramTime,
                             String nameInputFile, String nameOutputFile) {
        // (*100) -> cast to interest
        String efficiency = String.format("%.2f", ((sizeInputFile / sizeOutputFile) - 1) * 100);
        System.out.println("Efficiency : " + efficiency + " percents");
        long programExecutionTime = (System.currentTimeMillis() - starProgramTime);
        System.out.println("Execution time : " + programExecutionTime + " milliseconds");
        System.out.println("Input file size \"" + nameInputFile + "\" is equal : " + (int) sizeInputFile + " bytes");
        System.out.println("Output file size \"" + nameOutputFile +
                "\" is equal : " + +(int) sizeOutputFile + " bytes");
    }
}

