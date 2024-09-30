package com.techelevator;

public class Dice {

    private int numberOfSides;

    public Dice(int numberOfSides) {
        if (numberOfSides < 4) {
            numberOfSides = 4;
        } else if (numberOfSides > 20) {
            numberOfSides = 20;
        }
        this.numberOfSides = numberOfSides;

    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    private int getCurrentFace() {
        return currentFace;
    }

    public int rollDice() {
        return rollDice;

    }
}
