package com.shpp.p2p.cs.vsamchenko.exam.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

/**
 * A program that counts the number of vowels in a word, with some exceptions.
 */
public class Assignment5Part1 extends TextProgram {

    /**
     * A method that asks you to enter a word and output the number of syllables.
     */
    public void run() {
        while (true) {
            String word = readLine("Enter a single word: ");
            println("Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        char[] letters = {'a', 'e', 'i', 'o', 'u', 'y'};
        word = lettersIn(word.toLowerCase());
        int numberOfVowels = 0;
        if (word == null) {
            println("It is not string");
            return 0;
        }
        else {
            for (int i = 0; i < word.length(); i++) {
                for (char j : letters) {
                    if (i == word.length() - 1 && word.charAt(i) == j) {
                        numberOfVowels += 1;
                    } else if (word.charAt(i) == j && word.charAt(i + 1) != 'a' && word.charAt(i + 1) != 'e'
                            && word.charAt(i + 1) != 'i' && word.charAt(i + 1) != 'o'
                            && word.charAt(i + 1) != 'u' && word.charAt(i + 1) != 'y') {
                        numberOfVowels += 1;
                    }
                }
            }
            /* Check that the last letter is not "e". */
            if (word.charAt(word.length() - 1) == 'e' && word.charAt(word.length() - 2) != 'a'
                    && word.charAt(word.length() - 2) != 'e' && word.charAt(word.length() - 2) != 'i'
                    && word.charAt(word.length() - 2) != 'o' && word.charAt(word.length() - 2) != 'u'
                    && word.charAt(word.length() - 2) != 'y') {
                numberOfVowels -= 1;
            }
        }
        return Math.max(numberOfVowels, 1);
    }

    /**
     * Bringing what is written to a general form without numbers and signs.
     *
     * @param word the word being checked.
     * @return the word from which all the superfluous was removed.
     */
    private String lettersIn(String word) {
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            char charAt = word.charAt(i);
            if (Character.isLetter(charAt)) {
                result += charAt;
            }
        }
        /* Check that the user writes the word itself. */
        if (result.equals("")) {
            return null;
        } else {
            return result;
        }
    }
}