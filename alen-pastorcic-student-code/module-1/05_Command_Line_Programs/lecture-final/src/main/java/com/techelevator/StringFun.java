package com.techelevator;

public class StringFun {
    public static void main(String[] args) {


        // =========== comparing strings ============
        String hello = "hello";
        String world = "world";
        String full = hello + " " + world;
        String expected = "hello world";
        System.out.println(full);
        System.out.println(expected);
        System.out.println(full == expected); // cannot compare String with ==
        System.out.println(full.equals(expected));
        System.out.println("A".equals("a"));
        System.out.println("A".equalsIgnoreCase("a"));

        // spliting strings
        String words = "one,two,three,four";
        String[] actualWords = words.split(","); // splitting on the comma as a separator
        for (int i=0; i<actualWords.length;i++) {
            System.out.println(actualWords[i]);
        }

        // reassemble as separated by vertical bar
        String joinedBack ="";
        for(int i=0; i<actualWords.length;i++) {
            joinedBack += actualWords[i] + "|";
        }
        System.out.println(joinedBack);
        System.out.println(String.join("|", actualWords));

        // parsing
        String oneAsAString = "1";
        System.out.println(oneAsAString + 5);
        int six = Integer.parseInt(oneAsAString)+5;
        System.out.println(six);
        Long.parseLong("1");
        Double.parseDouble("42.0");
        Boolean.parseBoolean("true");
//        Integer.parseInt("not-an-integer"); this will cause error because the string is not a "number"

        // formatting
        int theAnswer = 42;
        System.out.println(theAnswer);
        System.out.println("the answer is " + theAnswer);
        // X=## Y=## Z=##
        String crazy = "X=" + 1 + " Y=" + 2 + " Z=" + 3;
        System.out.println(crazy);
        String formatString = "X=%s Y=%s Z=%s"; // %s <== a template placeholder
        System.out.println(String.format(formatString, 1, 2, 3));
        System.out.println(String.format(formatString, 51, 52, 53));

        /*
        %s <== general
        %d <== integer data types
        %f <== floating point data types
        https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Formatter.html
         */

        System.out.print("A");
        System.out.print("B");
        System.out.println("C");
        System.out.print("D");
        System.out.println("");

        System.out.printf(">%d<\n", 42); // \n means insert a line feed
        System.out.printf(">%5d<\n", 42);
        System.out.printf("*>%3d<*\n", 42000000);
        System.out.printf(">%5d<\n", 142);
        System.out.printf(">%-5d<\n", 142);
        System.out.printf(">%8f<\n", 42.1234);
        System.out.printf(">%8.2f<\n", 42.1234);


    }


}
