package com.techelevator.dao;

import com.techelevator.model.City;
import com.techelevator.model.Park;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDaoTests extends BaseDaoTests {

    private static final Park PARK_1 =
            new Park(1, "Park 1", LocalDate.parse("1800-01-02"), 100, true);
    private static final Park PARK_2 =
            new Park(2, "Park 2", LocalDate.parse("1900-12-31"), 200, false);
    private static final Park PARK_3 =
            new Park(3, "Park 3", LocalDate.parse("2000-06-15"), 300, false);

    private Park testPark;

    private JdbcParkDao sut;

    @Before
    public void setup() {
        sut = new JdbcParkDao(dataSource);
        testPark = new Park(0, "Test Park", LocalDate.now(), 900, true);
    }

    @Test
    public void getParkById_with_valid_id_returns_correct_park() {
        Park park = sut.getParkById(1);
        assertParksMatch(PARK_1, park);

        park = sut.getParkById(2);
        assertParksMatch(PARK_2, park);
    }

    @Test
    public void getParkById_with_invalid_id_returns_null_park() {
        Park park = sut.getParkById(99);
        Assert.assertNull(park);
    }

    @Test
    public void getParksByState_with_valid_state_returns_correct_parks() {
        List<Park> parks = sut.getParksByState("AA");
        Assert.assertEquals(2, parks.size());
        assertParksMatch(PARK_1, parks.get(0));
        assertParksMatch(PARK_3, parks.get(1));

        parks = sut.getParksByState("BB");
        Assert.assertEquals(1, parks.size());
        assertParksMatch(PARK_2, parks.get(0));
    }

    @Test
    public void getParksByState_with_invalid_state_returns_empty_list() {
        List<Park> parks = sut.getParksByState("XX");
        Assert.assertEquals(0, parks.size());
    }

    @Test
    public void createPark_creates_park() {
        Park createdPark = sut.createPark(testPark);
        Assert.assertNotNull(createdPark);

        int newId = createdPark.getParkId();
        Assert.assertTrue(newId > 0);

        /* Ben thinks this is a better test, as it matches the created object with the test object */
        testPark.setParkId(newId);
        assertParksMatch(testPark, createdPark);
        testPark.setParkId(0);

        /* The curriculum team used this test. It is ok to use it as well. */
        Park retrievedPark = sut.getParkById(newId);
        assertParksMatch(createdPark, retrievedPark);
    }

    @Test
    public void updatePark_updates_park() {
        Park parkToUpdate = sut.getParkById(1);

        parkToUpdate.setParkName("Updated");
        parkToUpdate.setDateEstablished(LocalDate.now());
        parkToUpdate.setArea(900);
        parkToUpdate.setHasCamping(false);

        Park updatedPark = sut.updatePark(parkToUpdate);
        Assert.assertNotNull(updatedPark);

        Park retrievedPark = sut.getParkById(1);
        assertParksMatch(updatedPark, retrievedPark);
    }

    @Test
    public void deleteParkById_deletes_park() {
        int rowsAffected = sut.deleteParkById(1);
        Assert.assertEquals(1, rowsAffected);

        Park retrievedPark = sut.getParkById(1);
        Assert.assertNull(retrievedPark);
    }

    @Test
    public void linkParkState_adds_park_to_list_of_parks_in_state() {
        int preLinkParkCount = sut.getParksByState("CC").size();

        sut.linkParkState(1, "CC");
        List<Park> parks = sut.getParksByState("CC");
        int postLinkParkCount = parks.size();

        Assert.assertEquals(preLinkParkCount + 1, postLinkParkCount);
        assertParksMatch(PARK_1, parks.get(0));
    }

    @Test
    public void unlinkParkState_removes_park_from_list_of_parks_in_state() {
        int preUnlinkParkCount = sut.getParksByState("AA").size();

        sut.unlinkParkState(1, "AA");
        List<Park> parks = sut.getParksByState("AA");
        int postUnlinkParkCount = parks.size();

        Assert.assertEquals(preUnlinkParkCount - 1, postUnlinkParkCount);
        assertParksMatch(PARK_3, parks.get(0));
    }

    private void assertParksMatch(Park expected, Park actual) {
        Assert.assertEquals("Park ids should match", expected.getParkId(), actual.getParkId());
        Assert.assertEquals("Park names should match", expected.getParkName(), actual.getParkName());
        Assert.assertEquals("Park dates established should match", expected.getDateEstablished(), actual.getDateEstablished());
        Assert.assertEquals("Park areas should match", expected.getArea(), actual.getArea(), 0.1);
        Assert.assertEquals("Park has_camping should match", expected.getHasCamping(), actual.getHasCamping());
    }

}
