package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
import com.techelevator.model.Order;
import com.techelevator.model.OrderStatus;
import com.techelevator.model.User;
import com.techelevator.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrderDao implements OrderDao {

    private JdbcTemplate jdbcTemplate;

    private EventDAO eventDAO;

    private UserDao userDao;

    public JdbcOrderDao(DataSource dataSource, EventDAO eventDAO, UserDao userDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.eventDAO = eventDAO;
        this.userDao = userDao;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, menu_item_id, user_id, comment, status, event_id FROM orders;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Order order = mapRowToOrder(results);
                orders.add(order);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orders;
    }

    @Override
    public int getOrderCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM orders;";
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
    public List<Integer> getOrderIds() {
        List<Integer> orderIds = new ArrayList<>();
        String sql = "SELECT order_id FROM orders ORDER BY order_id ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                orderIds.add(results.getInt("order_id"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orderIds;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        String sql = "SELECT order_id, menu_item_id, user_id, comment, status, event_id " +
                "FROM orders " +
                "WHERE order_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);
            if (results.next()) {
                order = mapRowToOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return order;
    }

    @Override
    public Order getOrderByUserId(int userId) {
        Order order = null;
        String sql = "SELECT order_id, menu_item_id, user_id, comment, status, event_id " +
                "FROM orders " +
                "WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                order = mapRowToOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return order;
    }

    @Override
    public Order getOrderByItemId(int itemId) {
        Order order = null;
        String sql = "SELECT order_id, menu_item_id, user_id, comment, status, event_id " +
                "FROM orders " +
                "WHERE menu_item_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemId);
            if (results.next()) {
                order = mapRowToOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return order;
    }

    @Override
    public List<Order> getOrderByEventId(int eventId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, menu_item_id, user_id, comment, status, event_id, title " +
                "FROM orders o JOIN menuitems m on o.menu_item_id = m.item_id " +
                "WHERE o.event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            while (results.next()) {
                Order order = mapRowToOrder(results);
                orders.add(order);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orders;
    }

    @Override
    public Order getOrderByStatus(OrderStatus status) {
        Order order = null;
        String sql = "SELECT order_id, menu_item_id, user_id, comment, status, event_id " +
                "FROM orders " +
                "WHERE status = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, status.name());
            if (results.next()) {
                order = mapRowToOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return order;
    }

    @Override
    public Order createOrder(Order order) {
        Order newOrder = null;
        String sql = "INSERT INTO orders (menu_item_id, user_id, comment, status, event_id) " +
                "VALUES (?, ?, ?, ?::order_status, ?) RETURNING order_id;";
        try {
            int newOrderId = jdbcTemplate.queryForObject(sql, int.class,
                    order.getMenuItemId(), order.getUserId(), order.getComment(),
                    order.getStatus().toString(), order.getEventId());

            newOrder = getOrderById(newOrderId);
            Event event = eventDAO.findById(newOrder.getEventId());
            String chefEmail = event.getChef().getEmail();
            String hostEmail = event.getHost().getEmail();
            String content = "New order received!!!";
            EmailService.sendEmail(content,chefEmail);
            EmailService.sendEmail(content,hostEmail);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database or server", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newOrder;
    }

    @Override
    public List<Order> getOrderByEventAndUserId(int event_id, int user_id) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, menu_item_id, user_id, comment, status, event_id, title " +
                "FROM orders o JOIN menuitems m on o.menu_item_id = m.item_id " +
                "WHERE (o.user_id = ? AND o.event_id = ?);";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id, event_id);
            while (results.next()) {
                Order order = mapRowToOrder(results);
                orders.add(order);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orders;
    }

    @Override
    public Order updateOrder(Order order) {
        Order updatedOrder = null;
        String sql = "UPDATE orders SET menu_item_id = ?, user_id = ?, comment = ?, status = ?::order_status, event_id = ? " +
                "WHERE order_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql,
                    order.getMenuItemId(), order.getUserId(), order.getComment(),
                    order.getStatus().toString(), order.getEventId(), order.getOrderId());


            User user = userDao.getUserById(order.getUserId());
            if(order.getStatus().toString().equals("cooking")){
                String content = "Your order is Start Cooking";
                EmailService.sendEmail(content, user.getEmail());
            }else if(order.getStatus().toString().equals("ready")){
                String content = "Hooray!!! Your order is ready!";
                EmailService.sendEmail(content, user.getEmail());
            }
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedOrder = getOrderById(order.getOrderId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedOrder;
    }

    @Override
    public int deleteOrderById(int orderId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM orders WHERE order_id = ?;";

        try {
            numberOfRows = jdbcTemplate.update(sql, orderId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException(DaoException.NO_DATABASE_ERROR, e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException(DaoException.DATA_INTEGRITY_ERROR, e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException(DaoException.BADLY_FORMED_ERROR, e);
        }
        return numberOfRows;
    }

    private Order mapRowToOrder(SqlRowSet rowSet) {
        Order order = new Order();
        order.setOrderId(rowSet.getInt("order_id"));
        order.setMenuItemId(rowSet.getInt("menu_item_id"));
        order.setUserId(rowSet.getInt("user_id"));
        order.setComment(rowSet.getString("comment"));

        String statusString = rowSet.getString("status");
        try {
            OrderStatus status = OrderStatus.valueOf(statusString);
            order.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown order status: " + statusString, e);
        }

        int eventId = rowSet.getInt("event_id");
        order.setEventId(eventId);

        try {
            order.setTitle(rowSet.getString("title"));
        } catch (Exception e) {

        }

        return order;
    }
}
