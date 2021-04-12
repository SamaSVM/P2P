package com.shpp.p2p.cs.vsamchenko.exam.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program is designed to search for the middle of the world.
 */
public class Assignment1Part3 extends KarelTheRobot {
    /**
     * Karel stands in the southwest corner and faces east.
     * Beeper is in the middle cell or in one of the two central.
     */
    public void run() throws Exception {

        if (frontIsBlocked()) {
            putBeeper();
        } else {
            putAllBeepers();
            searchMiddleLevel();

        /*
        Karel is located in the middle of the south street.
        Karel puts a beeper in the middle cell.
         */
            putBeeper();
        }
    }

    /**
     * All over the south street there are "beeper" except for the first cell.
     * Karel takes the beepers from the sides until he reaches the middle.
     */
    private void searchMiddleLevel() throws Exception {
        while (beepersPresent()) {
            pickBeeper();
            move();
            moveUntilBeepers();
            turnAround();
            move();
        }
    }

    /**
     * Karel is located on the southwest corner, facing east.
     * Beepers are licking all over the south street except for the first cell.
    */
    private void putAllBeepers() throws Exception {
        move();
        while (frontIsClear()) {
            putBeeper();
            move();
        }
        putBeeper();
        turnAround();
    }

    /**
     * Additional function for Karel reversal.
     */
    private void turnAround() throws Exception {
        for (var i = 0; i < 2; i++) {
            turnLeft();
        }
    }

    /**
     * An additional feature to find the beeper ahead.
     */
    public void moveUntilBeepers() throws Exception {
        while (beepersPresent()) {
            move();
        }
    }
}
