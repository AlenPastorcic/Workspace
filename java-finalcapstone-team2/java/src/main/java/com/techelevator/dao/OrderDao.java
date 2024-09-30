package com.techelevator.dao;

import com.techelevator.model.Order;
import com.techelevator.model.OrderStatus;

import java.util.List;

public interface OrderDao {

    int getOrderCount();

    List<Integer> getOrderIds();

    List<Order> getAllOrders();

    Order getOrderById(int orderId);

    Order getOrderByUserId(int userId);

    Order getOrderByItemId(int itemId);

    List<Order> getOrderByEventId(int eventId);

    Order getOrderByStatus(OrderStatus status);

    Order createOrder(Order order);

    Order updateOrder(Order order);

    List<Order> getOrderByEventAndUserId(int event_id, int user_id);

    int deleteOrderById(int orderId);

}
