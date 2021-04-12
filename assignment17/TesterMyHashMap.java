package com.shpp.p2p.cs.vsamchenko.exam.assignment17;

import acm.util.RandomGenerator;

import java.util.HashMap;

/**
 * A class that tests MyHashMap.
 */
class TesterMyHashMap {

    /**
     * MyHashMap with which we perform all operations.
     */
    private static final MyHashMap<Integer, Integer> testedMyHashMap = new MyHashMap<>();

    /**
     * HashMap with which we perform all operations.
     */
    private static final HashMap<Integer, Integer> testedHashMap = new HashMap<>();

    /**
     * random key.
     */
    private static int randomKey;

    /**
     * random value.
     */
    private static int randomValue;

    /**
     * Private constructor which does not allow you to create objects of this class.
     */
    private TesterMyHashMap() {
    }

    /**
     * A method that triggers all checks and initializes the transmission of completion status information.
     */
    static void testMyHashMap() {
        boolean putAndGetCaseMyHashMap = putAndGetCaseMyHashMap(100);
        Informer.testResult(putAndGetCaseMyHashMap, "putAndGetCaseMyHashMap");

        boolean containsKeyAndContainsValueCaseMyHashMap = containsKeyAndContainsValueCaseMyHashMap(100);
        Informer.testResult(containsKeyAndContainsValueCaseMyHashMap,
                "containsKeyAndContainsValueCaseMyHashMap");

        boolean removeAndSizeCaseMyHashMap = removeAndSizeCaseMyHashMap(100);
        Informer.testResult(removeAndSizeCaseMyHashMap, "removeAndSizeCaseMyHashMap");
    }

    /**
     * A method that adds random keys and values to a maps, and checks to see if they are there.
     *
     * @param numberOfCycles number of elements.
     * @return boolean value or successful completion of the case.
     */
    private static boolean putAndGetCaseMyHashMap(int numberOfCycles) {
        for (int i = 0; i < numberOfCycles; i++) {
            randomKey = RandomGenerator.getInstance().nextInt(numberOfCycles);
            randomValue = RandomGenerator.getInstance().nextInt(numberOfCycles);
            testedMyHashMap.put(randomKey, randomValue);
            testedHashMap.put(randomKey, randomValue);
        }

        for (int i = 0; i < numberOfCycles; i++) {
            randomKey = RandomGenerator.getInstance().nextInt(numberOfCycles);
            if (testedMyHashMap.containsKey(randomKey) && testedHashMap.containsKey(randomKey) &&
                    !testedMyHashMap.get(randomKey).equals(testedHashMap.get(randomKey))) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method that checks to see if maps have random keys and values.
     *
     * @param numberOfCycles number of elements.
     * @return boolean value or successful completion of the case.
     */
    private static boolean containsKeyAndContainsValueCaseMyHashMap(int numberOfCycles) {
        for (int i = 0; i < numberOfCycles; i++) {
            randomKey = RandomGenerator.getInstance().nextInt(numberOfCycles);
            randomValue = RandomGenerator.getInstance().nextInt(numberOfCycles);
            if (testedMyHashMap.containsKey(randomKey) != testedHashMap.containsKey(randomKey) ||
                    testedMyHashMap.containsValue(randomValue) != testedHashMap.containsValue(randomValue)) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method that deletes random keys and checks whether they have been deleted
     * and whether the size of the two maps is the same.
     *
     * @param numberOfCycles number of elements.
     * @return boolean value or successful completion of the case.
     */
    private static boolean removeAndSizeCaseMyHashMap(int numberOfCycles) {
        for (int i = 0; i < numberOfCycles; i++) {
            randomKey = RandomGenerator.getInstance().nextInt(numberOfCycles);
            testedMyHashMap.remove(randomKey);
            testedHashMap.remove(randomKey);
            if (testedMyHashMap.size() != testedHashMap.size()) {
                return false;
            }
        }
        return true;
    }
}
