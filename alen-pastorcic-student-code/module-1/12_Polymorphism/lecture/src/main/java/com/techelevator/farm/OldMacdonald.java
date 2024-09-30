package com.techelevator.farm;

public class OldMacdonald {
	public static void main(String[] args) {

		Cow bessie = new Cow();
		Chicken henrietta = new Chicken();
		Pig porky = new Pig();
		Horse nathan = new Horse();
		Cat purr = new Cat();
		Tractor thomas = new Tractor();
		FrontLoader fergie = new FrontLoader();

		Singable[] singables = new Singable[] { bessie, henrietta, porky, nathan, purr, thomas, fergie};

		for (Singable singable : singables) {
			String name = singable.getName();
			String sound = singable.getSound();
			System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
			System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
			System.out.println("With a " + sound + " " + sound + " here");
			System.out.println("And a " + sound + " " + sound + " there");
			System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
			System.out.println();
		}

		henrietta.layEgg();
		System.out.println();
	}
}