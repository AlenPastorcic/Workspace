package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDao;
import com.techelevator.reservations.dao.MemoryHotelDao;
import com.techelevator.reservations.dao.MemoryReservationDao;
import com.techelevator.reservations.dao.ReservationDao;
import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {

    private HotelDao hotelDao;
    private ReservationDao reservationDao;

    public HotelController() {
        this.hotelDao = new MemoryHotelDao();
        this.reservationDao = new MemoryReservationDao(hotelDao);
    }

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system
     */
    @RequestMapping(path = "/hotels", method = RequestMethod.GET)
    public List<Hotel> listHotels(@RequestParam(required=false) Integer stars,
                                  @RequestParam(required=false) String state,
                                  @RequestParam(required=false) String city) {
        List<Hotel> fullList = hotelDao.getHotelsByStateAndCity(state, city);
        if (stars != null && stars > 0) {
            List<Hotel> filteredList = new ArrayList<>();
            for (Hotel hotel : fullList) {
                if (hotel.getStars() == stars) {
                    filteredList.add(hotel);
                }
            }
            return filteredList;
        }

        return fullList;
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}", method = RequestMethod.GET)
    public Hotel getHotel(@PathVariable int id) {
        return hotelDao.getHotelById(id);
    }

    /**
     * Sample add method
     *
     * @return a + b
     */
    @RequestMapping(path = "/add/{a}/{b}", method = RequestMethod.GET)
    public double addNums(@PathVariable double a, @PathVariable double b) {
        return a + b;
    }

    /**
     * Return list of reservations
     *
     * @return all reservations
     */
    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservations() {
        return reservationDao.getReservations();
    }

    @RequestMapping(path = "/reservations/{id}", method = RequestMethod.GET)
    public Reservation getReservation(@PathVariable int id) {
        return reservationDao.getReservationById(id);
    }

    @RequestMapping(path = "/hotels/{id}/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservationsByHotel(@PathVariable("id") int hotelId) {
        return reservationDao.getReservationsByHotel(hotelId);
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.POST)
    public Reservation addReservation(@RequestBody Reservation newReservation) {
        Reservation returnedReservation = reservationDao.createReservation(newReservation);
        return returnedReservation;
    }



}
