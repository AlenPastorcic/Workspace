package com.techelevator.dao;

import com.techelevator.model.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> findAllMenus();

    List<Menu> findMenuByEventId(int id);

    Menu findMenuByItemId(int id);

    Menu createMenu(Menu menu);

    Menu updateMenu(Menu menu);

    void createEventMenus(List<Menu> menus);

    int deleteMenu(Menu menu);


}
