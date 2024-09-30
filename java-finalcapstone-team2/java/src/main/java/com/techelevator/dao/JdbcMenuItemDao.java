package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.MenuItem;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMenuItemDao implements MenuItemDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcMenuItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<MenuItem> getAllItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT item_id, title, description, img_url FROM menuItems;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                MenuItem menuItem = mapRowToMenuItem(results);
                menuItems.add(menuItem);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return menuItems;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM menuItems;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                count = results.getInt("count");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return count;
    }

    @Override
    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        String sql = "SELECT title FROM menuItems ORDER BY title ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                itemNames.add(results.getString("title"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return itemNames;
    }

    @Override
    public MenuItem getItemById(int itemId) {
        MenuItem menuItem = null;
        String sql = "SELECT item_id, title, description, img_url " +
                "FROM menuItems " +
                "WHERE item_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemId);
            if (results.next()) {
                menuItem = mapRowToMenuItem(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return menuItem;
    }

    @Override
    public MenuItem getItemByTitle(String title) {
        MenuItem menuItem = null;
        String sql = "SELECT item_id, title, description, img_url " +
                "FROM menuItems " +
                "WHERE title = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, title);
            if (results.next()) {
                menuItem = mapRowToMenuItem(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return menuItem;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        MenuItem newMenuItem = null;
        String sql = "INSERT INTO menuItems (title, description, img_url) " +
                "VALUES (?, ?, ?) RETURNING item_id;";
        try {
            int newMenuItemId = jdbcTemplate.queryForObject(sql, int.class,
                    menuItem.getTitle(), menuItem.getDescription(), menuItem.getImgUrl());

            newMenuItem = getItemById(newMenuItemId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return menuItem;
    }

    @Override
    public MenuItem updateMenuItem(MenuItem menuItem) {
        MenuItem updatedMenuItem = null;
        String sql = "UPDATE menuItems SET title = ?, description = ?, img_url = ? " +
                "WHERE item_id =?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, menuItem.getTitle(), menuItem.getDescription(), menuItem.getImgUrl());
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedMenuItem = getItemById(menuItem.getItemId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedMenuItem;
    }

    @Override
    public int deleteMenuItemById(int itemId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM menuItems WHERE item_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, itemId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }


    private MenuItem mapRowToMenuItem(SqlRowSet rowSet) {
        MenuItem menuItem = new MenuItem();
        menuItem.setItemId(rowSet.getInt("item_id"));
        menuItem.setTitle(rowSet.getString("title"));
        menuItem.setDescription(rowSet.getString("description"));
        menuItem.setImgUrl(rowSet.getString("img_url"));
        return menuItem;
    }


}
