package com.techelevator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();
		Set<String> characters = new HashSet<>();
		characters.add("Tish");
		System.out.println("Number of characters: " + characters.size());


		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();

	}


	public static Map<String, Integer> orders(){
		Map<String, Integer> newOrders = new HashMap<>();
		newOrders.put("Large Cheese", 2);
		newOrders.put("Medium Peperoni", 1);

		return newOrders;
	}


	public static Map<String, Integer> addToOrder(String item, Integer qty) {
		return orders;
	}
}
