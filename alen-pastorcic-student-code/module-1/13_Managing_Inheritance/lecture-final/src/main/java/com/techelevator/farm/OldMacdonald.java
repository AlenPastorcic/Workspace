package com.techelevator.farm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OldMacdonald {
	public static void main(String[] args) {

		Cow bessie = new Cow();
		Chicken henrietta = new Chicken();
		Pig porky = new Pig();
		Horse nathan = new Horse();
		Cat purr = new Cat();
		Pig charlize = new Pig();
		Tractor thomas = new Tractor();
		FrontLoader fergie = new FrontLoader();
//		FarmAnimal artie = new FarmAnimal("Aardvark", "snuffle!");
//		FarmAnimal qwerty = new FarmAnimal("Qwezzlebump", "snerg!");
		nathan.sleep();
		purr.sleep();

		Singable[] singables = new Singable[] { bessie, henrietta, porky, nathan, purr, charlize, thomas, fergie };
		FarmAnimal[] animals = new FarmAnimal[] { bessie, henrietta, porky, nathan, purr, charlize };

//		for (Singable singable : singables) {
//			String name = singable.getName();
//			String sound = singable.getSound();
//			System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
//			System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
//			System.out.println("With a " + sound + " " + sound + " here");
//			System.out.println("And a " + sound + " " + sound + " there");
//			System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
//			System.out.println();
//		}

		for (Singable singable : singables) {
			singAboutMe(singable);
		}

		for (int i = 0; i < animals.length; i++) {
			System.out.println(animals[i].getName() + ": " + animals[i].eat());
		}
		System.out.println();

		henrietta.layEgg();

		System.out.println(porky.getName() + " makes the sound " + porky.getSound());

		Egg[] carton = new Egg[12];

		List<Sellable> sellables = new ArrayList<>();
		sellables.add(charlize);
		sellables.add(porky);
		sellables.add(bessie);
		for (int i = 0; i < 12; i++) {
			carton[i] = henrietta.layEgg();
			sellables.add(carton[i]);
		}

		porky.setPrice(new BigDecimal("150.00"));
		for (Sellable sellable : sellables) {
			System.out.println(sellable.getName() + " for sale for only $" + sellable.getPrice());
		}

		singAboutMe(bessie);
		Singable mySingable = bessie;
		FarmAnimal animal = bessie;
		Cow myCow = (Cow) animal;

		GuernseyCow gerry = new GuernseyCow();


	}

	public static void singAboutMe(Singable me) {

//		if (me instanceof Cat) {
//			Cat thisCat = (Cat) me;
//			thisCat.wakeUp();
//		}
//		if (me instanceof FarmAnimal) {
//			FarmAnimal animal = (FarmAnimal) me;
//			animal.wakeUp();
//		}
		String name = me.getName();
		String sound = me.getSound();
		System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
		System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
		System.out.println("With a " + sound + " " + sound + " here");
		System.out.println("And a " + sound + " " + sound + " there");
		System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
		System.out.println();
	}


}