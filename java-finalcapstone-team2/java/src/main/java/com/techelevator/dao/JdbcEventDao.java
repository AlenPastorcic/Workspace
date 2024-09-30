package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import com.techelevator.services.EmailService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcEventDao implements EventDAO {
    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    private MenuItemDao menuItemDao;
    private MenuDao menuDao;

    public JdbcEventDao(JdbcTemplate jdbcTemplate, UserDao userDao, MenuItemDao menuItemDao, MenuDao menuDao) {

        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.menuItemDao = menuItemDao;
        this.menuDao = menuDao;
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, title, description, event_date, location, img_url, host_id, chef_id FROM events;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }

    @Override
    public Event findById(int id) {
        Event event = null;
        String sql = "SELECT event_id, title, description, event_date, location, img_url, host_id, chef_id FROM events WHERE event_id=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                event = mapRowToEvent(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return event;
    }

    @Override
    public List<Event> getHostEventByUserId(int userId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, title, description, event_date, location, img_url, host_id, chef_id FROM events WHERE host_id=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }

    @Override
    public List<Event> getChefEventByUserId(int userId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, title, description, event_date, location, img_url, host_id, chef_id FROM events WHERE chef_id=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }

    @Override
    public List<Event> getAttendeeEventByUserId(int userId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, title, description, event_date, location, img_url, host_id, chef_id FROM events " +
                "WHERE event_id in (Select event_id from attendees where user_id=?);";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }


    @Override
    public Event createEvent(Event event, List<String> emailList) {
        Event newEvent = null;
        String sql = "INSERT INTO events (title, description, event_date, location, img_url, host_id, chef_id) " +
                " VALUES (?,?,?,?,?,?,?) RETURNING event_id;";
        try {
            int newEventId = jdbcTemplate.queryForObject(sql, int.class,
                    event.getTitle(), event.getDescription(), Timestamp.valueOf(event.getEventDate()),
                    event.getLocation(), event.getImgUrl(), event.getHost().getId(), event.getChef().getId());
            for (int id : event.getAttendeesList().keySet()) {
                AttendeeDto attendeeDto = new AttendeeDto();
                attendeeDto.setAttendeeId(id);
                attendeeDto.setEventId(newEventId);
                addAttendee(attendeeDto);
            }
            for (int id : event.getMenu().keySet()) {
                Menu menu = new Menu();
                menu.setEventId(newEventId);
                menu.setItemId(id);
                menuDao.createMenu(menu);
            }
            newEvent = findById(newEventId);

            sendInviteEmail(emailList, newEvent.getTitle(),
                    newEvent.getEventDate(),
                    newEvent.getLocation(),
                    newEvent.getEventId() + "",
                    newEvent.getHost().getFirstname(),
                    newEvent.getHost().getEmail());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newEvent;

    }


    @Override
    public boolean addAttendee(AttendeeDto attendeeDto) {
        String sql = "INSERT INTO attendees (event_id, user_id) VALUES (?,?);";
        try {
            int numberOfRows = jdbcTemplate.update(sql, attendeeDto.getEventId(), attendeeDto.getAttendeeId());
            if (numberOfRows > 0) {
                return true;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return false;
    }

    @Override
    public boolean removeAttendee(AttendeeDto attendeeDto) {
        String sql = "DELETE FROM attendees WHERE (event_id = ? AND user_id = ?);";
        try {
            int numberOfRows = jdbcTemplate.update(sql, attendeeDto.getEventId(), attendeeDto.getAttendeeId());
            if (numberOfRows > 0) {
                return true;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return false;
    }

    @Override
    public Event update(Event event, List<String> emailList) {
        Event updatedEvent = null;
        Map<Integer, User> attendees = getAttendeesByEventId(event.getEventId());
        Map<Integer, MenuItem> menus = getMenu(event.getEventId());
        String sql = "UPDATE events SET title = ?, description = ?, event_date = ?, location = ?, img_url = ?, host_id = ?, chef_id = ? WHERE event_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, event.getTitle(), event.getDescription(), Timestamp.valueOf(event.getEventDate()),
                    event.getLocation(), event.getImgUrl(), event.getHost().getId(), event.getChef().getId(), event.getEventId());
            if (numberOfRows == 0) {
                throw new DaoException(DaoException.NO_DATA_AFFECTED);
            } else {
                for (int id : event.getAttendeesList().keySet()) {
                    if (!attendees.containsKey(id)) {
                        AttendeeDto attendeeDto = new AttendeeDto();
                        attendeeDto.setEventId(event.getEventId());
                        attendeeDto.setAttendeeId(id);
                        addAttendee(attendeeDto);
                    }
                }

                for(int id: attendees.keySet()){
                    if(!event.getAttendeesList().keySet().contains(id)){
                        AttendeeDto attendeeDto = new AttendeeDto();
                        attendeeDto.setEventId(event.getEventId());
                        attendeeDto.setAttendeeId(id);
                        removeAttendee(attendeeDto);
                    }
                }

                for (int id : event.getMenu().keySet()) {
                    if (!menus.containsKey(id)) {
                        Menu menu = new Menu();
                        menu.setEventId(event.getEventId());
                        menu.setItemId(id);
                        menuDao.createMenu(menu);
                    }
                }

                updatedEvent = findById(event.getEventId());
                sendInviteEmail(emailList, updatedEvent.getTitle(),
                        updatedEvent.getEventDate(),
                        updatedEvent.getLocation(),
                        updatedEvent.getEventId() + "",
                        updatedEvent.getHost().getFirstname(),
                        updatedEvent.getHost().getEmail());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException(DaoException.NO_DATABASE_ERROR, e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException(DaoException.DATA_INTEGRITY_ERROR, e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException(DaoException.BADLY_FORMED_ERROR, e);
        }

        return updatedEvent;

    }

    @Override
    public int deleteEventById(int id) {
        int numberOfRows = 0;
        String sql = "DELETE FROM events WHERE event_id = ?";
        String sqlAttendee = "DELETE FROM attendees WHERE event_id=?";
        String sqlMenus = "DELETE FROM menus WHERE event_id=?";

        try {
            jdbcTemplate.update(sqlAttendee, id);
            jdbcTemplate.update(sqlMenus, id);
            numberOfRows = jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException(DaoException.NO_DATABASE_ERROR, e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException(DaoException.DATA_INTEGRITY_ERROR, e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException(DaoException.BADLY_FORMED_ERROR, e);
        }
        return numberOfRows;
    }

    private Map<Integer, MenuItem> getMenu(int eventId) {
        Map<Integer, MenuItem> items = new HashMap<>();
        String sql = "SELECT item_id FROM menus WHERE event_id=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            while (results.next()) {
                int id = results.getInt("item_id");
                MenuItem menuItem = menuItemDao.getItemById(id);
                items.put(id, menuItem);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return items;
    }

    private Map<Integer, User> getAttendeesByEventId(int eventId) {
        Map<Integer, User> users = new HashMap<>();
        String sql = "SELECT event_id, user_id FROM attendees WHERE event_id=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if (results.next()) {
                int id = results.getInt("user_id");
                User user = userDao.getUserById(id);
                users.put(id, user);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return users;
    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();
        int eventId = rs.getInt("event_id");
        event.setEventId(eventId);
        event.setTitle(rs.getString("title"));
        event.setDescription(rs.getString("description"));
        event.setLocation(rs.getString("location"));
        event.setImgUrl(rs.getString("img_url"));
        User host = userDao.getUserById(rs.getInt("host_id"));
        User chef = userDao.getUserById(rs.getInt("chef_id"));
        event.setHost(host);
        event.setChef(chef);

        Timestamp timestamp = rs.getTimestamp("event_date");
        if (timestamp != null) {
            event.setEventDate(timestamp.toLocalDateTime());
        }
        event.setMenu(getMenu(eventId));
        event.setAttendeesList(getAttendeesByEventId(eventId));

        return event;
    }

    private void sendInviteEmail(List<String> emailList, String title, LocalDateTime date, String location, String eventId, String hostName, String hostEmail){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(emailList.size() > 0){
            for(String email : emailList){
                String content = EmailService.getContent(email.split("@")[0],
                        title,
                        date.format(formatter),
                        location,
                        eventId,
                        hostName,
                        hostEmail);
                EmailService.sendEmail(content, email);
            }
        }
    }
}



