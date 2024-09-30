package com.techelevator;

import java.util.*;

public class Lecture {

	public static void main(String[] args) {
		System.out.println("####################");
		System.out.println("       LISTS");
		System.out.println("####################");
		List<String> names = new ArrayList<>();
		names.add("Westley");
		names.add("Buttercup");
		names.add("Inigo Montoya");
		names.add("Prince Humperdink");
		names.add("Fezzik");
		names.add("Vizzini");
		System.out.println("There are now "+ names.size() + " elements in the List");


		System.out.println("####################");
		System.out.println("Lists are ordered");
		System.out.println("####################");

		;for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}
		System.out.println();
				names.set(3, "Humperdink");

		for (int i = names.size() - 1; i >= 0; i--) {
			System.out.println(names.get(i));
		}
		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");


		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");


		System.out.println("####################");
		System.out.println("Queues");
		System.out.println("####################");
		Queue<String> qactions = new LinkedList<>();
		qactions.offer("Climb hill");
		qactions.offer("Pick up boc");
		
		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");
		names.add(3, "Miracle Max");


		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");
		names.remove(4);
		for (int i = 0; i < names.size(); i ++) {
			System.out.println(names.get(i));
		}


		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");
		String[] namesArray = names.toArray(new String[0]);
		for (int i = 0; i < namesArray.length; i++) {
			System.out.println(namesArray[i]);
		}


		System.out.println("####################");
		System.out.println("Lists can be sorted");
		System.out.println("####################");
		Collections.sort(names);


		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");
		Collections.reverse(names);
		printNames(names);


		System.out.println("####################");
		System.out.println("       FOREACH");
		System.out.println("####################");
		for (String name : names) {
			System.out.println(name);
		}

	}


	public static void printNames(List<String> words) {
		for (int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}
	}
}
