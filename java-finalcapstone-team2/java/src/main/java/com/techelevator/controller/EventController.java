package com.techelevator.controller;

import com.techelevator.dao.EventDAO;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import com.techelevator.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private UserDao userDao;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        Event event = eventDAO.findById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public UserEventDto getUserEvent(@PathVariable int userId) {
        UserEventDto userEventDto = new UserEventDto();

        User user = userDao.getUserById(userId);
        userEventDto.setUser(user);

        List<Event> hostEvent = eventDAO.getHostEventByUserId(userId);
        userEventDto.setHostEvent(hostEvent);

        List<Event> chefEvent = eventDAO.getChefEventByUserId(userId);
        userEventDto.setChefEvent(chefEvent);

        List<Event> attendeeEvent = eventDAO.getAttendeeEventByUserId(userId);
        userEventDto.setAttendeeEvent(attendeeEvent);

        return userEventDto;
    }

    @PostMapping
    public Event createEvent(@RequestBody EventDto eventDto) {
        Event event = eventDtoTOevent(eventDto);
        return eventDAO.createEvent(event, eventDto.getAttendeesEmailList());
    }

    @PostMapping("/addAttendee")
    public boolean addAttendee(@RequestBody AttendeeDto attendeeDto) {
        return eventDAO.addAttendee(attendeeDto);
    }

    @DeleteMapping("/removeAttendee")
    public boolean removeAttendee(@RequestBody AttendeeDto attendeeDto) {
        return eventDAO.removeAttendee(attendeeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable int id, @RequestBody EventDto eventDto) {
        Event existingEvent = eventDAO.findById(id);
        if (existingEvent != null) {
            Event eventDetails = eventDtoTOevent(eventDto);
            eventDAO.update(eventDetails, eventDto.getAttendeesEmailList());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        Event existingEvent = eventDAO.findById(id);
        if (existingEvent != null) {
            eventDAO.deleteEventById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Event eventDtoTOevent(EventDto eventDto){
        User host = userDao.getUserById(eventDto.getHostId());
        User chef = userDao.getUserById(eventDto.getChefId());
        Map<Integer, User> userMap = eventDto.getAttendees().stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        Map<Integer, MenuItem> menuItemMap = eventDto.getMenu().stream()
                .collect(Collectors.toMap(MenuItem::getItemId, menuItem -> menuItem));

        return new Event(eventDto.getEventId(), eventDto.getTitle(),
                eventDto.getDescription(), eventDto.getEventDate(),eventDto.getLocation(),
                eventDto.getImgUrl(),host,chef,userMap,menuItemMap);

    }

}
