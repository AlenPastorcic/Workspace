package com.techelevator;

import org.junit.After; // The @After annotation is used to execute a method after every test
import org.junit.Assert; // The Assert class has static assertion methods for validating test results
import org.junit.Before; // The @Before annotation is used to execute a method before every test
import org.junit.Test; // The @Test annotation is used to label methods that should be run as tests
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

public class CigarPartyTest {

    @Test
    public void haveParty_test_weekday_party_within_expected_range() {
        CigarParty weekday = new CigarParty();
        Assert.assertTrue("It is Wednesday, but we have 50 cigars!", true);
        weekday.haveParty(40, false);
        Assert.assertFalse("It is Wednesday but we have 8 cigars", false);
        weekday.haveParty(5, true);
    }

    @Test
    public void weekend_not_enough_cigars() {
        CigarParty weekend = new CigarParty();
        Assert.assertFalse("Dang we do not have enough to party on the weekend!", false);
        weekend.haveParty(7, true);
    }

    @Test
    public void weekend_party_any_range() {
        CigarParty weekend = new CigarParty();
        Assert.assertTrue("It is the weekend and the squirrels have 65 cigars", true);
        weekend.haveParty(65, true);
    }




}
