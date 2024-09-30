package com.techelevator.controller;

import com.techelevator.dao.JdbcOrderDao;
import com.techelevator.dao.OrderDao;
import com.techelevator.model.Event;
import com.techelevator.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Order order = orderDao.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Order> getOrderByUserId(@PathVariable int id) {
        Order order = orderDao.getOrderByUserId(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/event/{event_id}")
    public ResponseEntity<List<Order>> getOrderByEventId(@PathVariable int event_id) {
        List<Order> order = orderDao.getOrderByEventId(event_id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/event/{event_id}/{user_id}")
    public ResponseEntity<List<Order>> getOrderByEventAndUserId(@PathVariable int event_id, @PathVariable int user_id) {
        List<Order> order = orderDao.getOrderByEventAndUserId(event_id, user_id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Boolean createOrder(@RequestBody List<Order> orders) {
        for(Order order : orders){
            orderDao.createOrder(order);
        }
        return true;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable int id, @RequestBody Order order) {
        Order currentOrder = orderDao.getOrderById(id);
        if (currentOrder != null) {
            orderDao.updateOrder(order);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        Order currentOrder = orderDao.getOrderById(id);
        if (currentOrder != null) {
            orderDao.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
