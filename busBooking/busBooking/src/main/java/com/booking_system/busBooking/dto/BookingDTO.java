package com.booking_system.busBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private int seatId;
    private String seatLabel;
    private float price;
    private String busNo;
    private String operator;
}
