package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment11;

/**
 * A class in which all the checks that are necessary for our program are collected.
 */
class Checker implements Variables {

    /**
     * Private class designer. Objects of this class cannot be created, only refer to its methods.
     */
    private Checker() {
    }

    /**
     * A method that checks whether the user has passed any arguments to the program at all.
     *
     * @param args arguments passed by the user.
     * @return if the arguments have been conveyed then returns the truth, if not then false.
     */
    static boolean checkSizeArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("You have not entered any parameters!");
            return false;
        }
        return true;
    }

    /**
     * A method that verifies the correctness of user input.
     *
     * @param args arguments passed by the user.
     */
    static void checkParameters(String[] args) {
        for (int i = 1; i < args.length; i++) {
            int count = checkTwoEquals(args, i);
            // check that the argument does not begin and end with the = sign
            if (args[i].indexOf('=') == -1 || args[i].indexOf('=') == args[i].length() - 1 ||
                    args[i].indexOf('=') == 0 || count > 1) {
                System.out.println("Invalid parameter : " + args[i]);
            } else {
                Parser.variableProcessor(args, i);
            }
        }
    }


    /**
     * The method that checks whether the user did not enter two characters when entering arguments is equal to.
     *
     * @param args  arguments passed by the user.
     * @param index the index of the argument we are checking from the array.
     * @return the value of the number of characters in the argument.
     */
    private static int checkTwoEquals(String[] args, int index) {
        int count = 0;
        for (int j = 0; j < args[index].length(); j++) {
            if (args[index].charAt(j) == '=') {
                count++;
            }
        }
        return count;
    }
}
