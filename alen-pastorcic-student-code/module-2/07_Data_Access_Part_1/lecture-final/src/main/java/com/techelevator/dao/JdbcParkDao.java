package com.techelevator.dao;

import com.techelevator.model.City;
import com.techelevator.model.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import javax.swing.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class JdbcParkDao implements ParkDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int getParkCount() {
        int count = 0;

        String sql = "SELECT COUNT(*) AS count FROM park;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        if (rowSet.next()) {
            count = rowSet.getInt("count");
        }

        return count;
    }
    
    @Override
    public LocalDate getOldestParkDate() {
        LocalDate oldest = null;

        String sql = "SELECT MIN(date_established) AS oldest FROM park;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        if (rowSet.next()) {
            Date date = rowSet.getDate("oldest");
            if (date != null) {
                oldest = date.toLocalDate();
            }
        }

        return oldest;
    }
    
    @Override
    public double getAverageParkArea() {
        double avgArea = 0.0;
        String sql = "SELECT AVG(area) AS avg_area FROM park;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            avgArea = results.getDouble("avg_area");
        }
        return avgArea;
    }
    
    @Override
    public List<String> getParkNames() {
        List<String> parkNames = new ArrayList<>();
        String sql = "SELECT park_name FROM park ORDER by park_name ASC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            String park = results.getString("park_name");
            parkNames.add(park);
        }
        return parkNames;
    }
    
    @Override
    public Park getRandomPark() {
        Park park = null;
        String sql = "SELECT * FROM park ORDER BY RANDOM() LIMIT 1;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            park = mapRowToPark(results);
        }
        return park;
    }

    @Override
    public List<Park> getParksWithCamping() {
        List<Park> parks = new ArrayList<>();

        String sql = "SELECT park_id, park_name, date_established, area, has_camping " +
                "FROM park " +
                "WHERE has_camping;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while(rowSet.next()) {
            Park park = mapRowToPark(rowSet);
            parks.add(park);
        }

        return parks;
    }

    @Override
    public Park getParkById(int parkId) {
        return new Park();
    }

    @Override
    public List<Park> getParksByState(String stateAbbreviation) {
        return new ArrayList<>();
    }

    @Override
    public List<Park> getParksByName(String name, boolean useWildCard) {
        List<Park> parks = new ArrayList<>();

        SqlRowSet rowSet;
        if (useWildCard) {
            String sql = "SELECT park_id, park_name, date_established, area, has_camping " +
                    "FROM park " +
                    "WHERE park_name ILIKE ?;";
            rowSet = jdbcTemplate.queryForRowSet(sql, "%" + name + "%");
        }
        else {
            String sql = "SELECT park_id, park_name, date_established, area, has_camping " +
                    "FROM park " +
                    "WHERE park_name = ?;";
            rowSet = jdbcTemplate.queryForRowSet(sql, name);
        }

        while(rowSet.next()) {
            Park park = mapRowToPark(rowSet);
            parks.add(park);
        }

        return parks;
    }

    private Park mapRowToPark(SqlRowSet rowSet) {
        Park park = new Park();
        park.setParkId(rowSet.getInt("park_id"));
        park.setParkName(rowSet.getString("park_name"));
        Date date = rowSet.getDate("date_established");
        if (date != null) {
            park.setDateEstablished(date.toLocalDate());
        }
        park.setArea(rowSet.getDouble("area"));
        park.setHasCamping(rowSet.getBoolean("has_camping"));

        return park;
    }
}
