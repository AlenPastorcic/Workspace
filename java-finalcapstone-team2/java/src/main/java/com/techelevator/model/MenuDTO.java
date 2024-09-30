package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO {
    private List<Menu> menus =new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
