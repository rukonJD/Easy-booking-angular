package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.BookingDTO;
import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.SeatState;
import com.booking_system.busBooking.entity.Seats;
import com.booking_system.busBooking.repository.BusRepository;
import com.booking_system.busBooking.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private SeatsRepository seatsRepository;  // Your repository to fetch seat data

    @Autowired
    private BusRepository busRepository;    // Your repository to fetch bus data

//    public BookingDTO bookSeat(int seatId) {
//        // Retrieve the seat by ID
//        Seats seat = seatsRepository.findById(seatId).orElseThrow(() -> new IllegalStateException("Seat not found"));
//
//        // If the seat is already sold or unavailable, throw an exception
//        if (seat.getState() != SeatState.AVAILABLE) {
//            throw new IllegalStateException("This seat is not available for booking.");
//        }
//
//        // Book the seat and update the state
//        seat.bookSeat();  // This will mark it as SOLD and update the availableSeats count.
//
//        // Save the updated seat state to the database
//        seatsRepository.save(seat);
//
//        // Fetch bus details for the booking
//        Bus bus = seat.getBusId();
//
//        // Create and return a BookingDTO with seat and bus details
//        return new BookingDTO(seat.getId(), seat.getLabel(), seat.getPrice(), bus.getBusNo(), bus.getOperator());
//    }
}
