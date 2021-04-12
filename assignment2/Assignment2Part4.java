package com.shpp.p2p.cs.vsamchenko.exam.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 *
 */
public class Assignment2Part4 extends WindowProgram {

    /**
     * The size of the flag is 2:3, as stated in the French constitution.
     * Flag width.
     */
    private static final double FLAG_WIDTH = 300;

    /**
     * The size of the flag is 2:3, as stated in the French constitution.
     * Flag height.
     */
    private static final double FLAG_HEIGHT = FLAG_WIDTH / 1.5;

    /**
     *
     */
    public void run() {
        /*
        Colors reproduced from the source:
        https://en.wikipedia.org/wiki/Flag_of_France
         */
        drawRectangle(
                (getWidth() / 2) - FLAG_HEIGHT - (FLAG_HEIGHT / 2),(getHeight() / 2) - (FLAG_WIDTH / 2),
                new Color(0, 85, 164));
        drawRectangle((getWidth() / 2) - (FLAG_HEIGHT / 2), (getHeight() / 2) - (FLAG_WIDTH / 2),
                Color.WHITE);
        drawRectangle((getWidth() / 2) + (FLAG_HEIGHT / 2),(getHeight() / 2) - (FLAG_WIDTH / 2),
                new Color(250, 60, 50));
        drawLabel("Flag of France");
    }

    private void drawRectangle(double x, double y, Color color) {
        GRect rect = new GRect(x, y, FLAG_HEIGHT, FLAG_WIDTH);
        rect.setFilled(true);
        rect.setColor(color);
        add (rect);
    }

    private void drawLabel(String nameOfCountry) {
        GLabel nameCountry = new GLabel(nameOfCountry,getWidth() - 240,getHeight() - 20);
        nameCountry.setFont("Verdana-30");
        add (nameCountry);
    }
}
