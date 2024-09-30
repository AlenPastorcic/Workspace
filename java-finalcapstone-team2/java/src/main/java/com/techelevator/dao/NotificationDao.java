package com.techelevator.dao;

import com.techelevator.model.Notification;

import java.util.List;

public interface NotificationDao {
    List<Notification> getNotificationByUserId(int userId);
}
