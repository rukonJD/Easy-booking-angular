package com.booking_system.busBooking.entity;

import java.util.List;

public class BookingRequest {
    private int busId;
    private String date;
    private int noOfSeats;
    private List<SeatRequest> seats;

    // Getters and Setters
    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public List<SeatRequest> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatRequest> seats) {
        this.seats = seats;
    }
}

