package com.shpp.p2p.cs.vsamchenko.exam.assignment7;
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    /**
     *
     */
    String name;

    /**
     *
     */
    private final int[] allDecades = new int[NDECADES];

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        // remove name from line
        name = line.substring(0, line.indexOf(' '));
        line = line.substring(line.indexOf(' ') + 1);
        // split remaining line into rank values and save them in array
        String[] splitLine = line.split(" ");
        for (int i = 0; i < NDECADES; i++) {
            allDecades[i] = Integer.parseInt(splitLine[i]);
        }
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return allDecades[decade];
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        int[] decade = new int[NDECADES];
        for (int i = 1; i < NDECADES; i++) {
            decade[i] = getRank(i + 1);
        }

        return getName() + " " + Arrays.toString(decade);
    }
}

