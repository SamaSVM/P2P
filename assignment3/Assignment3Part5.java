package com.shpp.p2p.cs.vsamchenko.exam.assignment3;
import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

/**
 * This is a hypothetical game for a casino with a simple ideology.
 * Two people play: lucky boy and sweaty boy.
 */
public class Assignment3Part5 extends TextProgram {

    /**
     * The game ends when lucky boy earns $ 20 or more.
     */
    private static final int MONEY_FOR_THE_FINISH = 20;

    /**
     * A method that is the entry point into the program.
     */
    public void run() {
     game();
    }

    /**
     * A method that simulates the game of the St. Petersburg game.
     */
    private void game() {
        /*
        Variables that contain player account data and how many games have been played.
         */
        int luckyBoyAccount = 0, sweatyBoyAccount = 1, gameCounter = 0;

            while (luckyBoyAccount < MONEY_FOR_THE_FINISH) {
                /*
                Random Boolean value generator.
                 */
                RandomGenerator randomGenerator = new RandomGenerator();
                boolean eagleOrTail = randomGenerator.nextBoolean();

                /*
                If the eagle - then the sweaty boy adds to the amount on the table exactly the same amount.
                If the tail - everything on the table, goes to lucky boy.
                 */
                if (eagleOrTail) {
                    sweatyBoyAccount = sweatyBoyAccount * 2;
                }else {
                    luckyBoyAccount = luckyBoyAccount + sweatyBoyAccount;
                    println("This game, you earned $" + sweatyBoyAccount);
                    println("Your total is $" + luckyBoyAccount);
                    gameCounter++;
                }
            }
            println("It took " + gameCounter + " games to earn $20");
    }
}