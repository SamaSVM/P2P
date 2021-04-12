package com.shpp.p2p.cs.vsamchenko.exam.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * A program that depicts an optical illusion, namely a matrix of black boxes separated by "streets".
 */
public class Assignment2Part5 extends WindowProgram {

    /**
     * Set the width of the window.
     */
    public static final int APPLICATION_WIDTH = 500;

    /**
     * Set the height of the window.
     */
    public static final int APPLICATION_HEIGHT = 500;

    /**
     * The number of rows in the grid, respectively.
     */
    private static final int NUM_ROWS = 8;

    /**
     * The number of columns in the grid, respectively.
     */
    private static final int NUM_COLS = 6;

    /**
     *  The width and height of each box.
     */
    private static final double BOX_SIZE = 50;

    /**
     *  The horizontal and vertical spacing between the boxes.
     */
    private static final double BOX_SPACING = 10;

    /**
     * A method that is the entry point into the program.
     */
    public void run() {
        drawIllusion(NUM_ROWS, NUM_COLS);
    }

    /**
     * A function that determines how many lines to draw.
     *
     * @param rows number of rows.
     * @param cols number of cols.
     */
    private void drawIllusion(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            drawIllusionRow(i, cols);
        }
    }

    /**
     * A function that determines how many elements in a row of images.
     *
     * @param rowNumber what line is drawn.
     * @param cols number of cols.
     */
    private void drawIllusionRow(int rowNumber, int cols) {
        for (int i = 0; i < cols; i++) {
            drawIllusionItem(rowNumber, i);
        }
    }

    /**
     * A method that determines the position of a square and draws it.
     *
     * @param rowNumber what line is drawn.
     * @param colNumber what col is drawn.
     */
    private void drawIllusionItem(int rowNumber, int colNumber) {
        /*
        Variable that regulates the position of the box relative to the x-axis.
         */
        double x = ((getWidth() / 2) - (((BOX_SIZE + BOX_SPACING) * NUM_COLS) - BOX_SPACING) / 2)
                + (colNumber * (BOX_SIZE + BOX_SPACING));
        /*
        Variable that regulates the position of the box relative to the y-axis.
         */
        double y = ((getHeight() / 2) - (((BOX_SIZE + BOX_SPACING) * NUM_ROWS) - BOX_SPACING) / 2)
                + (rowNumber * (BOX_SIZE + BOX_SPACING));

        GRect rect = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        rect.setFilled(true);
        rect.setFillColor(Color.BLACK);
        add(rect);
    }
}
