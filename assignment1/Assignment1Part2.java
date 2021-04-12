package com.shpp.p2p.cs.vsamchenko.exam.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * This program is designed to build rows of stones in front of the pillars.
 */
public class Assignment1Part2 extends KarelTheRobot {

    /**
     * Karel starts in the southwest corner and looks east.
     * Each column has rows of stones.
     */
    public void run() throws Exception {
        while (frontIsClear()) {
            determineColumnWay();
            makeColumns();
        }
        determineColumnWay();
        makeColumns();
    }

    /**
     * Karel starts in the southwest corner and looks east.
     * Karel finds the north side.
     */
    private void determineColumnWay() throws Exception {
        while (notFacingNorth()) {
            turnLeft();
        }
    }

    /**
     * Karel stands at the beginning of the first column, facing north.
     * Karel makes a line of beepers near each column.
     */
    private void makeColumns() throws Exception {
        makeOneColumn();
        goToStartColumn();
        searchNextColumn();
    }

    /**
     * Karel stands at the beginning of the end of the first column, facing north.
     * Karel stands at the end of the next column.
     */
    private void searchNextColumn() throws Exception {
        turnLeft();
        if (frontIsClear()) {
            goToNextColumn();
        }
    }

    /**
     * A method that indicates how Karelia will go to the next column. The distance between the columns is always four.
     */
    private void goToNextColumn() throws Exception {
        move();
        move();
        move();
        move();
    }

    /**
     * Karel stands at the end of the column facing south.
     * Karel stands at the beginning of the column facing north.
     */
    private void goToStartColumn() throws Exception {
        turnAround();
        while (frontIsClear()) {
            move();
        }
    }

    /**
     * Karel stands at the beginning of the column facing north.
     * Karel makes a line of beepers.
     */
    private void makeOneColumn() throws Exception {
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            } else {
                move();
            }
        }
        if (noBeepersPresent()) {
            putBeeper();
        }
    }

    /**
     * Additional function for Karel reversal.
     */
    private void turnAround() throws Exception {
        for (var i = 0; i < 2; i++) {
            turnLeft();
        }
    }
}