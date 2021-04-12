package com.shpp.p2p.cs.vsamchenko.exam.assignment16;

import acm.util.RandomGenerator;

/**
 * A class that is intended for testing MyStack.
 */
class TesterMyStack {

    /**
     * Private constructor which does not allow you to create objects of this class.
     */
    private TesterMyStack() {
    }

    /**
     * MyStack with which all actions are carried out.
     */
    private static final MyStack<Integer> testedMyStack = new MyStack<>();

    /**
     * A method that triggers all checks and initializes the transmission of completion status information.
     */
    static void testMyStack() {
        boolean pushCaseMyStack = pushCaseMyStack(100);
        Informer.testResult(pushCaseMyStack, "pushCaseMyStack");

        boolean popCaseMyStack = popCaseMyStack(50);
        Informer.testResult(popCaseMyStack, "popCaseMyStack");

        boolean peekCaseMyStack = peekCaseMyStack(10);
        Informer.testResult(peekCaseMyStack, "peekCaseMyStack");

        boolean emptyCaseMyStack = searchCaseMyStack(10);
        Informer.testResult(emptyCaseMyStack, "emptyCaseMyStack");
    }

    /**
     * Checking the method of push().
     * Add "numberOfCycles" elements to the stack.
     *
     * @param numberOfCycles how many elements will be added.
     * @return boolean value or successful completion of the case.
     */
    private static boolean pushCaseMyStack(int numberOfCycles) {
        for (int i = 0; i < numberOfCycles; i++) {
            testedMyStack.push(i);
        }
        if (testedMyStack.size() == numberOfCycles && testedMyStack.peek() == numberOfCycles - 1) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of pop().
     * Get "numberOfCycles" elements from the stack.
     *
     * @param numberOfCycles how many items will be extracted.
     * @return boolean value or successful completion of the case.
     */
    private static boolean popCaseMyStack(int numberOfCycles) {
        int stackContain = testedMyStack.peek();
        int count = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            if (testedMyStack.pop() == stackContain--) {
                count++;
            }
        }
        if (count == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of peek().
     * Add a random number to the stack and check if it is there.
     *
     * @param numberOfCycles number of repeated.
     * @return boolean value or successful completion of the case.
     */
    private static boolean peekCaseMyStack(int numberOfCycles) {
        int randomInt;
        int counter = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            randomInt = RandomGenerator.getInstance().nextInt();
            testedMyStack.push(randomInt);
            if (testedMyStack.peek() == randomInt) {
                counter++;
            }
            testedMyStack.pop();
        }
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of search().
     * Take a number within the stack and check if it is there.
     *
     * @param numberOfCycles number of repeated.
     * @return boolean value or successful completion of the case.
     */
    private static boolean searchCaseMyStack(int numberOfCycles) {
        int randomInt;
        int counter = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            randomInt = RandomGenerator.getInstance().nextInt(testedMyStack.size());
            if (testedMyStack.search(randomInt) > -1) {
                counter++;
            }
        }
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }
}
