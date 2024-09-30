package com.techelevator;

import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinderTest {

    @Test
    public void list_finding_largest_value() {
        Finder findsLargestNum = new Finder();
        assert findsLargestNum.findLargest(Arrays.asList()) == null;
        assert findsLargestNum.findLargest(Arrays.asList(25, 34, 678, 984, 21, 456, 8746)) == 8746;

    }
}
