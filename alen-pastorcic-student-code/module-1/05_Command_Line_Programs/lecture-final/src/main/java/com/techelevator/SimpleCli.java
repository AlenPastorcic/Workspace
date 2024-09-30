package com.techelevator;

import java.util.Scanner;

public class SimpleCli {
    public static void main(String[] args) {

        // 1. get info from user
        // 2. do something with the info
        // 3. output something back to the user

        // Standard Streams (abstractions over a lot of complexity):
        // - standard in - a stream that represents keyboard (System.in)
        // - standard out - a stream that represents console/screen (System.out)

        Scanner userInput = new Scanner(System.in);

        // prompt the user for their name
        System.out.println("What is your name?");
        String name = userInput.nextLine();

        System.out.println("what is your favorite color?");
        String color = userInput.nextLine();

        String greeting = "Hello " + name;

        System.out.println(greeting);
        System.out.println(color + " is my favorite also");

    }
}
