package com.techelevator.dao;

import com.techelevator.model.Notification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JdbcNotificationDao implements NotificationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcNotificationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Notification> getNotificationByUserId(int userId) {
        return null;
    }
}
