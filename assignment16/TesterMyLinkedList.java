package com.shpp.p2p.cs.vsamchenko.exam.assignment16;

import acm.util.RandomGenerator;

/**
 * A class that is intended for testing MyLinkedList.
 */
class TesterMyLinkedList {

    /**
     * Private constructor which does not allow you to create objects of this class.
     */
    private TesterMyLinkedList() {
    }

    /**
     * MyLinkedList with which all operations are performed.
     */
    private static final MyLinkedList<Integer> testedMyLinkedList = new MyLinkedList<>();

    /**
     * A constant that indicates a multiple of which number to use the index.
     */
    private static final int MULTIPLE = 10;

    /**
     * Elements that are used to make it easier to separate from the list.
     */
    private static final int ELEMENT = 999;

    /**
     * A method that triggers all checks and initializes the transmission of completion status information.
     */
    static void testMyLinkedList() {
        boolean addFirstCaseMyLinkedList = addFirstCaseMyLinkedList(50);
        Informer.testResult(addFirstCaseMyLinkedList, "addFirstCaseMyLinkedList");

        boolean addLastCaseMyLinkedList = addLastCaseMyLinkedList(50);
        Informer.testResult(addLastCaseMyLinkedList, "addLastCaseMyLinkedList");

        boolean addIndexCaseMyLinkedList = addIndexCaseMyLinkedList(5);
        Informer.testResult(addIndexCaseMyLinkedList, "addIndexCaseMyLinkedList");

        boolean setCaseMyLinkedList = setCaseMyLinkedList(5);
        Informer.testResult(setCaseMyLinkedList, "setCaseMyLinkedList");

        boolean pollIndexCaseMyLickedList = pollIndexCaseMyLickedList(5);
        Informer.testResult(pollIndexCaseMyLickedList, "pollIndexCaseMyLickedList");

        boolean peekFirstCaseMyLinkedList = peekFirstCaseMyLinkedList(5);
        Informer.testResult(peekFirstCaseMyLinkedList, "peekFirstCaseMyLinkedList");

        boolean peekLastCaseMyLinkedList = peekLastCaseMyLinkedList(5);
        Informer.testResult(peekLastCaseMyLinkedList, "peekLastCaseMyLinkedList");

        boolean peekIndexCaseMyLinkedList = peekIndexCaseMyLinkedList(10);
        Informer.testResult(peekIndexCaseMyLinkedList, "peekIndexCaseMyLinkedList");

        boolean indexOfCaseMyLinkedList = indexOfCaseMyLinkedList(10);
        Informer.testResult(indexOfCaseMyLinkedList, "indexOfCaseMyLinkedList");

        boolean pollFirstCaseMyLinkedList = pollFirstCaseMyLinkedList(10);
        Informer.testResult(pollFirstCaseMyLinkedList, "pollFirstCaseMyLinkedList");

        boolean pollLastCaseMyLinkedList = pollLastCaseMyLinkedList(10);
        Informer.testResult(pollLastCaseMyLinkedList, "pollLastCaseMyLinkedList");
    }

    /**
     * Checking the method of addFirst().
     * Add "numberOfCycles" elements to the start of the list.
     *
     * @param numberOfCycles how many elements to add.
     * @return boolean value or successful completion of the case.
     */
    private static boolean addFirstCaseMyLinkedList(int numberOfCycles) {
        int counter = 0;
        for (int i = numberOfCycles; i > 0; i--) {
            testedMyLinkedList.addFirst(i);
            if (testedMyLinkedList.peekFirst() == i) {
                counter++;
            }
        }
        if (testedMyLinkedList.size() == numberOfCycles && counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of addLast().
     * Add "numberOfCycles" elements to the end of the list.
     *
     * @param numberOfCycles how many elements to add.
     * @return boolean value or successful completion of the case.
     */
    private static boolean addLastCaseMyLinkedList(int numberOfCycles) {
        int size = testedMyLinkedList.size();
        int listContain = testedMyLinkedList.peekLast();
        for (int i = 0; i < numberOfCycles; i++) {
            testedMyLinkedList.addLast(++listContain);
        }
        if (size + numberOfCycles == testedMyLinkedList.size() && size + numberOfCycles == testedMyLinkedList.peekLast()) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of add(index, object).
     * We add to the list "ELEMENT" each "MULTIPLE" positions.
     *
     * @param numberOfCycles the number of elements that are added to the list. Cannot go beyond the list.
     * @return boolean value or successful completion of the case.
     */
    private static boolean addIndexCaseMyLinkedList(int numberOfCycles) {
        int size = testedMyLinkedList.size();
        for (int i = 0; i < numberOfCycles; i++) {
            testedMyLinkedList.add(i * MULTIPLE, ELEMENT);
        }

        int result = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            result += testedMyLinkedList.peek(i * MULTIPLE);
        }
        if (testedMyLinkedList.size() == size + numberOfCycles && result == ELEMENT * numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of set(index, object).
     * Replaces the first "numberOfCycles" elements.
     *
     * @param numberOfCycles how many elements change. Cannot go beyond the list.
     * @return boolean value or successful completion of the case.
     */
    private static boolean setCaseMyLinkedList(int numberOfCycles) {
        int size = testedMyLinkedList.size();
        int listContain = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            testedMyLinkedList.set(i * MULTIPLE, 0);
        }

        for (int i = 0; i < numberOfCycles; i++) {
            listContain += testedMyLinkedList.peek(i * MULTIPLE);
        }
        if (listContain == 0 && size == testedMyLinkedList.size()) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of poll(index).
     * Return and delete "numberOfCycles" elements.
     *
     * @param numberOfCycles how many items to rotate and delete. Must be equal to the last check.
     * @return boolean value or successful completion of the case.
     */
    private static boolean pollIndexCaseMyLickedList(int numberOfCycles) {
        int counter = 0;
        int size = testedMyLinkedList.size();
        for (int i = numberOfCycles - 1; i >= 0; i--) {
            if (testedMyLinkedList.poll(i * MULTIPLE) == 0) {
                counter++;
            }
        }
        if (size - numberOfCycles == testedMyLinkedList.size() && counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of peekFirst().
     * Add a random number to the beginning of the list and check if it is really there.
     *
     * @param numberOfCycles how many the check is performed.
     * @return boolean value or successful completion of the case.
     */
    private static boolean peekFirstCaseMyLinkedList(int numberOfCycles) {
        int counter = 0;
        int randomInt;
        for (int i = 0; i < numberOfCycles; i++) {
            randomInt = RandomGenerator.getInstance().nextInt();
            testedMyLinkedList.set(0, randomInt);
            if (randomInt == testedMyLinkedList.peekFirst()) {
                counter++;
            }
        }
        testedMyLinkedList.pollFirst();
        testedMyLinkedList.addFirst(testedMyLinkedList.peekFirst() - 1);
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of peekLast().
     * Add a random number to the end of the list and check if it is really there.
     *
     * @param numberOfCycles how many the check is performed.
     * @return boolean value or successful completion of the case.
     */
    private static boolean peekLastCaseMyLinkedList(int numberOfCycles) {
        int counter = 0;
        int randomInt;
        for (int i = 0; i < numberOfCycles; i++) {
            randomInt = RandomGenerator.getInstance().nextInt();
            testedMyLinkedList.set(testedMyLinkedList.size() - 1, randomInt);
            if (randomInt == testedMyLinkedList.peekLast()) {
                counter++;
            }
        }
        testedMyLinkedList.pollLast();
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of peek(index).
     * We add a random number to various lists and check if it is really there.
     *
     * @param numberOfCycles how many the check is performed.
     * @return boolean value or successful completion of the case.
     */
    private static boolean peekIndexCaseMyLinkedList(int numberOfCycles) {
        int counter = 0;
        int randomInt;
        for (int i = 0; i < numberOfCycles; i++) {
            randomInt = RandomGenerator.getInstance().
                    nextInt(testedMyLinkedList.peekFirst(), testedMyLinkedList.peekLast());
            if (testedMyLinkedList.peek(randomInt - 1) == randomInt) {
                counter++;
            }
        }
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of indexOf(index).
     * The list must be in order. We take a random number and
     * check whether the corresponding index is really that number.
     *
     * @param numberOfCycles how many the check is performed.
     * @return boolean value or successful completion of the case.
     */
    private static boolean indexOfCaseMyLinkedList(int numberOfCycles) {
        int counter = 0;
        int randomInt;
        for (int i = 0; i < numberOfCycles; i++) {
            randomInt = RandomGenerator.getInstance().nextInt(testedMyLinkedList.peekFirst(), testedMyLinkedList.peekLast());
            if (testedMyLinkedList.indexOf(randomInt) == randomInt - 1) {
                counter++;
            }
        }
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of pollFirst().
     * The list must be in order. We check whether the first elements correspond to the order.
     *
     * @param numberOfCycles how many first elements to check.
     * @return boolean value or successful completion of the case.
     */
    private static boolean pollFirstCaseMyLinkedList(int numberOfCycles) {
        int counter = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            if (testedMyLinkedList.pollFirst() == i + 1) {
                counter++;
            }
        }
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of pollLast().
     * The list must be in order. We check whether the last elements correspond to the order.
     *
     * @param numberOfCycles how many last elements to check.
     * @return boolean value or successful completion of the case.
     */
    private static boolean pollLastCaseMyLinkedList(int numberOfCycles) {
        int counter = 0;
        int lastElement = testedMyLinkedList.peekLast();
        for (int i = 0; i < numberOfCycles; i++) {
            if (testedMyLinkedList.pollLast() == lastElement--) {
                counter++;
            }
        }
        if (counter == numberOfCycles) {
            return true;
        }
        return false;
    }
}
