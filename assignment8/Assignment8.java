package com.shpp.p2p.cs.vsamchenko.exam.assignment8;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A program that draws on the screen a certain number of black color balls.
 * If you press the ball, it will start moving along the x-axis.
 * If you click on a ball that is already moving along the x-axis,
 * it will change color and start moving along the y-axis.
 */
public class Assignment8 extends WindowProgram {

    /**
     * Width of application window in pixels.
     */
    public static final int APPLICATION_WIDTH = 260;

    /**
     * Height of application window in pixels.
     */
    public static final int APPLICATION_HEIGHT = 280;

    /**
     * The diameter of the balls that are created.
     */
    private static final int BALL_DIAMETER = 50;

    /**
     * Number of balls created.
     */
    private static final int NUMBER_BALLS = 5;

    /**
     * The amount of time to pause between frames (32fps).
     */
    private static final double PAUSE_TIME = 1000.0 / 32;

    /**
     * Number of balls that can move simultaneously.
     */
    private static final int NUMBER_OF_MOVING_OBJECTS = 3;

    /**
     * Random color generator.
     */
    private static final RandomGenerator randomGenerator = new RandomGenerator();

    /**
     * ArrayList of objects to be moved.
     */
    ArrayList<GObject> myObjects = new ArrayList<>(NUMBER_OF_MOVING_OBJECTS);

    /**
     * ArrayList which stores BounceBall's/
     */
    ArrayList<BounceBall> arrayBalls = new ArrayList<>(NUMBER_OF_MOVING_OBJECTS);

    /**
     * A method that creates circles and adds a mouse listener.
     */
    public void run() {
        makeCircles();
        addMouseListeners();
    }

    /**
     * A method that creates circles diagonally, depending on their number.
     */
    private void makeCircles() {
        for (int i = 0; i < NUMBER_BALLS; i++) {
            GOval oval = new GOval(BALL_DIAMETER * i, (getHeight() - BALL_DIAMETER) - BALL_DIAMETER * i,
                    BALL_DIAMETER, BALL_DIAMETER);
            oval.setFilled(true);
            add(oval);
        }
    }

    /**
     * A method that adds a new object to the screen when you click on it.
     */
    public void mouseClicked(MouseEvent e) {
        /* Create a racket capture with your mouse. */
        GObject selectedObject = getElementAt(e.getX(), e.getY());

        if (selectedObject != null) {
            if (myObjects.contains(selectedObject)) {
                selectedObject.setColor(randomGenerator.nextColor());
                int index = searchIndexBall(selectedObject);
                BounceBall actualBall = arrayBalls.get(index);
                actualBall.setVX(0);
                actualBall.setVY(-1);
            } else {
                myObjects.add(selectedObject);
                addNewBall();
            }

            if (myObjects.size() > NUMBER_OF_MOVING_OBJECTS) {
                myObjects.remove(0);
                removeMyBall();
            }
        }
    }

    /**
     * The method that determines the index of the object clicked by the user.
     *
     * @param selectedObject object clicked by the user.
     * @return index at which position the object is located.
     */
    private int searchIndexBall(GObject selectedObject) {
        for (int i = 0; i < myObjects.size(); i++) {
            if (selectedObject.equals(myObjects.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * A method that removes a ball from the list and stops it.
     */
    private void removeMyBall() {
        BounceBall myBall = arrayBalls.remove(0);
        myBall.stop();
    }

    /**
     * A method that adds a ball to a list and starts it moving.
     */
    private void addNewBall() {
        BounceBall myBall = new BounceBall();
        arrayBalls.add(myBall);
        myBall.start();
    }


    /** Shows when the ball flies out of the window. */
    private boolean ballBehindTheLeftWall(GOval myBall) {
        return myBall.getX() <= 0;
    }

    /** Shows when the ball flies out of the window. */
    private boolean ballBehindTheRightWall(GOval myBall) {
        return myBall.getX() + myBall.getWidth() >= getWidth();
    }

    /** Shows when the ball flies out of the window. */
    private boolean ballOverTheCeiling(GOval myBall) {
        return myBall.getY() <= 0;
    }

    /** Shows when the ball flies out of the window. */
    private boolean ballBelowFloor(GOval myBall) {
        return myBall.getY() + myBall.getHeight() >= getHeight();
    }

    /**
     * The class of our moving object.
     */
    private class BounceBall extends Thread {

        /**
         * The direction of movement on the x-axis.
         */
        private int vx = 1;

        /**
         * The direction of movement along the y-axis.
         */
        private int vy = 0;


        /**
         * A method that changes the direction of motion along the x-axis.
         *
         * @param vx the direction of movement on the x-axis.
         */
        void setVX(int vx) {
            this.vx = vx;
        }

        /**
         * A method that changes the direction of motion along the y-axis.
         *
         * @param vy the direction of movement along the y-axis.
         */
        void setVY(int vy) {
            this.vy = vy;
        }

        /**
         *
         */
        public void run() {
            GOval myBall = (GOval) myObjects.get(myObjects.size() - 1);

            while (true) {
                myBall.move(vx, vy);

                if (ballOverTheCeiling(myBall)) {
                    vy = -vy;
                }
                if (ballBehindTheRightWall(myBall)) {
                    vx = -vx;
                }
                if (ballBehindTheLeftWall(myBall)) {
                    vx = -vx;
                }
                if (ballBelowFloor(myBall)) {
                    vy = -vy;
                }
                pause(PAUSE_TIME);
            }
        }
    }
}
