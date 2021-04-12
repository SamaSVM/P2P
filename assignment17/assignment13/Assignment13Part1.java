package com.shpp.p2p.cs.vsamchenko.exam.assignment17.assignment13;

/**
 * Program for counting silhouettes in the picture.
 */
class Assignment13Part1 {

    /**
     * A method that checks whether any parameters are passed
     * to the input and counts the number of silhouettes.
     *
     * @param args the name of the picture on which the number of silhouettes is counted.
     *             Located in the folder "assets".
     */
    public static void main(String[] args) {
        String fileWay = Checker.checkInputParameters(args);
        ImageProcessor.findSilhouettes(fileWay);
    }
}
