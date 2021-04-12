package com.shpp.p2p.cs.vsamchenko.exam.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Double.parseDouble;

/**
 *
 */
public class Calculator {

    /**
     *
     */
    static HashMap<String, Double> variables = new HashMap<>();

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ви не ввели параметри");
        } else {
            checkParameters(args);
            System.out.println(calculate(args[0].replaceAll("\\s", ""), variables));
        }
    }

    private static void checkParameters(String[] args) {
        for (int i = 1; i < args.length; i++) {
            // check for two characters ==
            int count = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (args[i].charAt(j) == '=') {
                    count++;
                }
            }

            // checking the correctness of the input of arguments
            if (args[i].indexOf('=') == -1 ||
                    args[i].indexOf('=') == args[i].length() - 1 ||
                    args[i].indexOf('=') == 0 ||
                    count > 1) {
                System.out.println("Invalid parameter set : " + args[i]);
            } else {
                // remove all spaces
                args[i] = args[i].replaceAll("\\s", "");
                // все що до = в кей все що після = в намбер
                String key = args[i].substring(0, args[i].indexOf('='));
                String number = args[i].substring(args[i].indexOf('=') + 1);
                // to whom we replace with a point
                number = number.replace(',', '.');
                // correctness of data entry
                if (!key.matches("[0-9]+") && number.matches("[0-9.-]+")) {
                    variables.put(key, parseDouble(number));
                } else {
                    System.out.println("Invalid parameter set : " + args[i]);
                }
            }
        }
        System.out.println(variables);
    }




    static double calculate(String formula, HashMap<String, Double> variables) {

        ArrayList<String> arraySplit = new ArrayList<>();

        if (theFirstSign(formula) > 0 && theFirstSign(formula.substring(formula.length()-1)) == -1) {
            while (theFirstSign(formula) != -1) {
                arraySplit.add(formula.substring(0, theFirstSign(formula)));
                formula = formula.substring(theFirstSign(formula));
                arraySplit.add(formula.substring(0, 1));
                formula = formula.substring(1);
            }
            arraySplit.add(formula);
        } else {
            System.out.println("Не коректно введена формула1 : " + formula);

        }

        //replace variables with numbers
        for (int i = 0; i < arraySplit.size(); i++) {
            if (arraySplit.get(i).matches("[a-zA-Z]+")) {
                if (variables.get(arraySplit.get(i)) == null) {
                    System.out.println("there is no such variable : " + arraySplit.get(i));
                    System.exit(1);
                }
                arraySplit.set(i, Double.toString(variables.get(arraySplit.get(i))));
            }
        }
        System.out.println(arraySplit);


        return conductingMathematicalOperations(arraySplit);
    }

    private static double conductingMathematicalOperations(ArrayList<String> arraySplit) {

        //elevate to the degree
        for (int i = arraySplit.size() - 1; i > 0; i--){
            if(arraySplit.get(i).equals("^")){
                double res = Math.pow(parseDouble(arraySplit.get(i - 1)), parseDouble(arraySplit.get(i + 1)));
                arraySplit.add(i - 1, Double.toString(res));
                arraySplit.remove(i);
                arraySplit.remove(i);
                arraySplit.remove(i);
                i = i + 1;
            }
        }

        //multiply and divide
        for ( int i = 0; i < arraySplit.size() - 1; i++){
            if(arraySplit.get(i).equals("*") || arraySplit.get(i).equals("/")){
                if(arraySplit.get(i).equals("*")){
                    double res = parseDouble(arraySplit.get(i - 1)) * parseDouble(arraySplit.get(i + 1));
                    arraySplit.add(i - 1, Double.toString(res));
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    i = i - 1;
                }
                else if(arraySplit.get(i).equals("/")){
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
        for ( int i = 0; i < arraySplit.size() - 1; i++){
            if(arraySplit.get(i).equals("+") || arraySplit.get(i).equals("-")){
                if(arraySplit.get(i).equals("+")){
                    double res = parseDouble(arraySplit.get(i - 1)) + parseDouble(arraySplit.get(i + 1));
                    arraySplit.add(i - 1, Double.toString(res));
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    arraySplit.remove(i);
                    i = i - 1;
                }
                else if(arraySplit.get(i).equals("-")){
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

    private static int[] definitionOfIndices(String formula) {
        int[] arrayIndexesOfCharacters = {formula.indexOf('^'), formula.indexOf('+'), formula.indexOf("-"),
                formula.indexOf('*'), formula.indexOf('/')};
        Arrays.sort(arrayIndexesOfCharacters);

        return arrayIndexesOfCharacters;
    }
}
