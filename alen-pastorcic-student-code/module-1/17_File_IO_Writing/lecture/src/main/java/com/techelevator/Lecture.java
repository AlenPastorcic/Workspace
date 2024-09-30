package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lecture {

	public static void main(String[] args) throws IOException {

		Scanner userInput = new Scanner(System.in);

		/*
		 * The java.io.File class is a representation of file and directory path names.  It provides methods to inspect and
		 * modify file system objects.
		 *
		 * One benefit is that it compensates for differences in Windows and Unix use of '/' and '\' as directory delimiters.
		 *
		 * A new instance of File can be created from a String that contains a file system path
		 */

		System.out.print("Enter the path of a file or a directory: ");
		String path = userInput.nextLine();
		File f = new File(path);
		System.out.println();

		if (f.exists()) {
			System.out.println("Name: " + f.getName());
			System.out.println("Absolute path: " + f.getAbsolutePath());
			if (f.isDirectory()) {
				System.out.println("Type: directory");
			} else if (f.isFile()) {
				System.out.println("Type: file");
			}

			System.out.println("Size: " + f.length());
		} else {
			System.out.println(f.getAbsolutePath() + "does not exist");
		}


		System.out.println();
		System.out.println("Let's create a new directory");
		System.out.print("Enter the path of the new directory: ");
		path = userInput.nextLine();
		File newDirectory = new File(path);

		if (newDirectory.exists() && !newDirectory.isDirectory()) {
//			System.out.println("Sorry, " + newDirectory.getAbsolutePath() + " already exists");
//			throw new IOException("Unable to create new directory as it already exists.");
//
			if (newDirectory.mkdir()) {
				System.out.println("New directory created at " + newDirectory.getAbsolutePath());
			} else {
				System.out.println("Could not create directory.");
				throw new IOException("Unable to create new directory.");
			}


		}
		System.out.println("gamelogs directory exists");


		System.out.println();
		System.out.println("Now lets put a file in the directory");
		System.out.print("Enter a name for the new file: ");
		String fileName = userInput.nextLine();
		File newFile = new File(newDirectory, fileName);

		if (!newFile.exists()) {
			newFile.createNewFile();
			System.out.println();
		}

		System.out.println();
		System.out.println("Now, let's write a message to the file");
		System.out.println("Enter a message to add to the file: ");
		String message = userInput.nextLine();

		PrintWriter writer = new PrintWriter(newFile);
		writer.println(message);
		for (int i = 1; i <= 10; i++) {
			writer.println(i + ") " + message);
			if (i % 3 == 0) {
				writer.flush();
			}
		}

		writer.close();
	}




}
