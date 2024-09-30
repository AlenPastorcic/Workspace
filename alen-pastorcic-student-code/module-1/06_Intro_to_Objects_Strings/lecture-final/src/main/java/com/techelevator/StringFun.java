package com.techelevator;

public class StringFun {
    public static void main(String[] args) {

        String hello = "hello world"; // this gives us a string object

        // length
        System.out.println(hello.length());
        System.out.println("     ".length());

        String words = "one two three four";

        // substring
        System.out.println(words.substring(4));
        System.out.println(words.substring(4, 6)); // returns 'tw'
        System.out.println(words.substring(4, 7)); // returns 'two'

        // charAt()
        char tee = 't';
        System.out.println(words.charAt(4));

        // contains
        System.out.println(words.contains("two")); // true
        System.out.println(words.contains("TWO")); // false; contains compares
                                                    // using a case-sensitive comparison

        // indexOf
        System.out.println(words.indexOf("two"));


        // startsWith & endsWith
        System.out.println(words.substring(0,1).equals("o"));
        System.out.println(words.startsWith("o"));
        System.out.println(words.endsWith("four"));

        // toUpperCase() & toLowerCase()
        // returns a new string
        System.out.println(words.toLowerCase());
        System.out.println(words.toUpperCase());

        String allCaps = words.toUpperCase();
        System.out.println(allCaps.contains("TWO"));

        System.out.println(words.toUpperCase().contains("TWO")); // method chaining

        // strings are immutable.
        String newString = words.replace("one", "five");
        System.out.println(newString);
        System.out.println(words);





    }
}
