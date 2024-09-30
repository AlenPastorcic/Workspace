package com.techelevator;

import java.util.Scanner;

public class Pulse {
    public static void main(String[] args) {

        // ==== splitting ====

        // date, upc, #units
        // 20240520,456788689987899,34000

        String singleString = "one,two,three";
        String[] words = singleString.split(",");
        for (int i=0; i<words.length; i++) {
            System.out.println(words[i]);
        }

        // ==== parsing ====
        String unitsAsAString = "34000";
        int unitsSold = Integer.parseInt(unitsAsAString);
        System.out.println(unitsSold);

        String numsAsStrings = "1,2,3,4,5";
        String[] nums = numsAsStrings.split(",");
        int[] vals = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            vals[i] = Integer.parseInt(nums[i]);
        }

        // this creates an object from the class Scanner.
        // the object is stored in a variable named 'userInput'
        // whose data type is 'Scanner'
        Scanner userInput = new Scanner(System.in);

        int x = 0; // primitive types
        int[] arr = null; // reference type
        System.out.println(arr);
        System.out.println(arr.length);

    }
}
