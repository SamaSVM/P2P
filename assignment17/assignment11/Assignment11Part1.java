package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment11;

/**
 * The program which inputs a mathematical expression and the parameters that it processes and
 * displays the value that came out as a result of solving the expression.
 */
class Assignment11Part1 implements Variables {

    /**
     * The main class that serves as the entry point into the program.
     * Initializes the parameter check and displays the result obtained during the calculation.
     *
     * @param args arguments passed by the user.
     *             the first argument should be the formula we are counting,
     *             all others can be variable.
     */
    public static void main(String[] args) {
        if (Checker.checkSizeArgs(args)) {
            Checker.checkParameters(args);
            removeAllSpaces(args);
            System.out.println(Calculator.calculate(args[0]));
        }
    }

    /**
     * A method that removes all spaces from arguments.
     *
     * @param args arguments passed by the user.
     */
    private static void removeAllSpaces(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].replaceAll("\\s", "");
        }
    }
}
