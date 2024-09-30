package com.techelevator.dao;

import com.techelevator.model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcCityDaoTests extends BaseDaoTests {

    private static final City CITY_1 = new City(1, "City 1", "AA", 11, 111);
    private static final City CITY_2 = new City(2, "City 2", "BB", 22, 222);
    private static final City CITY_4 = new City(4, "City 4", "AA", 44, 444);

    private City testCity;

    private JdbcCityDao sut;

    @Before
    public void setup() {
        sut = new JdbcCityDao(dataSource);
        testCity = new City(0, "Test City", "CC", 99, 999);
    }

    @Test
    public void getCityById_with_valid_id_returns_correct_city() {
        City city = sut.getCityById(1);
        assertCitiesMatch(CITY_1, city);

        city = sut.getCityById(2);
        assertCitiesMatch(CITY_2, city);
    }

    @Test
    public void getCityById_with_invalid_id_returns_null_city() {
        City city = sut.getCityById(99);
        Assert.assertNull("City retrieved with invalid id should be null", city);
    }

    @Test
    public void getCitiesByState_with_valid_state_returns_correct_cities() {
        List<City> cities = sut.getCitiesByState("AA");
        Assert.assertEquals("There should be two cities in AA", 2, cities.size());
        assertCitiesMatch(CITY_1, cities.get(0));
        assertCitiesMatch(CITY_4, cities.get(1));

        cities = sut.getCitiesByState("BB");
        Assert.assertEquals("There should be one city in BB", 1, cities.size());
        assertCitiesMatch(CITY_2, cities.get(0));
    }

    @Test
    public void getCitiesByState_with_invalid_state_returns_empty_list() {
        List<City> cities = sut.getCitiesByState("XX");
        Assert.assertNotNull("List of cities should be empty, not null", cities);
        Assert.assertEquals(0, cities.size());
    }

    @Test
    public void createCity_creates_city() {
        City createdCity = sut.createCity(testCity);
        Assert.assertNotNull(createdCity);

        int newId = createdCity.getCityId();
        Assert.assertTrue(newId > 0);

        /* Ben thinks this is a better test, as it matches the created object with the test object */
        testCity.setCityId(newId);
        assertCitiesMatch(testCity, createdCity);
        testCity.setCityId(0);

        /* The curriculum team used this test. It is ok to use it as well. */
        City retrievedCity = sut.getCityById(newId);
        assertCitiesMatch(createdCity, retrievedCity);
    }

    @Test
    public void updateCity_updates_city() {
        City cityToUpdate = sut.getCityById(1);

        cityToUpdate.setCityName("Updated");
        cityToUpdate.setStateAbbreviation("CC");
        cityToUpdate.setPopulation(99);
        cityToUpdate.setArea(999);

        City updatedCity = sut.updateCity(cityToUpdate);
        Assert.assertNotNull(updatedCity);

        City retrievedCity = sut.getCityById(1);
        assertCitiesMatch(updatedCity, retrievedCity);
    }

    @Test
    public void deleteCityById_deletes_city() {
        int rowsAffected = sut.deleteCityById(4);
        Assert.assertEquals(1, rowsAffected);

        City retrievedCity = sut.getCityById(4);
        Assert.assertNull(retrievedCity);
    }

    private void assertCitiesMatch(City expected, City actual) {
        Assert.assertEquals("City ids should match", expected.getCityId(), actual.getCityId());
        Assert.assertEquals("City names should match", expected.getCityName(), actual.getCityName());
        Assert.assertEquals("State abbreviations for city should match", expected.getStateAbbreviation(), actual.getStateAbbreviation());
        Assert.assertEquals("City populations should match", expected.getPopulation(), actual.getPopulation());
        Assert.assertEquals("City areas should match", expected.getArea(), actual.getArea(), 0.1);
    }
}
