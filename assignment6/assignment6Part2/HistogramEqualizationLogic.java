package com.shpp.p2p.cs.vsamchenko.exam.assignment6.assignment6Part2;

public class HistogramEqualizationLogic {

    /**
     * Maximum brightness of pictures.
     */
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminance of the pixels in an image, returns a histogram of the frequencies of
     * those luminance.
     * <p/>
     * You can assume that pixel luminance range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminance The luminance in the picture.
     * @return A histogram of those luminance.
     */
    public static int[] histogramFor(int[][] luminance) {
        int result [] = new int[MAX_LUMINANCE + 1];
        for (int i = 0; i < luminance.length; i++){
            for (int j = 0; j < luminance[i].length; j++){
                result[luminance[i][j]] += 1;
            }
        }
        return result;
    }

    /**
     * Given a histogram of the luminance in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
		int result [] = new int[MAX_LUMINANCE + 1];
		    for (int i = 0; i <= MAX_LUMINANCE; i++){
		        if (i == 0){
                    result[i] = histogram[i];
                }
		        else {
                    result[i] = histogram[i] + result[i - 1];
                }
            }
        return result;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminance A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminance) {
		return luminance.length * luminance[0].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminance The luminance of the input image.
     * @return The luminance of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminance) {
		int [][] result = new int[luminance.length][luminance[0].length];
        int [] cumulativeHistogram = cumulativeSumFor(histogramFor(luminance));
        for (int i = 0; i < luminance.length; i++){
            for (int j = 0; j < luminance[0].length; j++){
                int newLuminance = MAX_LUMINANCE * cumulativeHistogram[luminance[i][j]] / totalPixelsIn(luminance);
                result[i][j] = newLuminance;
            }
        }
        return result;
    }
}
