package com.techelevator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        SETS");
		System.out.println("####################");
		System.out.println();
		Set<String> characters = new HashSet<>();
		characters.add("Tish");
		System.out.println("Number of characters: " + characters.size());

		if (characters.contains("Bernie")) {
			System.out.println("Bernie is in there");
		}
		else {
			System.out.println("Bernie isn't there yet");
		}

		characters.add("Bernie");
		System.out.println("Number of characters: " + characters.size());

		if (characters.contains("Bernie")) {
			System.out.println("Bernie is in there");
		}
		else {
			System.out.println("Bernie isn't there yet");
		}

		characters.remove("Tish");

		System.out.println("Number of characters: " + characters.size());

		if (characters.contains("Bernie")) {
			System.out.println("Bernie is in there");
		}
		else {
			System.out.println("Bernie isn't there yet");
		}

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();

		Map<String, Integer> orders = createOrder();
		orders = addToOrder(orders,"Small Cheese", 1);

		System.out.println(printOrders(orders));

		orders = addToOrder(orders,"Large Cheese", 1);
		orders = addToOrder(orders,"Small Pepperoni", 2);
//		System.out.println("Large cheese pizzas: "+ getItemQuantity(orders,"Large Cheese"));
//		System.out.println("Medium cheese pizzas: "+ getItemQuantity(orders,"Medium Cheese"));
//		System.out.println("Small cheese pizzas: "+ getItemQuantity(orders,"Small Cheese"));
//		System.out.println("Medium pepperoni pizzas: "+ getItemQuantity(orders,"Medium Pepperoni"));
//		System.out.println();
		System.out.println(printOrders(orders));
		orders = addToOrder(orders, "Greek salad", 2);
		System.out.println(printOrders(orders));

		for (Map.Entry<String, Integer> item : orders.entrySet()) {
			System.out.println(item.getKey() + ": " + item.getValue());
		}
	}

	public static Map<String, Integer> createOrder() {
		Map<String, Integer> newOrders = new HashMap<>();
		newOrders.put("Large Cheese", 2);
		newOrders.put("Medium Pepperoni", 1);

		return newOrders;
	}

	public static Map<String, Integer> addToOrder(Map<String, Integer> orders, String item, Integer qty) {
		/* See whether the key is already used, and if so, get its qty and update it. If it isn't there */
		/* simply add the item with the qty. */
		if (orders.containsKey(item)) {
			Integer existingQty = orders.get(item);
			existingQty = existingQty + qty;
			orders.put(item, existingQty);
		}
		else {
			orders.put(item, qty);
		}

		return orders;
	}

	public static Integer getItemQuantity(Map<String, Integer> orders, String item) {
		if (orders.containsKey(item)) {
			return orders.get(item);
		}
		else {
			return 0;
		}
	}

//	public static String printOrders(Map<String, Integer> orders) {
//		String result = "";
//		String[] sizes = { "Small", "Medium", "Large" };
//		String[] toppings = { "Cheese", "Pepperoni" };
//
//		for (String topping : toppings) {
//			for (String size : sizes) {
//				String key = size + " " + topping;
//				if (orders.containsKey(key)) {
//					result = result + key + " pizzas: " + getItemQuantity(orders, key) + "\n";
//				}
//			}
//		}
//
//		return result;
//	}

	public static String printOrders(Map<String, Integer> orders) {
		String result = "";

		Set<String> items = orders.keySet();
		for (String key : items) {
			result = result + key + ": " + getItemQuantity(orders, key) + "\n";
		}

		return result;
	}


}
