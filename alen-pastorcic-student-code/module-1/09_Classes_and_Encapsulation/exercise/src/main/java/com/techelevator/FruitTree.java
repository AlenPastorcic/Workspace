package com.techelevator;

public class FruitTree {

    private String typeOfFruit;
    private int piecesOfFruitLeft;

    // Constructor
    public FruitTree(String typeOfFruit, int piecesOfFruitLeft) {
        this.typeOfFruit = typeOfFruit;
        this.piecesOfFruitLeft = piecesOfFruitLeft;
    }

    // Getter and Setter methods for typeOfFruit
    public String getTypeOfFruit() {
        return typeOfFruit;
    }

    public void setTypeOfFruit(String typeOfFruit) {
        this.typeOfFruit = typeOfFruit;
    }

    // Getter and Setter methods for piecesOfFruitLeft
    public int getPiecesOfFruitLeft() {
        return piecesOfFruitLeft;
    }

    public void setPiecesOfFruitLeft(int piecesOfFruitLeft) {
        this.piecesOfFruitLeft = piecesOfFruitLeft;
    }

    // Method to pick fruit from the tree
    public boolean pickFruit(int numberOfPiecesToRemove) {
        if (piecesOfFruitLeft >= numberOfPiecesToRemove) {
            piecesOfFruitLeft -= numberOfPiecesToRemove;
            return true;
        } else {
            return false;
        }
    }

}









