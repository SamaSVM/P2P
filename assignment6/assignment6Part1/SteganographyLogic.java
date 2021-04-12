package com.shpp.p2p.cs.vsamchenko.exam.assignment6.assignment6Part1;

import acm.graphics.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.shpp.p2p.cs.vsamchenko.exam.assignment6.assignment6Part1.SteganographyConstants.CANVAS_HEIGHT;
import static com.shpp.p2p.cs.vsamchenko.exam.assignment6.assignment6Part1.SteganographyConstants.CANVAS_WIDTH;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */

    public static boolean[][] findMessage(GImage source) {
        //Create a temporary file.
        source.saveImage(new File("assets/temporaryFile.png"));

        //Read the temporary file.
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("assets/temporaryFile.png"));
        } catch (IOException e) {
            System.err.println("Failed to read file!");
        }

        //Delete the temporary file.
        File temporaryFile = new File("assets/temporaryFile.png");
        temporaryFile.delete();

        boolean[][] result = new boolean[CANVAS_HEIGHT][CANVAS_WIDTH];

        for (int i = 0; i < CANVAS_HEIGHT; i++) {
            for (int j = 0; j < CANVAS_WIDTH; j++) {
                Color red = new Color(bufferedImage.getRGB(j, i));
                result[i][j] = red.getRed() % 2 != 0;
            }
        }
        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        //Create a temporary file.
        source.saveImage(new File("assets/temporaryFile.png"));

        //Read the temporary file.
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("assets/temporaryFile.png"));
        } catch (IOException e) {
            System.err.println("Failed to read file!");
        }

        //Delete the temporary file.
        File temporaryFile = new File("assets/temporaryFile.png");
        temporaryFile.delete();

        //We create a file to record our message.
        File outputFile = new File("assets/outputFile.png");

        for (int i = 0; i < CANVAS_HEIGHT; i++) {
            for (int j = 0; j < CANVAS_WIDTH; j++) {
                bufferedImage.setRGB(j, i, redPixelCorrection(message, bufferedImage, j, i));
            }
        }

        //Write a message to a file.
        try {
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (IOException e) {
            System.err.println("Failed to save file!");
        }
        //Create a GImage to return.
        return new GImage("assets/outputFile.png");
    }


    /**
     * A method that encrypts our message into a picture.
     * @param message We receive our message in the array.
     * @param bi BufferedImage to read the red image.
     * @param col Column.
     * @param row  Line.
     * @return Returns the color we need to insert into the image.
     */
    private static int redPixelCorrection(boolean[][] message, BufferedImage bi, int col, int row) {
        Color color = new Color(bi.getRGB(col, row));
        if (message[row][col]) {
            if (color.getRed() % 2 == 0) {
                Color newColor = new Color(color.getRed() + 1, color.getGreen(), color.getBlue());
                return newColor.getRGB();

            } else {
                Color newColor = new Color(color.getRed(), color.getGreen(), color.getBlue());
                return newColor.getRGB();
            }
        } else {
            if (color.getRed() % 2 == 0) {
                Color newColor = new Color(color.getRed(), color.getGreen(), color.getBlue());
                return newColor.getRGB();
            } else {
                Color newColor = new Color(color.getRed() - 1, color.getGreen(), color.getBlue());
                return newColor.getRGB();
            }
        }
    }
}