package com.shpp.p2p.cs.vsamchenko.exam.assignment5;

import com.shpp.cs.a.console.TextProgram;


/**
 * A program that counts the number of vowels in a word, with some exceptions.
 */
public class Assignment5Part2 extends TextProgram {

    /**
     * A method that asks you to enter two numbers and displays the result of the addition.
     */
    public void run() {
        while (true) {
            String firstNumber = readLine("Enter first number: ");
            String secondNumber = readLine("Enter second number: ");
            println(firstNumber + " + " + secondNumber + " = " + addNumericStrings(firstNumber, secondNumber) + "\n");
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param firstNumber The first number.
     * @param secondNumber The second number.
     * @return A String representation of firstNumber + secondNumber
     */
    private String addNumericStrings(String firstNumber, String secondNumber) {
        //These methods make our lines n1 and n2 equal.
        while (firstNumber.length() > secondNumber.length()){
            secondNumber = '0' + secondNumber;
        }
        while (firstNumber.length() < secondNumber.length()){
            firstNumber = '0' + firstNumber;
        }

        String result = "";
        char number;
        int decade = 0;

        //A loop that sorts characters from the latter.
        for (int i = firstNumber.length() - 1; 0 <= i; i--){
            // The formula for adding characters.
            number = (char) (((firstNumber.charAt(i) + decade - '0') + (secondNumber.charAt(i)) - '0'));
            // If the sign is greater than 9, the remainder is written and 1 goes to the next line.
            if (number > 9){
                number -= 10;
                decade = 1;
            }
            else {
                decade = 0;
            }
            // Convert the sign to a number.
            result = (char) (number +'0')  + result;
        }
        // Check that you haven't forgotten 1 in the beginning.
        if (decade == 1){
            result = '1' + result;
        }
        return result;
    }
}