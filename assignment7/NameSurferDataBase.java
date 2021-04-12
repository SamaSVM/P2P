package com.shpp.p2p.cs.vsamchenko.exam.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {
    HashMap<String, NameSurferEntry> result = new HashMap<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            while(true){
                String line = bufferedReader.readLine();
                if(line == null){
                    break;
                }
                line = line.toLowerCase();
                NameSurferEntry entry = new NameSurferEntry(line);
                result.put(entry.getName(), entry);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Failed to read file!");
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
       return result.get(name);
    }
}

