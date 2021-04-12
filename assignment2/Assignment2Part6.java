package com.shpp.p2p.cs.vsamchenko.exam.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * A program that draws a caterpillar.
 */
public class Assignment2Part6 extends WindowProgram {

    /**
     * The number of caterpillar segments that the user adjusts.
     */
    private static final int NUMBER_OF_CATERPILLAR_SEGMENT = 8;
    /**
     * The diameter of one segment of the caterpillar.
     */
    private static final int DIAMETER = 120;
    /**
     * A constant that regulates the segments along the x-axis, relative to each other.
     */
    private static final double  AMENDMENT_X_AXIS = 1.66;
    /**
     * A constant that regulates the segments along the y-axis, relative to each other.
     */
    private static final int AMENDMENT_Y_AXIS = 4;
    /**
     * A constant that indicates the height of the menu bar.
     */
    private static final int MENU_LINE_HEIGHT = 24;
    /**
     * The width of our workspace.
     */
    public static final int APPLICATION_WIDTH = (int) (DIAMETER + ((DIAMETER / AMENDMENT_X_AXIS)
            * (NUMBER_OF_CATERPILLAR_SEGMENT - 1)));
    /**
     * The height of our workspace.
     */
    public static final int APPLICATION_HEIGHT = DIAMETER + (DIAMETER / AMENDMENT_Y_AXIS) + MENU_LINE_HEIGHT;

    /**
     * A function that is the entry point into the program.
     */
    public void run(){
        drawCaterpillar ();
    }

    /**
     * Function that draws a caterpillar.
     */
    private void drawCaterpillar() {
        boolean  circlePosition = false;
        for (int i = 0; i < NUMBER_OF_CATERPILLAR_SEGMENT; i++){
            drawItem(i, circlePosition = !circlePosition);
        }
    }

    /**
     * A function that draws one segment of a caterpillar.
     */
    private void drawItem(int segments, boolean circlePosition) {
        GOval oval = new GOval(
                segments * DIAMETER / AMENDMENT_X_AXIS,
                circlePosition ? DIAMETER / AMENDMENT_Y_AXIS : 0,
                DIAMETER, DIAMETER);
        oval.setFilled(true);
        oval.setColor(Color.RED);
        oval.setFillColor(Color.GREEN);
        add(oval);
    }
}