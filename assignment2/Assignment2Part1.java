package com.shpp.p2p.cs.vsamchenko.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 * The program takes three numbers as input and returns the root of the quadratic equation.
 */
public class Assignment2Part1 extends TextProgram {

    /**
     * Assigning global variables
     */
    private static double  a, b, b2, c, D, x, x1, x2;

    /**
     * A method that runs the entire program.
     */
    public void run() {
        EnterNumbers();
        SearchDiscriminant();
        determiningTheNumberOfRoots();
    }

    /**
     * We have discriminants.
     * We compare the discriminant with zero and accordingly we have the number of roots.
     * Calculate the roots by the formula.
     */
    private void determiningTheNumberOfRoots() {
        if (D > 0){
            x1 = (-b + Math.sqrt(D)) / (2 * a);
            x2 = (-b - Math.sqrt(D)) / (2 * a);
            println("The equation has two roots x1 = " + x1 + "; " + "x2 = " + x2);
        }
        if (D < 0){
            println("There are no real roots");
        }
        if (D == 0){
            x = -b / (2 * a);
            println("The equation has one root x = " + x);
        }
    }

    /**
     * Enter numbers in the console.
     * We have 3 numbers entered by the user.
     */
    private void EnterNumbers() {
        a = +readInt("Please enter number \"a\": ");
        b = +readInt("Please enter number \"b\": ");
        c = +readInt("Please enter number \"c\": ");
    }

    /**
     * We have 3 numbers from the user.
     * Find the discriminant by the formula.
     */
    private void SearchDiscriminant() {
        b2 = b * b;
        D = b2 - (4 * a * c);
    }
}