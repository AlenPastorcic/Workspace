package com.techelevator;

public class Application {

	public static void main(String[] args) {
		System.out.println("Welcome to the Casino!!!");

		/* Step 1 - We instantiate an object which represents the game and call run method */
		House house = new House();
		house.run();

	}
}
