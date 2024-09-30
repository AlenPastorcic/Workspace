package com.techelevator.model;

public class Order {

    private String title;

    private int orderId;

    private int menuItemId;

    private int userId;

    private String comment;

    private OrderStatus status;

    private int eventId;



    public Order() {
        this.status = OrderStatus.pending;
    }

    public Order(int orderId, int menuItemId, int userId, String comment, int eventId) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.userId = userId;
        this.comment = comment;
        this.status = OrderStatus.pending;
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
