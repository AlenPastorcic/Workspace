package com.techelevator;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter a number");
		int num = scanner.nextInt();

		if (num <=0) {
			System.out.println("0, 1");
		}
		else {
			int first = 0;
			int second = 1;
			System.out.println(first + ", " + second);

			int next = first + second;
			while (next <= num) {
				System.out.println(", " + next);
				first = second;
				second = next;
				next = first + second;
			}
		}


	}

}
