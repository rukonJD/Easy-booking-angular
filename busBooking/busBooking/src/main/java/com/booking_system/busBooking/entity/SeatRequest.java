package com.booking_system.busBooking.entity;

public class SeatRequest {
    private int seatId;
    private String label;

    // Constructors, Getters and Setters
    public SeatRequest(int seatId, String label) {
        this.seatId = seatId;
        this.label = label;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

