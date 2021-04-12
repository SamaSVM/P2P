package com.shpp.p2p.cs.vsamchenko.exam.assignment13;

import acm.graphics.GImage;

import java.util.*;

/**
 * The class that processes the picture.
 */
public class ImageProcessor {

    /**
     * Number of silhouettes.
     */
    private static int result = 0;
    /**
     * The image that the program processes.
     */
    private static GImage myImage;
    /**
     * Three-dimensional array of pixels, in the format RGBA.
     */
    private static int[][] pixelsArray;
    /**
     * Silhouette pixel counter.
     */
    private static int counter = 0;
    /**
     * A variable that indicates which size of garbage not to accept.
     */
    private static double uncountableObjects;
    /**
     * A variable that indicates how narrow objects are to crop.
     */
    private static int separationLength;

    /**
     * A private constructor so that objects of this class cannot be created.
     */
    private ImageProcessor() {}

    /**
     * A function that initializes the search for silhouettes.
     */
    static void findSilhouettes(String way) {
        pictureSearch(way);
        arrayFormation();
        initializationOfVariables();
        int backgroundColor = backgroundColor(pixelsArray);
        separation(backgroundColor);
        bfs(backgroundColor);
        System.out.println(result);
    }


    /**
     * A method that separates small objects.
     *
     * @param backgroundColor background color.
     */
    private static void separation(int backgroundColor) {
        int height = pixelsArray.length;
        int width = pixelsArray[0].length;

        for (int i = 0; i < height - separationLength; i++) {
            for (int j = separationLength; j < width - separationLength; j++) {
                // check to the right.
                if (pixelsArray[i][j] == backgroundColor && pixelsArray[i][j + separationLength] == backgroundColor) {
                    for (int k = j; k < j + separationLength; k++) {
                        pixelsArray[i][k] = backgroundColor;
                    }
                }
                //check down.
                if (pixelsArray[i][j] == backgroundColor && pixelsArray[i + separationLength][j] == backgroundColor) {
                    for (int k = i; k < i + separationLength; k++) {
                        pixelsArray[k][j] = backgroundColor;
                    }
                }
                //check right at the bottom.
                if (pixelsArray[i][j] == backgroundColor
                        && pixelsArray[i + separationLength][j + separationLength] == backgroundColor) {
                    int diagonal = j;
                    for (int k = i; k < i + separationLength; k++) {
                        diagonal++;
                        pixelsArray[k][diagonal] = backgroundColor;
                    }
                }
                //check left bottom.
                if (pixelsArray[i][j] == backgroundColor
                        && pixelsArray[i + separationLength][j - separationLength] == backgroundColor) {
                    int diagonal = j;
                    for (int k = i; k < i + separationLength; k++) {
                        diagonal--;
                        pixelsArray[k][diagonal] = backgroundColor;
                    }
                }
            }
        }
    }

    /**
     * A function that compares pixels and performs a breadth-first search.
     */
    private static void bfs(int backgroundColor) {
        int height = pixelsArray.length;
        int width = pixelsArray[0].length;
        ArrayList<int[]> turn = new ArrayList<>();
        boolean[][] verifiedPixels = new boolean[height][width];
        boolean[][] turnPixels = new boolean[height][width];


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (pixelsArray[i][j] != backgroundColor && !verifiedPixels[i][j]) {
                    verifiedPixels[i][j] = true;

                    // step right.
                    if (j != width - 1 && !turnPixels[i][j + 1] &&
                            pixelsArray[i][j + 1] != backgroundColor && !verifiedPixels[i][j + 1]) {
                        turn.add(new int[]{i, j + 1});
                        turnPixels[i][j + 1] = true;
                        counter++;
                    }

                    // step down.
                    if (i != height - 1 && !turnPixels[i + 1][j] &&
                            pixelsArray[i + 1][j] != backgroundColor && !verifiedPixels[i + 1][j]) {
                        turn.add(new int[]{i + 1, j});
                        turnPixels[i + 1][j] = true;
                        counter++;
                    }

                    // step left.
                    if (j != 0 && !turnPixels[i][j - 1] &&
                            pixelsArray[i][j - 1] != backgroundColor && !verifiedPixels[i][j - 1]) {
                        turn.add(new int[]{i, j - 1});
                        turnPixels[i][j - 1] = true;
                        counter++;
                    }

                    if (turn.size() > 0) {
                        i = turn.get(0)[0];
                        j = turn.get(0)[1] - 1;
                        turn.remove(0);
                    } else {
                        objectCounter();
                        i = 0;
                        j = 0;
                    }

                }
            }
        }

    }

    /**
     * A function that analyzes the edges of the image and returns the tone color.
     *
     * @return the tone color.
     */
    private static int backgroundColor(int[][] pixelsArray) {
        int color = 0;
        int height = pixelsArray.length;
        int width = pixelsArray[0].length;
        ArrayList<Integer> backgroundColors = new ArrayList<>();


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    backgroundColors.add(pixelsArray[i][j]);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < backgroundColors.size(); i++) {
            int result = Collections.frequency(backgroundColors, backgroundColors.get(i));
            if (result > count) {
                count = result;
                color = backgroundColors.get(i);
            }
        }

        return color;
    }

    /**
     * A function that checks if an object is large enough and adds a counter.
     */
    private static void objectCounter() {
        if (counter > uncountableObjects) {
            result++;
        }
        counter = 0;
    }

    /**
     * A function that forms a three-dimensional array of pixels.
     */
    private static void arrayFormation() {
        pixelsArray = myImage.getPixelArray();
    }

    /**
     * A function that initializes uncountableObjects and separationLength.
     */
    private static void initializationOfVariables() {
        // objects less than 10 percent of the picture are not taken into account.
        uncountableObjects = (pixelsArray.length * pixelsArray[0].length) * 0.1;
        // objects less than 5 percent of the average height and width are cropped.
        separationLength = (int) ((pixelsArray.length + pixelsArray[0].length) / 2 * 0.05);
    }

    /**
     * A function that initializes an object of class GImage.
     * @param way file path.
     */
    private static void pictureSearch(String way) {
        try {
            myImage = new GImage(way);
        } catch (Exception e) {
            System.err.println("File not found!");
        }
    }
}
