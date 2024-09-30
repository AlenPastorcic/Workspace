package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // Properties
    private List<Dice> gameDice = new ArrayList<>();
    private final int STANDARD_DICE_SIZE = 6;

    // Constructors
    public Game(int[] diceSizes) {
        for (int i = 0; i < diceSizes.length; i++) {
            Dice myDice = new Dice(diceSizes[i]);
            myDice.rollDice();
            gameDice.add(myDice);
        }
    }

    public Game(int numberOfDice) {
        for (int i = 0; i < numberOfDice; i++) {
            Dice myDice = new Dice(STANDARD_DICE_SIZE);
            myDice.rollDice();
            gameDice.add(myDice);
        }
    }

    // Methods
    public void run() {
        System.out.println("** Welcome to our amazing dice game **");
        System.out.println("Roll the dice and get " + rollAllDice());
        printGameDice();
    }

    public void printGameDice() {
        for (Dice myDice : gameDice) {
            //System.out.println("Dice with " + myDice.getNumberOfSides() + " sides is currently a " + myDice.getCurrentFace());
            System.out.println("Looking at " + myDice);
        }
    }

    public int rollAllDice() {
        int sumOfAllDice = 0;

        for (Dice myDice : gameDice) {
            sumOfAllDice += myDice.rollDice();
        }

        return sumOfAllDice;
    }

    public int rollAllDice(int maxDice) {
        int sumOfAllDice = 0;

        for (int i = 0; i < gameDice.size() && i < maxDice; i++) {
          Dice myDice = gameDice.get(i);
          sumOfAllDice += myDice.rollDice();
        }

        return sumOfAllDice;
    }

}
