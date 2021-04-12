package com.shpp.p2p.cs.vsamchenko.exam.assignment3;

import acm.graphics.GLabel;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This program creates an animation that lasts 5 seconds.
 */
public class Assignment3Part6 extends WindowProgram {

    /**
     * Regulation of fps
     */
    private static final double FPS = 24;

    /**
     * The amount of time to pause between frames (24fps).
     */
    private static final double PAUSE_TIME = 1000.0 / FPS;

    /**
     *  Window width.
     */
    public static final int APPLICATION_WIDTH = 800;
    
    /**
     * Window height.
     */
    public static final int APPLICATION_HEIGHT = 500;

    /**
     * The duration of the initial animation.
     */
    private static final int SECONDS_FIRST_ANIMATION = 4;

    /**
     * A method that is the entry point into the program.
     */
    public void run() {
        pause(5000);
        startAnimation(0, 50);
        finishAnimation();
    }

    /**
     * A function that displays a large logo that changes its color.
     */
    private void finishAnimation() {
        GLabel logo1 = makeLogo("Ш", Color.BLACK, "Verdana-180",
                ((getWidth() / 2) - 180), getHeight() / 2 + 60);
        add(logo1);
        GLabel logo2 = makeLogo("++", Color.GREEN, "Verdana-180",
                 getWidth() / 2, getHeight() / 2 + 60);
        add(logo2);
        changeColorOfLogo(logo1, logo2);

    }

    /**
     * A function that forces the logo to change its color.
     */
    private void changeColorOfLogo(GLabel logo1, GLabel logo2) {
        RandomGenerator randomGenerator = RandomGenerator.getInstance();
        /* A cycle that lasts about a second. */
        for (int i = 0; i < FPS / 2; i++) {
            logo1.setColor(randomGenerator.nextColor());
            logo2.setColor(randomGenerator.nextColor());
            pause(FPS * SECONDS_FIRST_ANIMATION);
        }
        removeAll();
    }

    /**
     * A function that displays the first part of the animation and makes it move.
     */
    private void startAnimation(int x, int y) {
        GLabel logo1 = makeLogo("Ш", Color.BLACK, "Verdana-80", x, y);
        add(logo1);
        GLabel logo2 = makeLogo("++", Color.GREEN, "Verdana-80",
                (int) (x + logo1.getWidth()), y);
        add(logo2);
        bounceLogo(logo1, logo2);
        removeAll();
    }

    /**
     * A function that makes the logo move and prevents it from leaving the program window area.
     */
    private void bounceLogo(GLabel logo1, GLabel logo2) {

        double dx = 10;
        double dy = 10;

        /* A cycle that lasts about 4 seconds. */
        for (int i = 0; i < FPS * SECONDS_FIRST_ANIMATION; i++) {

            logo1.move(dx, dy);
            logo2.move(dx, dy);

            if (logoBelowFloor(logo1)) {
                dy = -dy;
            }
            if (logoOverTheCeiling(logo1)) {
                dy = Math.pow(dy, 2);
                dy = Math.sqrt(dy);
            }
            if (logoBehindTheRightWall(logo2)) {
                dx = -dx;
            }
            if (logoBehindTheLeftWall(logo1)) {
                dx = Math.pow(dx, 2);
                dx = Math.sqrt(dx);
            }

            pause(PAUSE_TIME);
        }

    }

    /**
     * A variable that does not allow the logo to disappear behind the left wall of the program.
     */
    private boolean logoBehindTheLeftWall(GLabel logo1) {
        return logo1.getX() <= 0;
    }

    /**
     * A variable that does not allow the logo to disappear behind the right wall of the program.
     */
    private boolean logoBehindTheRightWall(GLabel logo2) {
        return logo2.getX() + logo2.getWidth() >= getWidth();
    }

    /**
     * A variable that does not allow the logo to disappear
     * floor of the program.
     */
    private boolean logoBelowFloor(GLabel logo1) {
        return logo1.getY() >= getHeight();
    }

    /**
     * A variable that does not allow the logo to disappear
     * ceiling program.
     */
    private boolean logoOverTheCeiling(GLabel logo1) {
        return logo1.getY() - (logo1.getHeight() / 2) <= 0;
    }

    /**
     * A function that creates an inscription for display. A certain size, font, color, and position.
     */
    private GLabel makeLogo(String text, Color color, String type, int x, int y) {
        GLabel logo = new GLabel(text, x, y);
        logo.setFont(type);
        logo.setColor(color);
        return logo;
    }
}
