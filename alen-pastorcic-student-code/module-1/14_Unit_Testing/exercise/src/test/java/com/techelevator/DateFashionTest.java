package com.techelevator;

import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

public class DateFashionTest {

    @Test
    public void Testing_getting_a_Table() {
        DateFashion fashion = new DateFashion();
        assert fashion.getATable(3, 9) == 2;
        assert fashion.getATable(4, 6) == 1;
        assert fashion.getATable(1, 7) == 0;
    }



}
