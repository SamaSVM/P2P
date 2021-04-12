package com.shpp.p2p.cs.vsamchenko.exam.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This program clearly solves the problem from the book by Douglas Hofstadter.
 */
public class Assignment3Part2 extends TextProgram {

    /**
     * The number entered by the user and with which we perform all operations.
     */
    private double number;

    /**
     * A method that is the entry point into the program.
     */
    public void run() {
        dataIn();
        calculations();
    }

    /**
     * Carrying out calculations and displaying the result step by step.
     */
    private void calculations() {
        while (number != 1) {
            if (number % 2 == 0) {
                println(number + " is even so I take half: " + (number /= 2));
            }
            if ((number % 2 == 1) && (number != 1)) {
                println(number + "is odd so I make 3n + 1: " + (number = number * 3 + 1));
            }
        }
        println("end.");
    }

    /**
     * The user enters a positive integer.
     */
    private void dataIn() {
         number = readDouble("Enter a number: ");
        while ((number <= 0) || (number % 1 != 0)) {
            number = readDouble("Enter a positive integer! : ");
        }
    }
}