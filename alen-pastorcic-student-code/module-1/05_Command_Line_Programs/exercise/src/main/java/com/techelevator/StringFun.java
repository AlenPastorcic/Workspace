package com.techelevator;

import java.util.Locale;

public class StringFun {
    public static void main(String[] args) {
        String hello = "hello world";
        System.out.println(hello.length());
        String words = "one two three four";
        System.out.println(words.substring(7));
        System.out.println(words.contains("twO"));
        System.out.println(words.indexOf("three"));

        String newString = words.replace("one", "five");

    }
}
