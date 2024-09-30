package com.techelevator;

import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
public class SameFirstLastTest {

    @Test
    public void are_first_and_last_letter_same() {
        SameFirstLast firstLast = new SameFirstLast();
        Assert.assertTrue(firstLast.isItTheSame(new int[]{1, 2, 3, 1}));
        Assert.assertFalse(firstLast.isItTheSame(new int[]{1, 2, 3, 4}));

    }
}
