package com.techelevator;


import java.util.Date;
import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the length: ");
		double length = scanner.nextDouble();

		System.out.println("Is the measurement in (m)eters, or (f)eet? ");
		scanner.nextLine();
		String scale = scanner.nextLine();

		double convertedLength;
		final double METERS_CONVERSION = 0.3048;
		final double FEET_CONVERSION = 3.2808399;
		if (scale.equalsIgnoreCase("M")) {
			convertedLength = length * FEET_CONVERSION;
			System.out.printf("%.0fm is %.0ff.\n", length, Math.floor(convertedLength));
		}	else if (scale.equalsIgnoreCase("f")) {
				convertedLength = (length * METERS_CONVERSION);
			System.out.printf("%.0ff is %.0fm.\n", length, Math.floor(convertedLength));
		}	else {
			System.out.println("Invalid input. Please enter 'm' or 'f'.");
		}



	}

}
