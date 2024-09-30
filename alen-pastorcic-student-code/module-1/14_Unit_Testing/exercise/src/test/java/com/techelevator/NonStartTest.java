package com.techelevator;


import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
public class NonStartTest {
    @Test
    public void first_character_of_each() {
        NonStart firstLetter = new NonStart();
        Assert.assertEquals("he", firstLetter.getPartialString(null, "the"));
        Assert.assertEquals("", firstLetter.getPartialString(null, null));

        Assert.assertEquals("ppleanana", firstLetter.getPartialString("apple", "banana"));
        Assert.assertEquals("exasoldem", firstLetter.getPartialString("texas", "holdem"));



    }

}
