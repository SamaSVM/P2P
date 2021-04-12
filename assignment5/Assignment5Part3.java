package com.shpp.p2p.cs.vsamchenko.exam.assignment5;

import acm.util.ErrorException;
import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A program that will ask the user for a string of three letters
 * and then display words that can be composed of those letters.
 */
public class Assignment5Part3 extends TextProgram {
    /**
     * Specify the path to the word file to be used.
     */
    private static final String READ_FILE = "assets/dictionary.txt";

    /**
     * The number of characters entered by the user.
     */
    private static final int NUMBER_OF_CHARACTERS = 3;

    /**
     * A method that initializes reading a file, selecting the right words, and displaying the result.
     */
    public void run() {
        String userLetters = "";
        ArrayList<String> words = readFile();
        while (true) {
            userLetters = readLine("Enter your letters : ");
            //Displays that message if the user has entered more than three characters.
            if (userLetters.length() > NUMBER_OF_CHARACTERS) {
                println("You have entered more than three letters. Only the first letters will be taken into account.");
            }
            println("Words with your letters : " + wordsSearch(words, userLetters));
        }
    }

    /**
     * We find coincidences of the entered signs with our words.
     *
     * @param words ArrayList of all words in the list.
     * @param userLetters letters entered by the user.
     * @return ArrayList of all that meet our parameters.
     */
    private ArrayList wordsSearch(ArrayList words, String userLetters) {
        userLetters = userLetters.toLowerCase();
        ArrayList<String> appropriateWords = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            String chekWord = (String) words.get(i);
            for (int j = 0; j < chekWord.length(); j++) {
                if (chekWord.charAt(j) == userLetters.charAt(0)) {
                    for (int k = j + 1; k < chekWord.length(); k++) {
                        if (chekWord.charAt(k) == userLetters.charAt(1)) {
                            for (int l = k + 1; l < chekWord.length(); l++) {
                                if (chekWord.charAt(l) == userLetters.charAt(2)) {
                                    appropriateWords.add(chekWord);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return appropriateWords;
    }

    /**
     * Read the file and transfer it to the ArrayList.
     *
     * @return ArrayList of all words in the list.
     */
    private ArrayList<String> readFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(READ_FILE));
            ArrayList<String> result = new ArrayList<>();

            while (true) {
                String title = bufferedReader.readLine();
                if (title == null) {
                    break;
                }
                result.add(title);
            }
            bufferedReader.close();
            return result;

        } catch (IOException e) {
            throw new ErrorException(e);
        }
    }
}
