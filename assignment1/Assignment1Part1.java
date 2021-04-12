package com.shpp.p2p.cs.vsamchenko.exam.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * A program that allows work to move to the "newspaper", pick it up and return to the starting point.
 */
public class Assignment1Part1 extends KarelTheRobot {

    /**
     * Karel is at the beginning.
     * Karel takes the newspaper and returns to the beginning.
     */
    public void run() throws Exception {
        moveToTheDoor();
        moveAndPickToTheBeeper();
        moveToTheStartingPoint();
    }

    /**
     * Newspaper taken. Karel is facing east.
     * Karel returns to the starting point, faces east.
     */
    private void moveToTheStartingPoint() throws Exception {
        turnAround();
        moveUntilWall();
        turnRight();
        move();
        turnRight();
    }

    /**
     * Karel starts in the northwest corner of his house.
     * Karel goes to the "door".
     */
    private void moveToTheDoor() throws Exception {
        moveUntilWall();
        turnRight();
        move();
        turnLeft();
    }

    /**
     * Additional function for reversal Karel.
     */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /**
     * Karel stands at the "door", facing east.
     * Karel goes and takes the "newspaper".
     */
    private void moveAndPickToTheBeeper() throws Exception {
        while (noBeepersPresent()) {
            move();
        }
        pickBeeper();
    }

    /**
     * Additional function for turn right Karel.
     */
    private void turnRight() throws Exception {
        for (var i = 0; i < 3; i++) {
            turnLeft();
        }
    }

    /**
     * Additional function for finding the wall ahead.
     */
    private void moveUntilWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }
}

