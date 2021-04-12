package com.shpp.p2p.cs.vsamchenko.exam.assignment5;

import acm.util.ErrorException;
import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *A program that works with a comma-separated value format (or CSV),
 * where values in a single line are comma-separated or enclosed in quotation marks.
 */
public class Assignment5Part4 extends TextProgram {

    /**
     * The path to the folder where the file is located.
     */
    private static final String FILE_PATH = "assets/";

    /**
     * The name of the file we are reading.
     */
    private static final String FILE_NAME = "filename.csv";

    /**
     * The number of columns in the file.
     */
    private static final int COLUMN_INDEX = 3;

    /**
     * A method that is the entry point into a program.
     */
    public void run() {
        println(extractColumn());
    }

    /**
     * Reads the file and sorts our ArrayList by columns.
     */
    private ArrayList<String> extractColumn() {
        //Read the file, and create a new ArrayList to output the result.
        ArrayList<String> list = fieldsIn();
        ArrayList<String> result = new ArrayList<>();
        //A loop that sorts through each line.
        for (int i = 0; i < list.size(); i++) {
            String x = list.get(i);
            //A loop that scrolls our row to form the column we need.
            for (int j = 0; j <= COLUMN_INDEX; j++) {
                //Search for a punctuation index.
                int quotes = x.indexOf('"');
                int comma = x.indexOf(',');
                if (quotes == -1 && comma == -1){
                    result.add("\"null\"");
                    break;
                }

                //If the value is in quotation marks.
                char[] contents;
                if (quotes == 0){
                    x = x.substring(1);
                    quotes = x.indexOf('"');
                    contents = new char[quotes];
                    x.getChars(0, quotes, contents, 0);
                    String charArray = new String(contents);
                    if (j == COLUMN_INDEX) {
                        result.add(charArray);
                    }
                    x = x.substring(quotes + 2);
                }
                else {
                    contents = new char[comma];
                    x.getChars(0, comma, contents, 0);
                    String charArray = new String(contents);
                    if (j == COLUMN_INDEX) {
                        result.add(charArray);
                    }
                    x = x.substring(comma + 1);
                }
            }
        }
        return result;
    }

    /**
     * Reads the specified file and converts it to ArrayList.
     */
    private ArrayList<String> fieldsIn() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH + FILE_NAME));
            ArrayList<String> result = new ArrayList<>();

            while (true) {
                String contents = bufferedReader.readLine();
                if (contents == null) {
                    break;
                }
                //At the end of the line add a comma to mark the end.
                result.add(contents + ",");
            }
            bufferedReader.close();
            return result;

        } catch (IOException e) {
            throw new ErrorException(e);
        }
    }
}
