package com.shpp.p2p.cs.vsamchenko.exam.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program will create a resemblance to a chessboard with beepers.
 */
public class Assignment1Part4 extends KarelTheRobot {

    /**
     * The method which is the starting point for building a chessboard.
     */
    public void run() throws Exception {
        heightCheck();
        putAllLine();
    }

    /**
     * Karel stands in the south-west corner, facing east.
     * "Beepers" are in a checkerboard pattern around the world, one of the beavers is located in the southwest corner.
     */
    private void putAllLine() throws Exception {
        while (frontIsClear()) {
            putLine();
            if (leftIsClear()) {
                moveToTheSecondLine();
                putLine();
                if (rightIsClear()) {
                    moveToTheFirstLine();
                }
            }
        }
    }

    /**
     * The length of the street is one cell.
     * Beepers are located through one cell on the avenue.
     */
    private void heightCheck() throws Exception {
        if (frontIsBlocked()) {
            turnLeft();
            putLine();
        }
    }

    /**
     * Karel is located in the eastern part of the odd street facing east.
     * Karel is located in the eastern part of Steam Street, facing west.
    */
    private void moveToTheFirstLine() throws Exception {
        if (beepersPresent()) {
            turnRight();
            move();
            turnRight();
            move();
        } else {
            turnRight();
            move();
            turnRight();
        }
    }

    /**
     * Karel is located on the western part of the steam street, facing west.
     * Karel is located on the western part of the odd street, facing east.
     */
    private void moveToTheSecondLine() throws Exception {
        if (beepersPresent()) {
            turnLeft();
            move();
            turnLeft();
            move();
        } else {
            turnLeft();
            move();
            turnLeft();
        }
    }

    /**
     * Karel is located at the beginning or end of the street.
     * Karel puts the beepers in a checkerboard pattern.
     */
    private void putLine() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            if (beepersPresent()) {
                move();
            }
            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
    }

    /**
     * Additional function for turn right Karel.
     */
    private void turnRight() throws Exception {
        for (var i = 0; i < 3; i++) {
            turnLeft();
        }
    }
}
