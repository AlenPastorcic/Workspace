package com.techelevator.dao;

import com.techelevator.model.MenuItem;

import java.util.List;

public interface MenuItemDao {

    List<MenuItem> getAllItems();

    int getItemCount();

    List<String> getItemNames();

    MenuItem getItemById(int itemId);

    MenuItem getItemByTitle(String title);

    MenuItem createMenuItem(MenuItem menuItem);

    MenuItem updateMenuItem(MenuItem menuItem);

    int deleteMenuItemById(int itemId);
}
