package com.techelevator;
public class Program {

    public static void main(String[] args) {

        int[] diceSizes = {6, 6, 6, 6, 6, 20};
        Game game = new Game(diceSizes);
        game.run();

        System.out.println();
        Game secondGame = new Game(5);
        secondGame.run();
        
    }
}