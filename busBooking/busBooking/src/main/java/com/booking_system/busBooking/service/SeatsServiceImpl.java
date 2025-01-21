package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.SeatsDto;
import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.SeatRequest;
import com.booking_system.busBooking.entity.Seats;
import com.booking_system.busBooking.mapper.SeatsMapper;
import com.booking_system.busBooking.repository.BusRepository;
import com.booking_system.busBooking.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatsServiceImpl implements SeatsService {

    @Autowired
    SeatsRepository seatsRepository;
    @Autowired
    BusRepository busRepository;



    @Override
    public List<SeatsDto> getSeatsByBusId(int busId, String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parseDate;

        try {
            // Parse the string date into LocalDate
            parseDate = LocalDate.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            // If the date format is incorrect, throw an exception
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd");
        }
//        Seats bus=seatsRepository.findByBusIdAndRemoveDate(busId,parseDate);
        Bus bus = busRepository.findByBusNoAndSeats_BookDate(busId);

        if (bus == null) {
            throw new IllegalArgumentException("Bus not found or no seats available for the given date.");
        }

        return bus.getSeats().stream()
//                .filter(seat -> seat.isBooked())
                .map(seat -> {
                    SeatsDto seatDTO = new SeatsDto();
                    seatDTO.setSeatNo(seat.getSeatNo());
                    seatDTO.setBooked(seat.isBooked());
                    seatDTO.setBookDate(seat.getBookDate());
                    return seatDTO;
                })
                .collect(Collectors.toList());

    }

    @Override
    public String bookSeatsbyBus(int busId, String date, int noOfSeats) {
        // Convert the date string to LocalDate
        LocalDate bookDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        // Fetch seats based on busId and bookDate (returns a list of seats)
        List<Seats> seatsList = seatsRepository.findByBusAndDate(busId, bookDate);

        // Check if there are no seats available
        if (seatsList.isEmpty()) {
            return "No seats available for the selected bus and date.";
        }

        // Assuming only one row for the given busId and date (handle the first seat in the list)
        Seats seat = seatsList.get(0);

        // Check if there are enough available seats
        if (noOfSeats <= seat.getAvailableSeats()) {
            // Update the available seats
            seat.setAvailableSeats((byte) (seat.getAvailableSeats() - noOfSeats));

            // Update the seat in the database using the repository method
            seatsRepository.updateSeatAvailability(seat.getId(), seat.getAvailableSeats());

            return "Seats booked successfully!";
        } else {
            return "Not enough seats available.";
        }

    }




//    @Transactional
//    public String bookSeatsbyBus(int busId, String date, int noOfSeats) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate parseDate;
//        // Parse the date
//        try {
//            parseDate = LocalDate.parse(date, dateTimeFormatter);
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
//        }
//
//        // Fetch all the seats for the given bus and date
//        List<Seats> seats = seatsRepository.findByBusIdAndBookDate(busId, parseDate);
//
//
//
//        if (seats.isEmpty()) {
//            return "No seats available for the given bus and date";
//        }
//
//        int availableSeats = 0;
//
//
//
//        // Calculate available seats
//        availableSeats = 0;
////        for (Seats seat : seats) {
////            if (seat.getState() == SeatState.AVAILABLE) {
////                availableSeats++;
////            }
////        }
//        // Calculate the number of available seats
////        availableSeats = (int) seats.stream()
////                .filter(seat -> seat.getState() == SeatState.AVAILABLE)
////                .count();
//
//        // Check if there are enough available seats
//        if (availableSeats < noOfSeats) {
//            return "Not enough available seats";
//        }
//        int bookedSeats = 0;
//
//
////        for (Seats seat : seats) {
////            if (seat.getState() == SeatState.AVAILABLE && bookedSeats < noOfSeats) {
////                seat.setState(SeatState.SOLD);
////               // Mark the seat as sold
////                bookedSeats++;
////
////                // Optionally print which seat is sold
////                System.out.println("Seat " + seat.getLabel() + " is now SOLD");
//
//                // Save the seat to the database with the updated state
////                seatsRepository.save(seat);
////            }
////        }
////
////        // Save the updated seats in the database
////        seatsRepository.saveAll(seats);
////        // Return success or error message
////        return bookedSeats == noOfSeats ? "Successfully booked seats" : "Not enough available seats";
////    }




//




            @Override
            public String addSeatsbyBus(int busId, String date, int noOfseats) {
                DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parseDate;
                try {
                    parseDate=LocalDate.parse(date,dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format. use yyyy-MM-dd");
                }

                Optional<Bus> optionalBus=busRepository.findById(busId);
                if (optionalBus.isEmpty()){
                    return "Bus not found";
                }

                Bus bus=optionalBus.get();


                List<Seats> optionalSeats=seatsRepository.findByBusIdAndBookDate(busId,parseDate);
                if (optionalSeats.isEmpty()){
                    Seats newSeats=new Seats();
                    newSeats.setBusId(bus);
                    newSeats.setBookDate(parseDate);
                    newSeats.setAvailableSeats((byte) noOfseats);

                    seatsRepository.save(newSeats);
                    return String.format("Successfully added %d seats to bus %d for the date %s",noOfseats,busId,date);
                }

                // If seat record exists, update available seats
                Seats seats=optionalSeats.get(busId);
                seats.setAvailableSeats((byte) (seats.getAvailableSeats() + noOfseats));

                seatsRepository.save(seats);
                return String.format("Successfully added %d seats to bus %d for the date %s ",noOfseats,busId,parseDate);
            }

            @Override
            public String removeSeatsByBus(int busId, String date) {
                DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parseDate;
                try {
                    parseDate=LocalDate.parse(date,dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format. use yyyy-MM-dd");
                }

                Optional<Bus> optionalBus=busRepository.findById(busId);
                if (!optionalBus.isPresent()){
                    return "Bus not found";

                }

                Bus bus=optionalBus.get();
                Seats seats=seatsRepository.findByBusIdAndRemoveDate(busId,parseDate);
                if (seats==null){
                    return "No seats found for the given bus and date";
                }else {
                    seatsRepository.delete(seats);
                }

                return "Seates Successfully removed";
            }

    @Override
    public SeatsDto bookSeats(int busId, String bookDate, int seatNo) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate parseDate;
//
//        try {
//            // Parse the string date into LocalDate
//            parseDate = LocalDate.parse(bookDate, dateTimeFormatter);
//        } catch (DateTimeParseException e) {
//            // If the date format is incorrect, throw an exception
//            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd");
//        }

        //Bus bus = busRepository.findByBusNoAndSeats_BookDate(busId, parseDate);
        Bus bus = busRepository.findByBusNoAndSeats_BookDate(busId);
        if (bus == null) {
            throw new IllegalArgumentException("Bus not found or no available seats for the given date.");
        }

        Seats seat = bus.getSeats().stream()
                .filter(s -> s.getSeatNo() == seatNo && !s.isBooked())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Seat not available for booking"));

        seat.setBooked(true);
        seatsRepository.save(seat);

        SeatsDto seatDTO = new SeatsDto();
        seatDTO.setSeatNo(seat.getSeatNo());
        seatDTO.setBooked(seat.isBooked());
        seatDTO.setBookDate(seat.getBookDate());

        return seatDTO;
    }



        }

