package com.shpp.p2p.cs.vsamchenko.exam.assignment17;

/**
 * A class that is designed to work with source information.
 */
class Informer {

    /**
     * A private constructor that does not allow you to create objects of this class.
     */
    private Informer() {
    }

    /**
     * A method that displays a test message to the user.
     *
     * @param result   test result.
     * @param nameTest case name.
     */
    static void testResult(boolean result, String nameTest) {
        if (result) {
            System.out.println("\u001b[32m Case : \"" + nameTest + "\" completed!");
            return;
        }
        System.out.println("\u001b[31m Case : \"" + nameTest + "\" not completed!");
    }
}
