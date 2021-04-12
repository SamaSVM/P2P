package com.shpp.p2p.cs.vsamchenko.exam.assignment6.assignment6Part3;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        // Determine which cells are included in the current column.
        for (int i = 0; i < toneMatrix.length; i++) {
            if (toneMatrix[i][column]) {
                //We combine all the sounds.
                for (int j = 0; j < samples[i].length; j++) {
                    result[j] = result[j] + samples[i][j];
                }
            }
        }
        //Normalize the sound wave for the range [-1.0; 1.0]
        double max = 0;
        double min = 0;
        double totalMax;

        for (double v : result) {
            if (v > max) {
                max = v;
            }
            if (v < min) {
                min = v;
            }
        }
        if (min > -max){
            totalMax = max;
        }
        else if (min < -max){
            totalMax = min;
        }
        else{
            totalMax = max;
        }
        for (int i = 0; i < result.length; i++) {
            if (totalMax != 0) {
                result[i] = result[i] / totalMax;
            }
        }
        return result;
    }
}
