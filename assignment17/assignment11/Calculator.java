package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment11;

import com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment16.MyArrayList;

import static java.lang.Double.parseDouble;

/**
 * The class in which all the mathematical operations and the sequence of actions that the program needs to perform.
 */
public class Calculator implements Variables {

    /**
     * Private class designer. Objects of this class cannot be created, only refer to its methods.
     */
    private Calculator() {
    }

    /**
     * The function that enters the calculation formula and the variables
     * that are set by the user and returns the calculated value.
     *
     * @param formula the formula to be calculated
     * @return the value obtained
     */
    static double calculate(String formula) {
        MyArrayList<String> arraySplit = Parser.preparationOfTheFormula(formula);                                       // ArrayList
        countInsideParentheses(arraySplit);
        replaceOfVariablesWithValues(arraySplit);
        return conductingMathematicalOperations(arraySplit);
    }

    /**
     * A method that determines whether an expression has trigonometric calculations and performs it.
     *
     * @param arraySplit list of all elements of the formula.
     * @param index      the index of the element from the formula.
     */
    private static void conductingTrigonometricOperations(MyArrayList<String> arraySplit, int index) {                  // ArrayList
        if (arraySplit.get(index).contains("sin")) {
            double number;
            if (arraySplit.get(index + 1).equals("-")) {
                number = Math.sin(Double.parseDouble(arraySplit.get(index + 1) + arraySplit.get(index + 2)));
                arraySplit.remove(index + 1);
            } else {
                number = Math.sin(Double.parseDouble(arraySplit.get(index + 1)));
            }
            arraySplit.add(index, Double.toString(number));
            arraySplit.remove(index + 1);
            arraySplit.remove(index + 1);
        }

        if (arraySplit.get(index).contains("cos")) {
            double number;
            if (arraySplit.get(index + 1).equals("-")) {
                number = Math.cos(Double.parseDouble(arraySplit.get(index + 1) + arraySplit.get(index + 2)));
                arraySplit.remove(index + 1);
            } else {
                number = Math.cos(Double.parseDouble(arraySplit.get(index + 1)));
            }
            arraySplit.add(index, Double.toString(number));
            arraySplit.remove(index + 1);
            arraySplit.remove(index + 1);
        }

        if (arraySplit.get(index).contains("atan")) {
            double number;
            if (arraySplit.get(index + 1).equals("-")) {
                number = Math.atan(Double.parseDouble(arraySplit.get(index + 1) + arraySplit.get(index + 2)));
                arraySplit.remove(index + 1);
            } else {
                number = Math.atan(Double.parseDouble(arraySplit.get(index + 1)));
            }
            arraySplit.add(index, Double.toString(number));
            arraySplit.remove(index + 1);
            arraySplit.remove(index + 1);
        } else if (arraySplit.get(index).contains("tan")) {
            double number;
            if (arraySplit.get(index + 1).equals("-")) {
                number = Math.tan(Double.parseDouble(arraySplit.get(index + 1) + arraySplit.get(index + 2)));
                arraySplit.remove(index + 1);
            } else {
                number = Math.tan(Double.parseDouble(arraySplit.get(index + 1)));
            }
            arraySplit.add(index, Double.toString(number));
            arraySplit.remove(index + 1);
            arraySplit.remove(index + 1);
        }

        if (arraySplit.get(index).contains("log2")) {
            double number;
            if (arraySplit.get(index + 1).equals("-")) {
                number = Math.log(Double.parseDouble(arraySplit.get(index + 1) + arraySplit.get(index + 2)))
                        / Math.log(2);
                arraySplit.remove(index + 1);
            } else {
                number = Math.log(Double.parseDouble(arraySplit.get(index + 1))) / Math.log(2);
            }
            arraySplit.add(index, Double.toString(number));
            arraySplit.remove(index + 1);
            arraySplit.remove(index + 1);
        }

        if (arraySplit.get(index).contains("log10")) {
            double number;
            if (arraySplit.get(index + 1).equals("-")) {
                number = Math.log10(Double.parseDouble(arraySplit.get(index + 1) + arraySplit.get(index + 2)));
                arraySplit.remove(index + 1);
            } else {
                number = Math.log10(Double.parseDouble(arraySplit.get(index + 1)));
            }
            arraySplit.add(index, Double.toString(number));
            arraySplit.remove(index + 1);
            arraySplit.remove(index + 1);
        }

        if (arraySplit.get(index).contains("sqrt")) {
            double number;
            if (arraySplit.get(index + 1).equals("-")) {
                number = Math.sqrt(Double.parseDouble(arraySplit.get(index + 1) + arraySplit.get(index + 2)));
                arraySplit.remove(index + 1);
            } else {
                number = Math.sqrt(Double.parseDouble(arraySplit.get(index + 1)));
            }
            arraySplit.add(index, Double.toString(number));
            arraySplit.remove(index + 1);
            arraySplit.remove(index + 1);
        }
    }

    /**
     * A function that determines the parentheses you want to open and make calculations.
     *
     * @param arraySplit list of all elements of the formula.
     */
    private static void countInsideParentheses(MyArrayList<String> arraySplit) {                                        // ArrayList
        while (arraySplit.contains("(")) {
            int leftParenthesis = arraySplit.lastIndexOf("(");
            int rightParenthesis = 0;
            for (int i = leftParenthesis; i < arraySplit.size(); i++) {
                if (arraySplit.get(i).contains(")")) {
                    rightParenthesis = i;
                    break;
                }
            }

            StringBuilder formulaInParentheses = new StringBuilder();

            for (int i = leftParenthesis + 1; i < rightParenthesis; i++) {
                formulaInParentheses.append(arraySplit.get(i));
            }

            double number = calculate(formulaInParentheses.toString());
            arraySplit.add(arraySplit.lastIndexOf("("), Double.toString(number));

            for (int i = rightParenthesis + 1; i > leftParenthesis; i--) {
                arraySplit.remove(i);
            }
        }
    }

    /**
     * A method that replaces all variables with values.
     *
     * @param arraySplit list of all formula elements.
     */
    private static void replaceOfVariablesWithValues(MyArrayList<String> arraySplit) {                                  // ArrayList

        for (int i = 0; i < arraySplit.size(); i++) {
            if (arraySplit.get(i).matches("[a-zA-Z][0-9]*+")) {
                if (variables.get(arraySplit.get(i)) == null) {
                    System.out.println("There is no such variable : " + arraySplit.get(i));
                    System.exit(1);
                }
                arraySplit.set(i, Double.toString(variables.get(arraySplit.get(i))));
            }
        }
    }

    /**
     * A function that takes an ArrayList with a formula broken down into elements,
     * calculates it, and returns the resulting value.
     *
     * @param arraySplit the formula is broken down into elements
     * @return the value we get after the calculations
     */
    private static double conductingMathematicalOperations(MyArrayList<String> arraySplit) {                            // ArrayList

        for (int i = 0; i < arraySplit.size(); i++) {
            conductingTrigonometricOperations(arraySplit, i);
        }

        for (int i = arraySplit.size() - 1; i > 0; i--) {
            if (arraySplit.get(i).equals("^")) {
                elevationToTheDegree(arraySplit, i);
            }
        }

        for (int i = 0; i < arraySplit.size() - 1; i++) {
            if (arraySplit.get(i).equals("*") || arraySplit.get(i).equals("/")) {
                if (arraySplit.get(i).equals("*")) {
                    multiplication(arraySplit, i);
                    i = i - 1;
                } else if (arraySplit.get(i).equals("/")) {
                    division(arraySplit, i);
                    i = i - 1;
                }
            }
        }

        for (int i = 0; i < arraySplit.size() - 1; i++) {
            if (arraySplit.get(i).equals("+") || arraySplit.get(i).equals("-")) {
                if (arraySplit.get(i).equals("+")) {
                    addition(arraySplit, i);
                    i = i - 1;
                } else if (arraySplit.get(i).equals("-")) {
                    subtraction(arraySplit, i);
                    i = i - 1;
                }
            }
        }
        return parseDouble(arraySplit.get(0));
    }

    /**
     * A method that subtracts two adjacent values from a sign.
     *
     * @param arraySplit list of all formula elements.
     * @param index      an index that shows where the list sign is.
     */
    private static void subtraction(MyArrayList<String> arraySplit, int index) {                                        // ArrayList
        double res = parseDouble(arraySplit.get(index - 1)) - parseDouble(arraySplit.get(index + 1));
        arraySplit.add(index - 1, Double.toString(res));
        removeUnnecessary(arraySplit, index);
    }


    /**
     * A method that adds two adjacent values from a sign.
     *
     * @param arraySplit list of all formula elements.
     * @param index      an index that shows where the list sign is.
     */
    private static void addition(MyArrayList<String> arraySplit, int index) {                                           // ArrayList
        double res = parseDouble(arraySplit.get(index - 1)) + parseDouble(arraySplit.get(index + 1));
        arraySplit.add(index - 1, Double.toString(res));
        removeUnnecessary(arraySplit, index);
    }

    /**
     * A method that separates two adjacent values from a sign.
     *
     * @param arraySplit list of all formula elements.
     * @param index      an index that shows where the list sign is.
     */
    private static void division(MyArrayList<String> arraySplit, int index) {                                           // ArrayList
        double res = parseDouble(arraySplit.get(index - 1)) / parseDouble(arraySplit.get(index + 1));
        arraySplit.add(index - 1, Double.toString(res));
        removeUnnecessary(arraySplit, index);
    }

    /**
     * A method that multiplies two adjacent values from a sign.
     *
     * @param arraySplit list of all formula elements.
     * @param index      an index that shows where the list sign is.
     */
    private static void multiplication(MyArrayList<String> arraySplit, int index) {                                     // ArrayList
        double res = parseDouble(arraySplit.get(index - 1)) * parseDouble(arraySplit.get(index + 1));
        arraySplit.add(index - 1, Double.toString(res));
        removeUnnecessary(arraySplit, index);
    }

    /**
     * A method that elevates two adjacent values from a sign.
     *
     * @param arraySplit list of all formula elements.
     * @param index      an index that shows where the list sign is.
     */
    private static void elevationToTheDegree(MyArrayList<String> arraySplit, int index) {                               // ArrayList
        double res = Math.pow(parseDouble(arraySplit.get(index - 1)), parseDouble(arraySplit.get(index + 1)));
        arraySplit.add(index - 1, Double.toString(res));
        removeUnnecessary(arraySplit, index);
    }

    /**
     * A method that removes unnecessary items from the list after the operation.
     *
     * @param arraySplit list of all formula elements.
     * @param index      index of items to delete.
     */
    private static void removeUnnecessary(MyArrayList<String> arraySplit, int index) {                                  // ArrayList
        arraySplit.remove(index);
        arraySplit.remove(index);
        arraySplit.remove(index);
    }
}
