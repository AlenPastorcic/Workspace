package com.techelevator;

import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.sql.Array;
import java.util.ArrayList;

public class Lucky13Test {

    @Test
    public void test_get_lucky() {
        Lucky13 no13 = new Lucky13();
        Assert.assertTrue("There is no 1 or 3 in the list", no13.getLucky(new int[]{2, 4, 5, 6,}));
        Assert.assertFalse("There is a 1 or 3 in the list", no13.getLucky(new int[]{1, 2, 5, 6, 9}));

    }
}