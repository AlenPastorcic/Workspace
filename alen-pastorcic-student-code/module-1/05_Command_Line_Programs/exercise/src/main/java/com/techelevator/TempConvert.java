package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the temperature: ");
		double temperature = scanner.nextDouble();

		System.out.println("Is the temperature in (C)elsius, or (F)ahrenheit? ");
		scanner.nextLine();
		String scale = scanner.nextLine();

		double convertedTemperature;
		if (scale.equalsIgnoreCase("C")) {
			convertedTemperature = (temperature * 1.8) + 32;
			System.out.printf("%.0fC is %.0fF.\n", temperature, convertedTemperature);
		} else if (scale.equalsIgnoreCase("F")) {
			convertedTemperature = (temperature - 32) / 1.8;
			System.out.printf("%.0fF is %.0fC.\n", temperature, convertedTemperature);

		} else {
			System.out.println("Invalid input. Please enter 'C' or 'F'. ");
		}
	}
}
