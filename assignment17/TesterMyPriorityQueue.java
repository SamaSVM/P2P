package com.shpp.p2p.cs.vsamchenko.exam.assignment17;

import acm.util.RandomGenerator;

import java.util.PriorityQueue;

/**
 * A class that tests MyPriorityQueue.
 */
class TesterMyPriorityQueue {

    /**
     * MyPriorityQueue with which we perform all operations.
     */
    private static final MyPriorityQueue<Integer> testedMyPriorityQueue = new MyPriorityQueue<>();

    /**
     * PriorityQueue with which we perform all operations.
     */
    private static final PriorityQueue<Integer> testedPriorityQueue = new PriorityQueue<>();

    /**
     * Private constructor which does not allow you to create objects of this class.
     */
    private TesterMyPriorityQueue() {
    }

    /**
     * A method that triggers all checks and initializes the transmission of completion status information.
     */
    static void testMyPriorityQueue() {
        boolean addPeekAndPollCaseMyPriorityQueue = addPeekAndPollCaseMyPriorityQueue(100);
        Informer.testResult(addPeekAndPollCaseMyPriorityQueue,
                "addPeekAndPollCaseMyPriorityQueue");

        boolean containsSizeAndIsEmptyCaseMyPriorityQueue = containsSizeAndIsEmptyCaseMyPriorityQueue(100);
        Informer.testResult(containsSizeAndIsEmptyCaseMyPriorityQueue,
                "containsSizeAndIsEmptyCaseMyPriorityQueue");
    }

    /**
     * A method that tests the following methods of the class: add, peek, poll.
     * The method performs the same operations on MyPriorityQueue and PriorityQueue, and compares their results.
     *
     * @param numberOfCycles number of cycles.
     * @return boolean value or successful completion of the case.
     */
    private static boolean addPeekAndPollCaseMyPriorityQueue(int numberOfCycles) {
        for (int i = 0; i < numberOfCycles; i++) {
            int randomInt = RandomGenerator.getInstance().nextInt();
            testedMyPriorityQueue.add(randomInt);
            testedPriorityQueue.add(randomInt);
        }

        for (int i = 0; i < numberOfCycles; i++) {
            if (!testedMyPriorityQueue.peek().equals(testedPriorityQueue.peek())
                    || !testedMyPriorityQueue.poll().equals(testedPriorityQueue.poll())) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method that tests the following methods of the class: contains, size, isEmpty.
     * The method performs the same operations on MyPriorityQueue and PriorityQueue, and compares their results.
     *
     * @param numberOfCycles number of cycles.
     * @return boolean value or successful completion of the case.
     */
    private static boolean containsSizeAndIsEmptyCaseMyPriorityQueue(int numberOfCycles) {
        int randomInt;

        for (int i = 0; i < numberOfCycles; i++) {
            randomInt = RandomGenerator.getInstance().nextInt();
            testedMyPriorityQueue.add(randomInt);
            testedPriorityQueue.add(randomInt);
        }

        for (int i = 0; i < numberOfCycles; i++) {
            randomInt = RandomGenerator.getInstance().nextInt(numberOfCycles);
            if (testedMyPriorityQueue.contains(randomInt) != testedPriorityQueue.contains(randomInt) ||
                    testedMyPriorityQueue.size() != testedPriorityQueue.size() ||
                    testedMyPriorityQueue.isEmpty() != testedPriorityQueue.isEmpty()) {
                return false;
            }
            testedMyPriorityQueue.poll();
            testedPriorityQueue.poll();
        }
        return true;
    }
}
