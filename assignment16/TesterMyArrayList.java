package com.shpp.p2p.cs.vsamchenko.exam.assignment16;

/**
 * A class that is intended for testing MyArrayList.
 */
class TesterMyArrayList {

    /**
     * Private constructor which does not allow you to create objects of this class.
     */
    private TesterMyArrayList() {
    }

    /**
     * MyArrayList with which we perform all operations.
     */
    private static final MyArrayList<Integer> testedMyArrayList = new MyArrayList<>();


    /**
     * A constant that indicates a multiple of which number to use the index.
     */
    private static final int MULTIPLE = 10;

    /**
     * Elements that are used to make it easier to separate from the array.
     */
    private static final int ELEMENT = 999;

    /**
     * A method that triggers all checks and initializes the transmission of completion status information.
     */
    static void testMyArrayList() {
        boolean addCaseMyArrayList = addCaseMyArrayList(100);
        Informer.testResult(addCaseMyArrayList, "addCaseMyArrayList");

        boolean addIndexCaseMyArrayList = addIndexCaseMyArrayList(5);
        Informer.testResult(addIndexCaseMyArrayList, "addIndexCaseMyArrayList");

        boolean removeIndexCaseMyArrayList = removeIndexCaseMyArrayList(5);
        Informer.testResult(removeIndexCaseMyArrayList, "removeIndexCaseMyArrayList");

        boolean removeObjectCaseMyArrayList = removeObjectCaseMyArrayList(50);
        Informer.testResult(removeObjectCaseMyArrayList, "removeObjectCaseMyArrayList");

        boolean setCaseMyArrayList = setCaseMyArrayList(50);
        Informer.testResult(setCaseMyArrayList, "setCaseMyArrayList");

        boolean containsCaseMyArrayList = containsCaseMyArrayList();
        Informer.testResult(containsCaseMyArrayList, "containsCaseMyArrayList");
    }

    /**
     * Checking the method of add(object).
     * Numbers from 0 to "numberOfCycles" are written to the array.
     *
     * @param numberOfCycles the number of elements that are added to the array.
     * @return boolean value or successful completion of the case.
     */
    private static boolean addCaseMyArrayList(int numberOfCycles) {
        int counter = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            testedMyArrayList.add(i);
            if (testedMyArrayList.get(i) == i) {
                counter++;
            }
        }
        if (testedMyArrayList.size() == numberOfCycles && counter == numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of add(index, object).
     * We add to the array "ELEMENT" each "MULTIPLE" positions.
     *
     * @param numberOfCycles the number of elements that are added to the array. Cannot go beyond the array.
     * @return boolean value or successful completion of the case.
     */
    private static boolean addIndexCaseMyArrayList(int numberOfCycles) {
        int size = testedMyArrayList.size();
        for (int i = 0; i < numberOfCycles; i++) {
            testedMyArrayList.add(i * MULTIPLE, ELEMENT);
        }

        int result = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            result += testedMyArrayList.get(i * MULTIPLE);
        }
        if (testedMyArrayList.size() == size + numberOfCycles && result == ELEMENT * numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of remove(index).
     * Delete each "MULTIPLE" items that were recorded earlier.
     *
     * @param numberOfCycles how many elements do delete. It should be equal to the previous case.
     * @return boolean value or successful completion of the case.
     */
    private static boolean removeIndexCaseMyArrayList(int numberOfCycles) {
        int size = testedMyArrayList.size();
        for (int i = numberOfCycles; i > 0; i--) {
            testedMyArrayList.remove(i * MULTIPLE - MULTIPLE);
        }

        int result = 0;
        int arrayContain = 0;
        for (int i = 0; i < numberOfCycles; i++) {
            result += i * MULTIPLE;
            arrayContain += testedMyArrayList.get(i * MULTIPLE);
        }
        if (result == arrayContain && testedMyArrayList.size() == size - numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of remove(object).
     * Delete the last "numberOfCycles" elements.
     *
     * @param numberOfCycles how many elements do delete.Cannot be larger than array size.
     * @return boolean value or successful completion of the case.
     */
    private static boolean removeObjectCaseMyArrayList(int numberOfCycles) {
        int size = testedMyArrayList.size();
        int removeObject = testedMyArrayList.get(testedMyArrayList.size() - 1);

        for (int i = 0; i < numberOfCycles; i++) {
            testedMyArrayList.remove(removeObject--);
        }
        if (testedMyArrayList.get(testedMyArrayList.size() - 1) == removeObject &&
                testedMyArrayList.size() == size - numberOfCycles) {
            return true;
        }
        return false;
    }

    /**
     * Checking the method of set(index, object).
     * Replaces the first "numberOfCycles" elements.
     *
     * @param numberOfCycles how many elements change. Cannot go beyond the array.
     * @return boolean value or successful completion of the case.
     */
    private static boolean setCaseMyArrayList(int numberOfCycles) {
        boolean result = false;
        int size = testedMyArrayList.size();
        int arrayContain = 0;

        for (int i = 0; i < numberOfCycles; i++) {
            testedMyArrayList.set(i, ELEMENT);
            arrayContain += testedMyArrayList.get(i);
        }
        if (size == testedMyArrayList.size() && ELEMENT * numberOfCycles == arrayContain) {
            result = true;
        }
        return result;
    }

    /**
     * Checking the method of contains(object).
     * Check if there is an "ELEMENT" in the list (it should be!). And if there is no 0 in the list (it should not be!).
     *
     * @return boolean value or successful completion of the case.
     */
    private static boolean containsCaseMyArrayList() {
        return testedMyArrayList.contains(ELEMENT) && !testedMyArrayList.contains(0);
    }
}