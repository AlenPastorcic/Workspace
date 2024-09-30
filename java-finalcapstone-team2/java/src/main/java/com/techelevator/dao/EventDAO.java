package com.techelevator.dao;

import com.techelevator.model.AttendeeDto;
import com.techelevator.model.Event;

import java.util.List;

public interface EventDAO {
    List<Event> findAll();

    Event findById(int id);

    Event createEvent(Event event, List<String> emailList);

    Event update(Event event, List<String> emailList);

    int deleteEventById(int id);

    List<Event> getHostEventByUserId(int userId);

    List<Event> getChefEventByUserId(int userId);

    List<Event> getAttendeeEventByUserId(int userId);

    boolean addAttendee(AttendeeDto attendeeDto);

    boolean removeAttendee(AttendeeDto attendeeDto);
}
