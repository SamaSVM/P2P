package com.shpp.p2p.cs.vsamchenko.exam.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

/**
 * A program that draws two beautiful quotes.
 */
public class Assignment2Part3 extends WindowProgram {

    /*
    Constants controlling the relative positions of the
	three toes to the upper-left corner of the pawprint.
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left corner of the pawprint. */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. These constants will tell Java to
     create a window whose size is *approximately* given by these dimensions. */
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /*
     Draws a pawprint. The parameters should specify the upper-left corner of the
     bounding box containing that pawprint.
	 @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
	 @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y) {
        // draw finger 1.
        drawOval(FIRST_TOE_OFFSET_X +x, FIRST_TOE_OFFSET_Y + y, TOE_WIDTH,TOE_HEIGHT);
        // draw finger 2.
        drawOval(SECOND_TOE_OFFSET_X + x, SECOND_TOE_OFFSET_Y + y, TOE_WIDTH,TOE_HEIGHT);
        // draw finger 3.
        drawOval( THIRD_TOE_OFFSET_X + x, THIRD_TOE_OFFSET_Y + y, TOE_WIDTH,TOE_HEIGHT);
        // draw heel.
        drawOval(HEEL_OFFSET_X + x, HEEL_OFFSET_Y + y,  HEEL_WIDTH, HEEL_HEIGHT);
    }

    /**
     * A method that draws a circle.
     *
     * @param x coordinate.
     * @param y coordinate.
     * @param width circle width.
     * @param height circle height.
     */
    private void drawOval(double x, double y, double width, double height) {
        GOval oval = new GOval(x, y, width, height);
        oval.setFilled(true);
        oval.setColor(Color.BLACK);
        add(oval);
    }
}
