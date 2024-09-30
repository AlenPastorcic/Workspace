package com.techelevator;

import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FrontTimesTest {

    @Test
    public void generateString_get_first_three_strings() {
        FrontTimes firstThree = new FrontTimes();
        assert firstThree.generateString("th", 3).equals("ththth");
        assert firstThree.generateString("thejshfkj", 3).equals("thethethe");
    }



}
