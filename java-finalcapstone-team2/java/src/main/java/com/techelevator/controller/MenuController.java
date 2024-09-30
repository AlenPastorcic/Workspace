package com.techelevator.controller;

import com.techelevator.dao.MenuDao;
import com.techelevator.dao.MenuItemDao;
import com.techelevator.model.Menu;
import com.techelevator.model.MenuDTO;
import com.techelevator.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/menus")
@CrossOrigin
public class MenuController {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuItemDao menuItemDao;

    @GetMapping
    public List<Menu> getAllMenus() {
        return menuDao.findAllMenus();
    }

    @GetMapping("/{eventid}")
    public ResponseEntity<MenuDTO> getMenuByEventId(@PathVariable int eventid) {
        List<Menu> menu = menuDao.findMenuByEventId(eventid);
        List<Integer> itemIds = menu.stream().map(Menu::getItemId).collect(Collectors.toList());
        List<MenuItem> menuItems  = new ArrayList<>();
        if (menu != null) {
            for(int itemId : itemIds){
                MenuItem menuItem = menuItemDao.getItemById(itemId);
                menuItems.add(menuItem);
            }
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenus(menu);
            menuDTO.setMenuItems(menuItems);
            return ResponseEntity.ok(menuDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void createMenu(@RequestBody Menu menu) {
        menuDao.createMenu(menu);
    }

    @PutMapping("/{eventid}")
    public ResponseEntity<Void> updateMenu(@PathVariable int id, @RequestBody Menu menuDetails) {
        List<Menu> existingMenu = menuDao.findMenuByEventId(id);
        if (existingMenu != null) {
            menuDao.updateMenu(menuDetails);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createMenu")
    public void createMenus(@RequestBody List<Menu> menus){
        menuDao.createEventMenus(menus);
    }
}
