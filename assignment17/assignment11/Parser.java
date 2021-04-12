package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment11;

import com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment16.MyArrayList;

import java.util.Arrays;

import static java.lang.Double.parseDouble;

/**
 * A class in which all methods that perform operations with a formula and variables are collected.
 */
class Parser implements Variables {

    /**
     * Private class designer. Objects of this class cannot be created, only refer to its methods.
     */
    private Parser() {
    }

    /**
     * A method that breaks down a formula into elements.
     *
     * @param formula the formula entered by the user.
     * @return list of all elements of the formula.
     */
    static MyArrayList<String> preparationOfTheFormula(String formula) {                                                // ArrayList
        MyArrayList<String> arraySplit = new MyArrayList<>();                                                           // ArrayList
        parsFormula(arraySplit, formula);
        parsingTrigonometricSigns(arraySplit);
        parsingBrackets(arraySplit);
        return arraySplit;
    }

    /**
     * A method that divides all variables into key-values.
     *
     * @param args  arguments passed by the user.
     * @param index the index of the argument with which the program works.
     */
    static void variableProcessor(String[] args, int index) {
        // everything before = in key everything after = in the number
        String key = args[index].substring(0, args[index].indexOf('='));
        String number = args[index].substring(args[index].indexOf('=') + 1);

        // to whom we replace with a point
        number = number.replace(',', '.');

        // check that in key did not put numbers and in value - letters
        if (!key.matches("[0-9]+") && number.matches("[0-9.-]+")) {
            variables.put(key, parseDouble(number));
        } else {
            System.out.println("Invalid parameter : " + args[index]);
        }
    }

    /**
     * A method that divides a formula into individual elements.
     *
     * @param arraySplit list of all formula elements.
     * @param formula    the formula entered by the user.
     */
    static void parsFormula(MyArrayList<String> arraySplit, String formula) {                                           // ArrayList
        while (theFirstSign(formula) != -1) {
            arraySplit.add(formula.substring(0, theFirstSign(formula)));
            formula = formula.substring(theFirstSign(formula));
            arraySplit.add(formula.substring(0, 1));
            formula = formula.substring(1);
        }
        arraySplit.add(formula);
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
            if (number > 0) {
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

    /**
     * This function breaks the ArrayList into parentheses.
     *
     * @param arraySplit input ArrayList
     */
    private static void parsingBrackets(MyArrayList<String> arraySplit) {                                               // ArrayList
        for (int i = 0; i < arraySplit.size(); i++) {
            if (arraySplit.get(i).contains("(") && arraySplit.get(i).length() != 1) {
                arraySplit.add(i, "(");
                arraySplit.add(i + 1, arraySplit.get(i + 1).substring(1));
                arraySplit.remove(i + 2);
            }
        }
        for (int i = arraySplit.size() - 1; i > 0; i--) {
            if (arraySplit.get(i).contains(")") && arraySplit.get(i).length() != 1) {
                arraySplit.add(i + 1, arraySplit.get(i).substring(arraySplit.get(i).lastIndexOf(")")));
                arraySplit.add(i + 1, arraySplit.get(i).substring(0, arraySplit.get(i).lastIndexOf(")")));
                arraySplit.remove(i);
                i = i + 1;
            }
        }
    }

    /**
     * Determines which trigonometric operation is performed, and calls the separationOfSignsOfTrigonometry function.
     */
    private static void parsingTrigonometricSigns(MyArrayList<String> arraySplit) {                                     // ArrayList

        for (int i = 0; i < arraySplit.size(); i++) {

            int sinPosition = arraySplit.get(i).indexOf("sin");
            int cosPosition = arraySplit.get(i).indexOf("cos");
            int atanPosition = arraySplit.get(i).indexOf("atan");
            int tanPosition = arraySplit.get(i).indexOf("tan");
            int log10Position = arraySplit.get(i).indexOf("log10");
            int log2Position = arraySplit.get(i).indexOf("log2");
            int sqrtPosition = arraySplit.get(i).indexOf("sqrt");

            int maxNumberOfChars = 5;
            if (arraySplit.get(i).length() <= 5) {
                maxNumberOfChars = arraySplit.get(i).length() - 1;
            }
            for (int j = 0; j < arraySplit.get(i).length() - maxNumberOfChars; j++) {

                String formula = arraySplit.get(i).substring(j, j + maxNumberOfChars);

                // sin
                if (formula.contains("sin")) {
                    i = separationOfSignsOfTrigonometry(sinPosition, i, "sin", arraySplit);
                    break;
                }
                //cos
                if (formula.contains("cos")) {
                    i = separationOfSignsOfTrigonometry(cosPosition, i, "cos", arraySplit);
                    break;
                }
                // atan
                if (formula.contains("atan")) {
                    i = separationOfSignsOfTrigonometry(atanPosition, i, "atan", arraySplit);
                    break;
                }
                // tan
                else if (formula.contains("tan")) {
                    i = separationOfSignsOfTrigonometry(tanPosition, i, "tan", arraySplit);
                    break;
                }
                //log10
                if (formula.contains("log10")) {
                    i = separationOfSignsOfTrigonometry(log10Position, i, "log10", arraySplit);
                    break;
                }
                //log2
                else if (formula.contains("log2")) {
                    i = separationOfSignsOfTrigonometry(log2Position, i, "log2", arraySplit);
                    break;
                }
                //sqrt
                if (formula.contains("sqrt")) {
                    i = separationOfSignsOfTrigonometry(sqrtPosition, i, "sqrt", arraySplit);
                    break;
                }
            }
        }
    }

    /**
     * Performs element separation.
     *
     * @param charPosition       transaction index
     * @param step               step
     * @param signOfTrigonometry what trigonometric operation is performed
     * @return step from which to read the item.
     */
    private static int separationOfSignsOfTrigonometry(int charPosition, int step, String signOfTrigonometry,
                                                       MyArrayList<String> arraySplit) {                                // ArrayList
        arraySplit.add(step + 1, arraySplit.get(step).substring(charPosition + signOfTrigonometry.length()));
        arraySplit.add(step + 1, signOfTrigonometry);
        if (charPosition != 0) {
            arraySplit.add(step + 1, arraySplit.get(step).substring(0, charPosition));
        }
        arraySplit.remove(step);
        if (charPosition != 0) {
            return step + 1;
        }
        return step;
    }
}
