package com.shpp.p2p.cs.vsamchenko.exam.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * A program that draws an optical illusion with 4 circles at the corners and a rectangle in the middle.
 */
public class Assignment2Part2 extends WindowProgram {
    /**
     * Set the width of the window
     */
    public static final int APPLICATION_WIDTH = 500;
    /**
     * Set the height of the window
     */
    public static final int APPLICATION_HEIGHT = 500;
    /**
     * Set the diameter of the circle
     */
    private static final int DIAMETER = 150;

    /**
     * The method that runs the program.
     */
    public void run() {
        drawCircles();
        drawRectangle();
    }

    /**
     * A method that draws a rectangle in the middle.
     */
    private void drawRectangle() {
        GRect rect = new GRect(DIAMETER / 2, DIAMETER / 2, getWidth() - DIAMETER, getHeight() - DIAMETER);
        rect.setFilled(true);
        rect.setColor(Color.white);
        add(rect);
    }

    /**
     * A method that indicates at which points to draw circles.
     */
    private void drawCircles() {
        drawCircle(0, 0);
        drawCircle(getWidth() - DIAMETER, getHeight() - DIAMETER);
        drawCircle(0, getHeight() - DIAMETER);
        drawCircle(getWidth() - DIAMETER, 0);
    }

    /**
     * A method that draws circles in given coordinates.
     *
     * @param x coordinate.
     * @param y coordinate.
     */
    private void drawCircle(int x, int y) {
        GOval oval = new GOval(x, y, DIAMETER, DIAMETER);
        oval.setFilled(true);
        oval.setColor(Color.BLACK);
        add(oval);
    }
}