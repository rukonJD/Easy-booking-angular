package com.booking_system.busBooking.dto;

import com.booking_system.busBooking.entity.Bus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatsDto {

    private int id;

    private int seatNo;

    private boolean isBooked;

    private LocalDate bookDate; // Date the seat was booked

    private Byte availableSeats; // Number of available seats

    private Bus busId;          // ID of the bus


}


