package com.shpp.p2p.cs.vsamchenko.exam.assignment10;

import java.util.HashMap;

import static java.lang.Double.parseDouble;

public class Parser {
    public static void main(String[] args) {

        HashMap<String,Double> variables = new HashMap<>();

        for (int i = 1; i < args.length; i++){
            // check for two characters ==
            int count = 0;
            for(int j = 0; j < args[i].length(); j++){
                if (args[i].charAt(j) == '='){
                    count++;
                }
            }

            // checking the correctness of the input of arguments
            if (args[i].indexOf('=') == -1 ||
                args[i].indexOf('=') == args[i].length()-1 ||
                args[i].indexOf('=') == 0 ||
                count > 1) {
                System.out.println("Невірно заданий параметр : " + args[i]);
            }else {
                args[i] = args[i].replaceAll("\\s","");

                String key = args[i].substring(0, args[i].indexOf('='));
                String number = args[i].substring(args[i].indexOf('=') + 1);

                if(!key.matches("[0-9]+") && number.matches("[0-9]+")) {
                    variables.put(key, parseDouble(number));
                }else{
                    System.out.println("Invalid parameter set : " + args[i]);
                }
            }
        }
        calculate(args[0].replaceAll("\\s",""), variables);
    }

    static double calculate(String formula, HashMap<String, Double> variables){
        System.out.println(formula);
        return 0;
    }
}
