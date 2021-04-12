package com.shpp.p2p.cs.vsamchenko.exam.assignment16;

/**
 * A class that is intended for testing MyStack.
 */
class TesterMyQueue {

    /**
     * Private constructor which does not allow you to create objects of this class.
     */
    private TesterMyQueue() {
    }

    /**
     * MyQueue with which all transactions are performed.
     */
    private static final MyQueue<Integer> testedMyQueue = new MyQueue<>();

    /**
     * A method that triggers all checks and initializes the transmission of completion status information.
     */
    static void testMyQueue() {
        boolean addCaseMyQueue = addCaseMyQueue(50);
        Informer.testResult(addCaseMyQueue, "addCaseMyQueue");

        boolean poolAdnPeekCaseMyQueue = pollAndPeekCaseMyQueue(10);
        Informer.testResult(poolAdnPeekCaseMyQueue, "poolAdnPeekCaseMyQueue");
    }

    /**
     * Checking the method of add().
     * "numberOfCycles" elements are added to the queue.
     *
     * @param numberOfCycles number of elements.
     * @return boolean value or successful completion of the case.
     */
    private static boolean addCaseMyQueue(int numberOfCycles) {
        for (int i = 0; i < numberOfCycles; i++) {
            testedMyQueue.add(i);
        }
        if (testedMyQueue.size() == numberOfCycles && testedMyQueue.peek() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of peek() and poll().
     * We check whether at the end of the queue finds the right item and then get it out of there.
     *
     * @param numberOfCycles number of inspections.
     * @return boolean value or successful completion of the case.
     */
    private static boolean pollAndPeekCaseMyQueue(int numberOfCycles) {
        int counter = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            if (testedMyQueue.peek() == i && testedMyQueue.poll() == i) {
                counter++;
            }
        }
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }
}
