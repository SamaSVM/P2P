package com.shpp.p2p.cs.vsamchenko.exam.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;


import java.awt.*;
import java.awt.event.MouseEvent;


/**
 * Classic arcade game "Breakout"
 * Colored rectangles at the top of the screen - bricks, black rectangle at the bottom - a racket.
 * The racket is fixed on the vertical axis, but can move freely horizontally,
 * back and forth on the screen following the mouse pointer until it reaches the edge of the screen.
 * The game consists of three attempts.
 * At the beginning of each attempt, the ball is launched at a random angle from the racket.
 * This ball bounces off the edges of the world and from the racket, according to the laws of physics:
 * the angle of incidence is equal to the angle of reflection.
 */
public class Breakout extends WindowProgram {

    /**
     * The amount of time to pause between frames (32fps).
     */
    private static final double PAUSE_TIME = 1000.0 / 32;

    /**
     * Width of application window in pixels.
     */
    public static final int APPLICATION_WIDTH = 500;

    /**
     * Height of application window in pixels.
     */
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;

    /**
     *  Paddle width.
     */
    private static final int PADDLE_WIDTH = 60;

    /**
     * Paddle height.
     */
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NUMBER_OF_BRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NUMBER_OF_BRICKS_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NUMBER_OF_BRICKS_PER_ROW - 1) * BRICK_SEP) / NUMBER_OF_BRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Maximum speed of the ball.
     */
    private static final double MAX_BALL_SPEED = 8.0;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NUMBER_OF_TURNS = 3;

    /**
     * These objects were removed in order to be able to work with them on retry.
     */
    private GRect myPaddle;

    /**
     * The ball with which the program works.
     */
    private GOval myBall;

    /**
     * The message that is displayed on the screen.
     */
    private GLabel label;

    /**
     * To count the amount of bricks left on the screen.
     */
    private static int brickCounter = NUMBER_OF_BRICKS_PER_ROW * NUMBER_OF_BRICKS_ROWS;

    /**
     * To count the remaining attempts.
     */
    private int trialCounter = NUMBER_OF_TURNS;

    /**
     * A method that is the entry point into the program.
     */
    public void run() {
        addBricks();
        game();
    }

    /**
     * A method that runs all the elements of the game in turn.
     */
    private void game() {
        for (int i = 0; i < NUMBER_OF_TURNS; i++) {
            /* Create a paddle and add control to it. */
            myPaddle = makePaddle(Color.BLACK);
            add(myPaddle);
            addMouseListeners();

            /* Add a ball, and realize the bounce of the ball from the sides of the window.*/
            myBall = makeBall(Color.BLACK);
            add(myBall);

            /* The game or attempt starts only after clicking the mouse button. */
            waitForClick();
            /* Remove the caption between attempts. */
            if (trialCounter < NUMBER_OF_TURNS) {
                remove(label);
            }
            /* Add the reflection of the ball. */
            bounceBall(myBall);

            /* Determine from which objects the ball will come. */
            getCollidingObject();
            trialCounter--;
        }
    }

    /**
     * Add bricks to the screen.
     */
    private void addBricks() {
        for (int i = 0; i < NUMBER_OF_BRICKS_PER_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_BRICKS_ROWS; j++) {
                drawBrick(j, i);
            }
        }
    }

    /**
     * A method of drawing bricks on a field.
     *
     * @param x number of lines.
     * @param y number of columns.
     */
    private void drawBrick(int x, int y) {
        /* Brick centering factor. */
        int bricksCentering = 0;
        if (x == 0) {
            bricksCentering = BRICK_SEP / 2;
        }
        GRect brick = new GRect(bricksCentering + x * (BRICK_WIDTH + BRICK_SEP),
                BRICK_Y_OFFSET + (y * (BRICK_HEIGHT + BRICK_SEP)),
                BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        if (y < 2) {
            brick.setFillColor(Color.RED);
        } else if (y < 4) {
            brick.setFillColor(Color.ORANGE);
        } else if (y < 6) {
            brick.setFillColor(Color.YELLOW);
        } else if (y < 8) {
            brick.setFillColor(Color.GREEN);
        } else {
            brick.setFillColor(Color.CYAN);
        }
        add(brick);
    }

    /**
     * A method that determines whether our ball has collided with other objects.
     *
     * @return the object the ball collided with.
     */
    private GObject getCollidingObject() {
        /* To determine from which objects our ball is reflected. */
        GObject collidingObject;
        /* Variables to reduce the record of formulas. */
        double x = myBall.getX();
        double y = myBall.getY();

        if (getElementAt(x, y) != null) {
            collidingObject = getElementAt(x, y);
        } else if (getElementAt(x + (2 * BALL_RADIUS), y) != null) {
            collidingObject = getElementAt(x + (2 * BALL_RADIUS), y);
        } else if (getElementAt(x, y + (2 * BALL_RADIUS)) != null) {
            collidingObject = getElementAt(x, y + (2 * BALL_RADIUS));
        } else if (getElementAt(x + (2 * BALL_RADIUS), y + (2 * BALL_RADIUS)) != null) {
            collidingObject = getElementAt(x + (2 * BALL_RADIUS), y + (2 * BALL_RADIUS));
        } else {
            collidingObject = null;
        }
        return collidingObject;
    }


    /**
     * A function that controls the reflection of the ball from the walls of the wick
     * and from other objects.
     *
     * @param myBall object to which motion is given.
     */
    private void bounceBall(GOval myBall) {
        /* To assign a certain ball movement. */
        double vx;
        double vy;
        RandomGenerator randomGenerator = RandomGenerator.getInstance();
        vx = randomGenerator.nextDouble(1.0, 3.0);
        vy = MAX_BALL_SPEED;
        if (randomGenerator.nextBoolean(0.5)) {
            vx = -vx;
        }
        while (true) {
            myBall.move(vx, vy);
            GObject collider = getCollidingObject();

            if (collider != myPaddle && collider != null) {
                brickCounter--;
                remove(collider);
                vy = -vy;
                if (brickCounter == 0) {
                    trialCounter = 0;
                    removeAll();
                    addLabel("You Win!");
                    break;
                }
            }
            if (collider == myPaddle) {
                vy = -vy;
            }
            if (ballBelowFloor(myBall)) {
                addLabel("Next try!");
                remove(myPaddle);
                remove(myBall);
                break;
            }
            if (ballOverTheCeiling(myBall)) {
                vy = -vy;
            }
            if (ballBehindTheRightWall(myBall)) {
                vx = -vx;
            }
            if (ballBehindTheLeftWall(myBall)) {
                vx = -vx;
            }
            pause(PAUSE_TIME);
        }
    }

    /**
     * The method that displays the message.
     *
     * @param text the text to be displayed.
     */
    private void addLabel(String text) {
        if (trialCounter == 1) {
            text = "Game Over!";
        }
        /* Since the label's inscriptions are always different,
         50 and 40 are correction factors for centering the label. */
        label = new GLabel(text,
                getWidth() / 2 - 50,
                getHeight() / 2 - 40);
        label.setFont("Verdana-30");
        add(label);
    }

    /**
     * Shows when the ball flies out of the window.
     */
    private boolean ballBehindTheLeftWall(GOval myBall) {
        return myBall.getX() <= 0;
    }

    /**
     * Shows when the ball flies out of the window.
     */
    private boolean ballBehindTheRightWall(GOval myBall) {
        return myBall.getX() + myBall.getWidth() >= getWidth();
    }

    /**
     * Shows when the ball flies out of the window.
     */
    private boolean ballOverTheCeiling(GOval myBall) {
        return myBall.getY() <= 0;
    }

    /**
     * Shows when the ball flies out of the window.
     */
    private boolean ballBelowFloor(GOval myBall) {
        return myBall.getY() >= getHeight();
    }

    /**
     * Create a ball of the desired size in the middle of the window.
     *
     * @param color object color.
     * @return object that was created.
     */
    private GOval makeBall(Color color) {
        GOval ball = new GOval(getWidth() / 2 - BALL_RADIUS,
                getHeight() / 2 - BALL_RADIUS,
                BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setFillColor(color);
        return ball;
    }

    /**
     * A method that makes our paddle move along the X-axis.
     */
    @Override
    public void mouseMoved(MouseEvent me) {
        /*
         * Create a racket capture with your mouse.
         */
        GObject selectedObject;
        /*
         Determine that our control object is always in a specific Y-axis
         */
        selectedObject = getElementAt(me.getX(), getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
        super.mouseMoved(me);

        /*
         If the mouse cursor moves away from the middle of the paddle, it follows the cursor.
         Direction to the right.
         */
        if (selectedObject != null
                && me.getX() > myPaddle.getX() + myPaddle.getWidth() / 2
                && (me.getX() < getWidth() - myPaddle.getWidth() / 2)) {
            /* The Y-axis object does not move, and the X-axis moves at the speed of the mouse cursor. */
            selectedObject.move(me.getX() - (myPaddle.getX() + myPaddle.getWidth() / 2), 0);
        }
        /*
         If the mouse cursor moves away from the middle of the paddle, it follows the cursor.
         Direction to the left.
         */
        if (selectedObject != null
                && me.getX() < myPaddle.getX() + myPaddle.getWidth() / 2
                && (me.getX() > myPaddle.getWidth() / 2)) {
            /* The Y-axis object does not move, and the X-axis moves at the speed of the mouse cursor. */
            selectedObject.move(me.getX() - (myPaddle.getX() + myPaddle.getWidth() / 2), 0);
        }
    }

    /**
     * The method that draws our paddle.
     */
    private GRect makePaddle(Color color) {
        GRect paddle = new GRect(
                getWidth() / 2 - PADDLE_WIDTH / 2,
                getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET,
                PADDLE_WIDTH,
                PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setColor(color);
        return (paddle);
    }
}