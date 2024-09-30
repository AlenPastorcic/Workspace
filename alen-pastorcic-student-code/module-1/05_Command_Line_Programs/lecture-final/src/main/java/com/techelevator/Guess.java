package com.techelevator;

import java.util.Scanner;

public class Guess {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        // pick a secret number
        int secret = (int) ((Math.random() * 100) + 1); // 1-100

        // prompt the user for their guess
        boolean keepAsking = true;
        while (keepAsking) {
            System.out.println("Pick a number between 1 and 100");
            String input = userInput.nextLine();
            int guess = Integer.parseInt(input);
            if (guess < secret) {
                System.out.println("guess higher");
            } else if (guess > secret) {
                System.out.println("guess lower");
            } else {
                System.out.println("CORRECT!");
                keepAsking = false;
            }
        }

    }
}
