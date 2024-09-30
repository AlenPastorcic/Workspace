package com.techelevator;

public class Lecture {

	public static void main(String[] args) {
		
		/*
		1. Create a variable to hold an int and call it numberOfExercises.
			Then set it to 26.
		*/
	int numberOfExercises = 26;
			System.out.println(numberOfExercises);
		//System.out.println(numberOfExercises);

		/*
		2. Create a variable to hold a double and call it half.
			Set it to 0.5.
		*/
	double half = 0.5;
		System.out.println(half);


		/*
		3. Create a variable to hold a String and call it name.
			Set it to "TechElevator".
		*/
	String name = "TechElevator";
		System.out.println(name);
		//System.out.println(name);

		/*
		4. Create a variable called seasonsOfFirefly and set it to 1.
		*/
	int seasonsOfFireFly = 1;
		System.out.println(seasonsOfFireFly);
		//System.out.println(seasonsOfFirefly);

		/*
		5. Create a variable called myFavoriteLanguage and set it to "Java".
		*/
	String myFavoriteLanguage = "java";
		System.out.println(myFavoriteLanguage);
		//System.out.println(myFavoriteLanguage);

		/*
		6. Create a *constant* called PI and set it to 3.14159.
		*/
	final double PI = 3.14159;
			System.out.println(PI);
		//System.out.println(PI);

		/*
		7. Create and set a variable that holds your name.
		*/
	String myName = "Alen";
		System.out.println(myName);
		/*
		8. Create and set a variable that holds the number of buttons on your mouse.
		*/
	int numberOfButtons = 2;
		System.out.println("Number of buttons on mouse: " + numberOfButtons);
		/*
		9. Create and set a variable that holds the percentage of battery left on
		your phone.
		*/
	int percentBattery = 88;
		System.out.println("Percentage of phone battery: " + percentBattery);
		/*
		10. Create an int variable that holds the difference between 121 and 27.
		*/
	int differenceBetweenNumbers = 121 - 27;
		System.out.println(differenceBetweenNumbers);
		/*
		11. Create a double that holds the addition of 12.3 and 32.1.
		*/
	double additionOfNumbers = 12.3 + 32.1;
		System.out.println(additionOfNumbers);
		/*
		12. Create a String that holds your full name.
		*/
	String myFullName = "Alen Pastorcic";
		System.out.println(myFullName);
		/*
		13. Create a String that holds the word "Hello, " concatenated onto your
		name from above.
		*/
	String greeting = "Hello, " + myFullName;
		System.out.println(greeting);
		/*
		14. Add " Esquire" onto the end of your full name and save it back to
		the same variable.
		*/
	myFullName = myFullName + ", Esquire";
		System.out.println(myFullName);
		/*
		15. Now do the same as exercise 14, but use the += operator.
		*/
	myFullName += ", Esquire";
		System.out.println(myFullName);
		/*
		16. Create a variable to hold "Saw" and add a 2 onto the end of it.
		*/
	String mySawSword = "Saw" + 2;
		System.out.println(mySawSword);
		/*
		17. Add a 0 onto the end of the variable from exercise 16.
		*/
	mySawSword += 0;
		System.out.println(mySawSword);

		/*
		18. What is 4.4 divided by 2.2?
		*/
		System.out.println(4.4 / 2.2);
		/*
		19. What is 5.4 divided by 2?
		*/
		System.out.println(5.4 / 2);
		/*
		20. What is 5 divided by 2?
		*/
		System.out.println(5 / 2);
		/*
		21. What is 5.0 divided by 2?
		*/
		System.out.println(5.0 / 2);
		/*
		22. What is 66.6 divided by 100? Is the answer you get right or wrong?
		*/
		System.out.println(66.6 / 100);
		/*
		23. If I divide 5 by 2, what's my remainder?
		*/
		System.out.println(5 % 2);
		/*
		24. What is 1,000,000,000 * 3?
		*/
		System.out.println(1000000000 * 3);
		/*
		25. Create a variable that holds a boolean called isDoneWithExercises and
		set it to false.
		*/
	boolean isDoneWithExercises = false;
		/*
		26. Now set isDoneWithExercise to true.
		*/
		isDoneWithExercises = true;
	}

}
