package com.shpp.p2p.cs.vsamchenko.exam.assignment14;


/**
 * A program that archives data by comparing the original bits with the created table.
 */
public class Assignment14Part1 {

    /**
     * The method that initializes the start of the program.
     *
     * @param args arguments passed by the user to the program.
     */
    public static void main(String[] args) {
        try {
            Checker.checkArgsAndStartDataProcessor(args);
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
}
