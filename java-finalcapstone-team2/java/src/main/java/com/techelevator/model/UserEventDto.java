package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class UserEventDto {
    private User user;
    private List<Event> hostEvent;
    private List<Event> chefEvent;
    private List<Event> attendeeEvent;

    public UserEventDto() {
        hostEvent = new ArrayList<>();
        chefEvent = new ArrayList<>();
        attendeeEvent = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Event> getHostEvent() {
        return hostEvent;
    }

    public void setHostEvent(List<Event> hostEvent) {
        this.hostEvent = hostEvent;
    }

    public List<Event> getChefEvent() {
        return chefEvent;
    }

    public void setChefEvent(List<Event> chefEvent) {
        this.chefEvent = chefEvent;
    }

    public List<Event> getAttendeeEvent() {
        return attendeeEvent;
    }

    public void setAttendeeEvent(List<Event> attendeeEvent) {
        this.attendeeEvent = attendeeEvent;
    }
}
