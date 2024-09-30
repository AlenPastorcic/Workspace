package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Menu;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JdbcMenuDao implements MenuDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcMenuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Menu> findAllMenus() {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT event_id, item_id FROM menus;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Menu menu = mapRowToUser(results);
                menus.add(menu);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return menus;
    }

    @Override
    public List<Menu> findMenuByEventId(int id) {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT event_id, item_id FROM menus WHERE event_id =?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
               Menu menu = mapRowToUser(results);
               menus.add(menu);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return menus;
    }

    @Override
    public Menu findMenuByItemId(int id) {
        Menu menu = null;
        String sql = "SELECT event_id, item_id FROM menus WHERE item_id =?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                menu = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return menu;
    }

    public Menu findMenuByEventAndItemId(int eventId, int itemId){
        Menu menu = null;
        String sql = "SELECT event_id, item_id FROM menus WHERE (item_id =? AND event_id=?);";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemId, eventId);
            if (results.next()) {
                menu = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return menu;
    }

    @Override
    public Menu createMenu(Menu menu) {
        Menu newMenu = null;
        String sql = "INSERT INTO menus (event_id, item_id) " +
                " VALUES (?,?);";
        try {
            jdbcTemplate.update(sql, menu.getEventId(), menu.getItemId());
            newMenu = findMenuByEventAndItemId(menu.getEventId(), menu.getItemId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newMenu;
    }

    @Override
    public void createEventMenus(List<Menu> menus) {
        if(menus.size() < 1){
            return;
        }
        List<Menu> currentMenu = findMenuByEventId(menus.get(0).getEventId());
        List<Integer> currentItemIds = currentMenu.stream().map(Menu::getItemId).collect(Collectors.toList());
        List<Integer> itemIds = menus.stream().map(Menu::getItemId).collect(Collectors.toList());
        //add menu item to menu
        for(Menu menu : menus){
            if(!currentItemIds.contains(menu.getItemId())){
                createMenu(menu);
            }
        }
        //remove menu item to menu
        for(Menu menu : currentMenu){
            if(!itemIds.contains(menu.getItemId())){
                deleteMenu(menu);
            }
        }
        return;
    }

    @Override
    public int deleteMenu(Menu menu) {
        String sql = "DELETE from menus WHERE (item_id =? AND event_id=?);";
        int numberOfRows = 0;
        try {
            numberOfRows = jdbcTemplate.update(sql, menu.getItemId(), menu.getEventId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException(DaoException.NO_DATABASE_ERROR, e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException(DaoException.DATA_INTEGRITY_ERROR, e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException(DaoException.BADLY_FORMED_ERROR, e);
        }
        return numberOfRows;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        //TODO: learn sql pls
        return null;
    }

    private Menu mapRowToUser(SqlRowSet rs) {
        Menu menu = new Menu();
        menu.setEventId(rs.getInt("event_id"));
        menu.setItemId(rs.getInt("item_id"));
        return menu;
    }
}
