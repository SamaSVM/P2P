package com.shpp.p2p.cs.vsamchenko.exam.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The program builds a pyramid with a certain number of bricks at the base.
 */
public class Assignment3Part4 extends WindowProgram {

    /**
     * The height of one brick.
     */
    private static final int BRICK_HEIGHT = 10;

    /**
     * The width of one brick.
     */
    private static final int BRICK_WIDTH = 20;

    /**
     * The number of bricks at the base of the pyramid.
     */
    private static final int BRICKS_IN_BASE = 28;

    /**
     * The thickness of the line between the bricks. Must be 10% of the width.
     */
    private static final int INDENTATION_BETWEEN_THE_BRICKS = (int) (BRICK_WIDTH * 0.1);

    /**
     * The coefficient of displacement of the bricks relative to each other.
     */
    private static final double THE_COEFFICIENT_OF_DISPLACEMENT_OF_THE_BRICK = 1.8;

    /**
     * The width of the site which is tied to the width of the pyramid.
     */
    public static final int APPLICATION_WIDTH = ((BRICK_WIDTH + INDENTATION_BETWEEN_THE_BRICKS) *
            BRICKS_IN_BASE + BRICK_WIDTH);

    /**
     * The height of the site which is tied to the height of the pyramid.
     */
    public static final int APPLICATION_HEIGHT = ((BRICK_HEIGHT + INDENTATION_BETWEEN_THE_BRICKS) *
            (BRICKS_IN_BASE + BRICK_HEIGHT));

    /**
     * A method that is the entry point into the program.
     */
    public void run() {
        drawPyramid();
    }

    /**
     * A method that draws a pyramid.
     */
    private void drawPyramid() {
        for (int i = 0; i <= BRICKS_IN_BASE; i++) {
            drawRowOfPyramids(i);
        }
    }

    /**
     * A method that draws rows of a pyramid with an offset.
     */
    private void drawRowOfPyramids(int row) {
        for (int i = 0; i < row; i++) {
            drawBrick(row, i);
        }
    }

    /**
     * A method that bricks offset from each other.
     */
    private void drawBrick(int row, int col) {
        // Variable that regulates the displacement of the brick along the x-axis.
       double x = (getWidth() / 2) - ((((BRICK_WIDTH + INDENTATION_BETWEEN_THE_BRICKS +
                THE_COEFFICIENT_OF_DISPLACEMENT_OF_THE_BRICK) * BRICKS_IN_BASE) -
                INDENTATION_BETWEEN_THE_BRICKS) / 2) + ((BRICK_WIDTH +
                INDENTATION_BETWEEN_THE_BRICKS) * row) -
                ((BRICK_WIDTH / THE_COEFFICIENT_OF_DISPLACEMENT_OF_THE_BRICK) * col);


        // Variable that regulates the displacement of the brick along the y-axis.
        double y = (getHeight() - BRICK_HEIGHT) - ((BRICK_HEIGHT + INDENTATION_BETWEEN_THE_BRICKS)
                * col);

        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(Color.BLACK);
        add(brick);
    }
}
