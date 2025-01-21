package com.booking_system.busBooking.dto;

import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.Passenger;
import com.booking_system.busBooking.entity.PaymentType;
import com.booking_system.busBooking.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketsDto {
    private int id;

    private Date bookedDate;
    private String ownerName;
    private transient String ownerPhoneNo;
    private float fare;
    private int noOfSeats;
    private String status;

    private PaymentType paymentMode;

    private Bus busId;

    private User userId;

    private List<Passenger> passengers=new ArrayList<Passenger>();
}
