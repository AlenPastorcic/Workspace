package com.techelevator;

public class Application {

	public static void main(String[] args) {
		System.out.println("Welcome to the casino!!!");

		/* Step 1 - We create an object which represents the game and call run method */
		House house = new House();
		house.run();
	}
}
