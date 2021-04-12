package com.shpp.p2p.cs.vsamchenko.exam.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * A program that collects data about a user's physical activity
 * and displays whether the user is engaged for a long time.
 */
public class Assignment3Part1 extends TextProgram {
    /**
     * Counter of days when the user performed enough minutes of aerobics for cardiovascular health.
     */
    private int counterCardiovascularHealth = 0;

    /**
     * A counter of the days when the user did enough minutes of aerobics for blood pressure.
     */
    private int counterBloodPressure = 0;

    /**
     * User tracking period (days).
     */
    private static final int TRACKING_PERIOD = 7;

    /**
     * Doctors recommend 30 minutes of aerobics five days a week to maintain your cardiovascular health.
     */
    private static final int DAYS_OF_AEROBICS_FOR_CARDIOVASCULAR = 5;

    /**
     * Doctors recommend 40 minutes of aerobics three times a week to keep blood pressure low.
     */
    private static final int DAYS_OF_AEROBICS_FOR_BLOOD_PRESSURE = 3;

    /**
     * Doctors recommend 30 minutes of aerobics five days a week to maintain your cardiovascular health.
     */
    private static final int MINUTES_OF_AEROBICS_FOR_CARDIOVASCULAR = 30;

    /**
     * Doctors recommend 40 minutes of aerobics three times a week to keep blood pressure low.
     */
    private static final int MINUTES_OF_AEROBICS_FOR_BLOOD_PRESSURE = 40;

    /**
     * A method that is the entry point into the program.
     */
    public void run() {
        questionOfIndicators();
        outputOfTheResult();
    }

    /**
     * Displays the result according to the data entered by the user.
     */
    private void outputOfTheResult() {
        if (counterCardiovascularHealth >= DAYS_OF_AEROBICS_FOR_CARDIOVASCULAR) {
            println("Great job! You've done enough exercise for cardiovascular health.");
        } else {
            println("You needed to train hard for at least " +
                    (DAYS_OF_AEROBICS_FOR_CARDIOVASCULAR - counterCardiovascularHealth) + " more day(s) a week!");
        }
        if (counterBloodPressure >= DAYS_OF_AEROBICS_FOR_BLOOD_PRESSURE) {
            println("Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println("You needed to train hard for at least " + (DAYS_OF_AEROBICS_FOR_BLOOD_PRESSURE - counterBloodPressure) +
                    " more day(s) a week!");
        }
    }

    /**
     * Poll of the user on what days he played sports.
     */
    private void questionOfIndicators() {
        int time;

        for (int i = 0; i < TRACKING_PERIOD; i++) {
            time = readInt("How many minutes did you do on day " + (1 + i) + "?");
            if (time >= MINUTES_OF_AEROBICS_FOR_BLOOD_PRESSURE) {
                counterBloodPressure++;
            }
            if (time >= MINUTES_OF_AEROBICS_FOR_CARDIOVASCULAR) {
                counterCardiovascularHealth++;
            }
        }
    }
}