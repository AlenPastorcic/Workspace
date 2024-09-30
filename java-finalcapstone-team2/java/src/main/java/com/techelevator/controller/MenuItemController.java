package com.techelevator.controller;

import com.techelevator.dao.MenuItemDao;
import com.techelevator.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menuitems")
@CrossOrigin
public class MenuItemController {

    @Autowired
    private MenuItemDao menuItemDao;

    @PreAuthorize("permitAll")
    @GetMapping
    public List<MenuItem> getAllItems() {
        return menuItemDao.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getItemById(@PathVariable int id) {
        MenuItem menuItem = menuItemDao.getItemById(id);
        if (menuItem != null) {
            return ResponseEntity.ok(menuItem);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void createMenuItem(@RequestBody MenuItem menuItem) {
        menuItemDao.createMenuItem(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItemDetails) {
        MenuItem currentMenuItem = menuItemDao.getItemById(id);
        if (currentMenuItem != null) {
            menuItemDao.updateMenuItem(menuItemDetails);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable int id) {
        MenuItem currentMenuItem = menuItemDao.getItemById(id);
        if (currentMenuItem != null) {
            menuItemDao.deleteMenuItemById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
