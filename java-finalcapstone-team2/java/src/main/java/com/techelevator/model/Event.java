package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Event {

    private int eventId;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private String imgUrl;
    private User host;
    private User chef;

    private Map<Integer, User> attendeesList = new HashMap<>();

    private Map<Integer, MenuItem> menu = new HashMap();

    public Event() {
    }

    public Event(int eventId, String title, String description, LocalDateTime eventDate, String location, String imgUrl, User host, User chef, Map<Integer, User> attendeesList, Map<Integer, MenuItem> menu) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.imgUrl = imgUrl;
        this.host = host;
        this.chef = chef;
        this.attendeesList = attendeesList;
        this.menu = menu;
    }

    public Map<Integer, User> getAttendeesList() {
        return attendeesList;
    }

    public void setAttendeesList(Map<Integer, User> attendeesList) {
        this.attendeesList = attendeesList;
    }

    public void addAttendee(User attendee) {
        this.attendeesList.put(attendee.getId(), attendee);
    }

    public void removeAttendeeById(int userId){
        this.attendeesList.remove(userId);
    }

    public Map<Integer, MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(Map<Integer, MenuItem> menu) {
        this.menu = menu;
    }

    public void addMenuItem(MenuItem item) {
        this.menu.put(item.getItemId(), item);
    }

    public void removeMenuItemById(int itemId){
        this.menu.remove(itemId);
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public User getChef() {
        return chef;
    }

    public void setChef(User chef) {
        this.chef = chef;
    }
}





