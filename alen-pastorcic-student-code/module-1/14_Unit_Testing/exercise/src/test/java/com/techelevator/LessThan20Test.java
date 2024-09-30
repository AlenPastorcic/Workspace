package com.techelevator;

import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

public class LessThan20Test {

    @Test
    public void isLessThanMultipleOf20_test(){
        Less20 number = new Less20();
        Assert.assertTrue("number is multiple of 20 -1 or -2", number.isLessThanMultipleOf20(18));
        Assert.assertFalse("number is a multiple of 20 -1 or -2", number.isLessThanMultipleOf20(37));

    }
}
