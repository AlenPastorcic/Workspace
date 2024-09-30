package com.techelevator;

import java.util.ArrayList;
import java.util.List;
public class game {

    List<Dice> gameDice = new ArrayList<>();

    public game(int[] diceSizes) {
        for (int i= 0;i <= diceSizes.length; i++ ) {
            Dice myDice = new Dice(diceSizes[i]);
            myDice.rollDice();
            gameDice.add(myDice);
        }
    }
    public void run() {
        System.out.println("Welcome to our amazing game of dice");
        Dice myDice = new Dice(4);
        System.out.println("My one dice has the value of " + myDice.getNumberOfSides());
    }
}
