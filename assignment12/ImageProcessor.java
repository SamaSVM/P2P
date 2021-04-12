package com.shpp.p2p.cs.vsamchenko.exam.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * The class that processes the picture.
 */
public class ImageProcessor {
    /**
     * The stack in which all points of the graph which we already bypassed are stored.
     */
    private static final Stack<int[]> stackPixels = new Stack<>();
    /**
     * The stack in which all points of the graph which we will bypass are stored.
     */
    private static final Stack<int[]> stackSteps = new Stack<>();
    /**
     * Three-dimensional array of pixels, in the format RGBA.
     */
    private static int[][][] pixelArray;
    /**
     * Silhouette pixel counter.
     */
    private static int counter = 0;
    /**
     * Number of silhouettes.
     */
    private static int result = 0;

    private static BufferedImage myBufferedImage;
    /**
     * A constant that indicates which size of garbage not to accept.
     */
    private static final int UNCOUNTABLE_OBJECTS = 150;

    /**
     * A private constructor so that objects of this class cannot be created.
     */
    private ImageProcessor(){}

    /**
     * A function that initializes the search for silhouettes.
     */
    static void findSilhouettes(String way) {
        arrayFormation(way);

        int[] backgroundColor = backgroundColor();
        arrayAnalysis(backgroundColor);
        System.out.println(result);
    }

    /**
     * A function that compares pixels and performs a Depth-first search.
     */
    private static void arrayAnalysis(int[] backgroundColor) {
        for (int i = 0; i < myBufferedImage.getHeight(); i++) {
            for (int j = 0; j < myBufferedImage.getWidth(); j++) {
                if (!Arrays.equals(backgroundColor, pixelArray[i][j])
                        && !stackPixels.contains(pixelArray[i][j])) {
                    dfs(i, j);
                    objectCounter();
                }
            }
        }
    }

    /**
     * A function that analyzes the edges of the image and returns the tone color.
     *
     * @return the tone color.
     */
    private static int[] backgroundColor() {
        ArrayList<int[]> backgroundColors = new ArrayList<>();

        int[] returnColor = new int[4];

        for (int i = 0; i < myBufferedImage.getHeight(); i++) {
            for (int j = 0; j < myBufferedImage.getWidth(); j++) {
                if (i == 0 || i == myBufferedImage.getHeight() - 1 || j == 0 || j == myBufferedImage.getWidth() - 1) {
                    backgroundColors.add(pixelArray[i][j]);
                }
            }
        }
        int maxPixels = 0;
        for (int i = 0; i < backgroundColors.size(); i++) {
            int[] x = backgroundColors.get(i);
            int counter = 0;
            for (int[] backgroundColor : backgroundColors) {
                if (Arrays.equals(x, backgroundColor)) {
                    counter++;
                }
            }
            if (counter > maxPixels) {
                maxPixels = counter;
                returnColor = backgroundColors.get(i);
            }
        }

        return returnColor;
    }

    /**
     * A function that checks if an object is large enough and adds a counter.
     */
    private static void objectCounter() {
        if (counter > UNCOUNTABLE_OBJECTS) {
            result++;
        }
        counter = 0;
    }

    /**
     * The function that performs the Depth-first search.
     *
     * @param width  x is the pixel coordinate of an object that is different from the background.
     * @param height y is the pixel coordinate of an object that is different from the background.
     */
    private static void dfs(int width, int height) {
        // check for missing pixels in the stack
        if (!stackPixels.contains(pixelArray[width][height])) {
            stackPixels.push(pixelArray[width][height]);
            counter++;
            int[] step = {width, height};
            stackSteps.push(step);
        }
        // step to the right
        if (height != myBufferedImage.getWidth() - 1) {
            step(width, height, width, height + 1);
        }

        // step up
        if (width != 0) {
            step(width, height, width - 1, height);
        }

        // step to the left
        if (height != 0) {
            step(width, height, width, height - 1);
        }

        // step down
        if (width != myBufferedImage.getHeight() - 1) {
            step(width, height, width + 1, height);
        }

        // step back
        if (stackSteps.size() != 0) {
            int[] lastStep = stackSteps.pop();
            dfs(lastStep[0], lastStep[1]);
        }
    }

    /**
     * A function that implements a step in the specified direction.
     *
     * @param width      initial x coordinate.
     * @param height     initial y coordinate.
     * @param moveWidth  x coordinate where you want to move.
     * @param moveHeight y coordinate where you want to move.
     */
    private static void step(int width, int height, int moveWidth, int moveHeight) {
        if (Arrays.equals(pixelArray[width][height], pixelArray[moveWidth][moveHeight])
                && !stackPixels.contains(pixelArray[moveWidth][moveHeight])) {
            dfs(moveWidth, moveHeight);
        }
    }

    /**
     * A function that forms a three-dimensional array of pixels.
     *
     * @param way way to the picture.
     */
    private static void arrayFormation(String way) {
        try {
            myBufferedImage = ImageIO.read(new File(way));
        } catch (IOException e) {
            System.err.println("There is no such file.");
        }

        pixelArray = new int[myBufferedImage.getHeight()][myBufferedImage.getWidth()][4];
        for (int i = 0; i < myBufferedImage.getWidth(); i++) {
            for (int j = 0; j < myBufferedImage.getHeight(); j++) {
                Color currentPixel = new Color(myBufferedImage.getRGB(i, j), true);
                pixelArray[j][i][0] = currentPixel.getRed();
                pixelArray[j][i][1] = currentPixel.getGreen();
                pixelArray[j][i][2] = currentPixel.getBlue();
                pixelArray[j][i][3] = currentPixel.getAlpha();
            }
        }
    }
}