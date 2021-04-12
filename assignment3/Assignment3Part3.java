package com.shpp.p2p.cs.vsamchenko.exam.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This program raises the number to the power.
 */
public class Assignment3Part3 extends TextProgram {

    /**
     * The number that is raised to the power.
     */
    private static final double BASE = 5;

    /**
     * The degree to which the number is raised.
     */
    private static final int EXPONENT = 4;

    /**
     * A method that is the entry point into the program.
     */
    public void run() {
        double number = raiseToPower();
        println(number);
    }

    /**
     * A method that reduces a number to a power.
     */
    private double raiseToPower() {
        double number = BASE;
        if (EXPONENT == 0) {
            return 1;
        }
        if (EXPONENT > 0) {
            for (int i = 1; i < EXPONENT; i++) {
                number = number * BASE;
            }
        } else {
            for (int i = -1; i > EXPONENT; i--) {
                number = number * BASE;
            }
            number = 1 / number;
        }
        return (number);
    }
}