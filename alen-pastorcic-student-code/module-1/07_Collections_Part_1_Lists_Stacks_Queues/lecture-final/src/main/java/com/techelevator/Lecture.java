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
		System.out.println();


		System.out.println("####################");
		System.out.println("Lists are ordered");
		System.out.println("####################");

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}
		System.out.println();
		names.set(3, "Humperdink");

		for (int i = names.size() - 1; i >= 0; i--) {
			System.out.println(names.get(i));
		}
		System.out.println();


		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");
		names.add("Fezzik");
		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}
		System.out.println();

		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");
		names.add(3, "Miracle Max");
		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}
		System.out.println();

		System.out.println("####################");
		System.out.println("Lists allow elements to be removed by index");
		System.out.println("####################");
		names.remove(4);
		printNames(names);
//		while (names.remove("Fezzik")) {
//
//		}
//		for (int i = 0; i < names.size(); i++) {
//			System.out.println(names.get(i));
//		}
//		System.out.println();
//		names.remove("Fezzik");
//		for (int i = 0; i < names.size(); i++) {
//			System.out.println(names.get(i));
//		}
//		System.out.println();
//		names.remove("Fezzik");
//		for (int i = 0; i < names.size(); i++) {
//			System.out.println(names.get(i));
//		}
//		System.out.println();


		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");
		if (names.contains("Fezzik")) {
			System.out.println("Fezzik represents");
		}
		else {
			System.out.println("No Fezzik. Sorry.");
		}


		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");
		System.out.println("Miracle Max is at " + names.indexOf("Miracle Max"));
		names.add(3, "The Grandfather");
		System.out.println("Miracle Max is at " + names.indexOf("Miracle Max"));
		System.out.println("The King is at " + names.indexOf("The King"));
		System.out.println();

		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");
		String[] namesArray = names.toArray(new String[0]);
		for (int i = 0; i < namesArray.length; i++) {
			System.out.println(namesArray[i]);
		}
		System.out.println();

		System.out.println("####################");
		System.out.println("Lists can be sorted");
		System.out.println("####################");
		Collections.sort(names);
		printNames(names);


		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");
		Collections.reverse(names);
		printNames(names);

		System.out.println("####################");
		System.out.println("       FOREACH");
		System.out.println("####################");
		System.out.println();
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println();

		System.out.println("####################");
		System.out.println("Stacks");
		System.out.println("####################");
		Stack<String> actions = new Stack<>();
		actions.push("climb hill");
		actions.push("pick up box");
		actions.push("take out sword");

		for (String action : actions) {
			System.out.println("Action: "+ action);
		}
		System.out.println();
		System.out.println(actions.pop());
		System.out.println();
		for (String action : actions) {
			System.out.println("Action: "+ action);
		}
		System.out.println();

		System.out.println();
		System.out.println(actions.pop());
		System.out.println();
		for (String action : actions) {
			System.out.println("Action: "+ action);
		}
		System.out.println();

		System.out.println("####################");
		System.out.println("Queues");
		System.out.println("####################");
		Queue<String> qactions = new LinkedList<>();

		qactions.offer("climb hill");
		qactions.offer("pick up box");
		qactions.offer("take out sword");

		for (String qaction : qactions) {
			System.out.println("QAction: "+ qaction);
		}
		System.out.println();
		System.out.println(qactions.poll());
		System.out.println();
		for (String qaction : qactions) {
			System.out.println("QAction: "+ qaction);
		}
		System.out.println();
	}

	public static void printNames(List<String> words) {
		for (int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}
		System.out.println();
	}
}
