package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.SeatsDto;
import com.booking_system.busBooking.entity.SeatRequest;
import com.booking_system.busBooking.entity.Seats;

import java.util.List;

public interface SeatsService {
   public List<SeatsDto> getSeatsByBusId(int busId, String date);
   public String bookSeatsbyBus(int busId,String date,int noOfSeats);


   public SeatsDto bookSeats(int busId, String bookDate, int seatNo);


//   String bookSeatsbyBus(int busId, String date, int noOfSeats, List<SeatRequest> seatRequests);

//   String bookSeatsbyBus(int busId, String date, int noOfSeats, List<SeatRequest> seatRequests);

   public String addSeatsbyBus(int busId, String date, int noOfseats);
   public  String removeSeatsByBus(int busId,String date);
}
