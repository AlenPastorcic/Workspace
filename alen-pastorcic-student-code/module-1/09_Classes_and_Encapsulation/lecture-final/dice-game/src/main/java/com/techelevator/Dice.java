package com.techelevator;

import java.util.Random;

public class Dice {
    // Properties
    private int numberOfSides;
    private int currentFace;

    // Constructors
    public Dice(int numberOfSides) {
        if (numberOfSides < 4) {
            numberOfSides = 4;
        }
        else if (numberOfSides > 20) {
            numberOfSides = 20;
        }
        this.numberOfSides = numberOfSides;
    }

    // Getters and Setters
    public int getNumberOfSides() {
        return numberOfSides;
    }

    public int getCurrentFace() {
        return currentFace;
    }

    public void setCurrentFace(int currentFace) {
        if (currentFace < 1 || currentFace > numberOfSides) {
            System.out.println("Invalid face to set. Minumum is 1, maximum is " + numberOfSides);
            return;
        }
        this.currentFace = currentFace;
    }

    public String getHighLow() {
        rollDice();
        if (currentFace >= numberOfSides / 2) {
            return "High";
        }
        else {
            return "Low";
        }
    }

    // Other Methods
    public int rollDice() {
        int currentValue = getValidRandomNumber();
        this.currentFace = currentValue;
        return currentValue;
    }

    private int getValidRandomNumber() {
        int currentValue;
        Random random = new Random();
        currentValue = random.nextInt(numberOfSides-1) + 1;
        return currentValue;
    }

    public String toString() {
        return "Dice with " + numberOfSides + " sides is currently a " + currentFace;
    }
}
