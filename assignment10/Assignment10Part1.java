package com.shpp.p2p.cs.vsamchenko.exam.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Double.parseDouble;

/**
 * The program which inputs a mathematical expression and the parameters that it processes and
 * displays the value that came out as a result of solving the expression.
 */
public class Assignment10Part1 {
    /**
     * HashMap which contains the key - the name of the variable, the value - the value of the variable.
     */
    static HashMap<String, Double> variables = new HashMap<>();

    /**
     * The main class that serves as the entry point into the program.
     * Initializes the parameter check and displays the result obtained during the calculation.
     *
     * @param args Arguments passed by the user.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You have not entered any parameters.");
        } else {
            checkParameters(args);
            System.out.println(calculate(args[0].replaceAll("\\s", ""), variables));
        }
    }

    /**
     * A method that verifies the correctness of user input.
     *
     * @param args Arguments passed by the user.
     */
    private static void checkParameters(String[] args) {
        for (int i = 1; i < args.length; i++) {
            // check for two characters ==
            int count = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (args[i].charAt(j) == '=') {
                    count++;
                }
            }

            // check that the argument does not begin and end with the = sign
            if (args[i].indexOf('=') == -1 ||
                    args[i].indexOf('=') == args[i].length() - 1 ||
                    args[i].indexOf('=') == 0 ||
                    count > 1) {
                System.out.println("Invalid parameter : " + args[i]);
            } else {
                // remove all spaces
                args[i] = args[i].replaceAll("\\s", "");
                // everything before = in key everything after = in the number
                String key = args[i].substring(0, args[i].indexOf('='));
                String number = args[i].substring(args[i].indexOf('=') + 1);
                // to whom we replace with a point
                number = number.replace(',', '.');
                // check that in key did not put numbers and in value - letters
                if (!key.matches("[0-9]+") && number.matches("[0-9.-]+")) {
                    variables.put(key, parseDouble(number));
                } else {
                    System.out.println("Invalid parameter : " + args[i]);
                }
            }
        }
    }

    /**
     * The function that enters the calculation formula and the variables
     * that are set by the user and returns the calculated value.
     *
     * @param formula   the formula to be calculated
     * @param variables variables that are set by the user
     * @return the value obtained
     */
    static double calculate(String formula, HashMap<String, Double> variables) {
        //ArrayList which contains each formula element separately
        ArrayList<String> arraySplit = new ArrayList<>();

        // check that the formula does not begin and end with a sign
        if (theFirstSign(formula) > 0 && theFirstSign(formula.substring(formula.length() - 1)) == -1) {
            while (theFirstSign(formula) != -1) {
                arraySplit.add(formula.substring(0, theFirstSign(formula)));
                formula = formula.substring(theFirstSign(formula));
                arraySplit.add(formula.substring(0, 1));
                formula = formula.substring(1);
            }
            arraySplit.add(formula);
        } else {
            System.out.println("Invalid formula entered : " + formula);
        }

        //replace the names of variables with their values
        for (int i = 0; i < arraySplit.size(); i++) {
            if (arraySplit.get(i).matches("[a-zA-Z][0-9]*+")) {
                if (variables.get(arraySplit.get(i)) == null) {
                    System.out.println("There is no such variable : " + arraySplit.get(i));
                    System.exit(1);
                }
                arraySplit.set(i, Double.toString(variables.get(arraySplit.get(i))));
            }
        }

        return conductingMathematicalOperations(arraySplit);
    }

    /**
     * A function that takes an ArrayList with a formula broken down into elements,
     * calculates it, and returns the resulting value.
     *
     * @param arraySplit the formula is broken down into elements
     * @return the value we get after the calculations
     */
    private static double conductingMathematicalOperations(ArrayList<String> arraySplit) {

        //elevate to the power
        for (int i = arraySplit.size() - 1; i > 0; i--) {
            if (arraySplit.get(i).equals("^")) {
                double res = Math.pow(parseDouble(arraySplit.get(i - 1)), parseDouble(arraySplit.get(i + 1)));
                arraySplit.add(i - 1, Double.toString(res));
                arraySplit.remove(i);
                arraySplit.remove(i);
                arraySplit.remove(i);
            }
        }

        // multiply and divide
        for (int i = 0; i < arraySplit.size() - 1; i++) {
            if (arraySplit.get(i).equals("*") || arraySplit.get(i).equals("/")) {
                if (arraySplit.get(i).equals("*")) {
                    double res = parseDouble(arraySplit.get(i - 1)) * parseDouble(arraySplit.get(i + 1));
                    arraySplit.add(i - 1, Double.toString(res));
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    i = i - 1;
                } else if (arraySplit.get(i).equals("/")) {
                    double res = parseDouble(arraySplit.get(i - 1)) / parseDouble(arraySplit.get(i + 1));
                    arraySplit.add(i - 1, Double.toString(res));
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    i = i - 1;
                }
            }
        }

        // add and subtract
        for (int i = 0; i < arraySplit.size() - 1; i++) {
            if (arraySplit.get(i).equals("+") || arraySplit.get(i).equals("-")) {
                if (arraySplit.get(i).equals("+")) {
                    double res = parseDouble(arraySplit.get(i - 1)) + parseDouble(arraySplit.get(i + 1));
                    arraySplit.add(i - 1, Double.toString(res));
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    i = i - 1;
                } else if (arraySplit.get(i).equals("-")) {
                    double res = parseDouble(arraySplit.get(i - 1)) - parseDouble(arraySplit.get(i + 1));
                    arraySplit.add(i - 1, Double.toString(res));
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    i = i - 1;
                }
            }
        }
        return parseDouble(arraySplit.get(0));
    }

    /**
     * A function that receives a formula at the input and returns the index of the very first character.
     *
     * @param formula the formula to be calculated
     * @return index of the first character
     */
    private static int theFirstSign(String formula) {
        int[] arrayIndexesOfCharacters = definitionOfIndices(formula);

        int min = -1;
        for (int number : arrayIndexesOfCharacters) {
            if (number >= 0) {
                min = number;
                break;
            }
        }
        return min;
    }

    /**
     * The function that inputs the formula to be calculated, and returns an ordered array of character indices.
     *
     * @param formula the formula to be calculated
     * @return an ordered array of character indices
     */
    private static int[] definitionOfIndices(String formula) {
        int[] arrayIndexesOfCharacters = {formula.indexOf('^'), formula.indexOf('+'), formula.indexOf("-"),
                formula.indexOf('*'), formula.indexOf('/')};
        Arrays.sort(arrayIndexesOfCharacters);

        return arrayIndexesOfCharacters;
    }
}
