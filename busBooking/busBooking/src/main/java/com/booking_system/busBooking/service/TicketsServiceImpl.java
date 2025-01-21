package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.TicketsDto;
import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.Passenger;
import com.booking_system.busBooking.entity.Tickets;
import com.booking_system.busBooking.entity.User;
import com.booking_system.busBooking.mapper.TicketsMapper;
import com.booking_system.busBooking.repository.BusRepository;
import com.booking_system.busBooking.repository.TicketsRepository;
import com.booking_system.busBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketsServiceImpl implements TicketsService {
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    BusRepository busRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public TicketsDto bookTickets(Tickets tickets, int busId, int userId) {
        // Check if bus exists
        Bus bus=busRepository.findById(busId).orElseThrow(()->new IllegalArgumentException("Bus with id "+busId+" not found."));
        // Check if user exists
        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User with id "+userId+" not found."));

        // Set bus and customer for the ticket
        tickets.setBusId(bus);
        tickets.setUserId(user);
        tickets.setBookedDate(new Date());

        // Save the ticket to the database
        Tickets saveTickets=ticketsRepository.save(tickets);
        // Return the saved ticket details as a DTO
        return TicketsMapper.toTicketsDto(saveTickets);
    }

    @Override
    public TicketsDto cancelTickets(int ticketId) {
        Optional<Tickets> optionalTickets=ticketsRepository.findById(ticketId);
        if(!optionalTickets.isPresent()){
            throw new RuntimeException("Ticket not found with id "+ticketId);
        }

        Tickets tickets=optionalTickets.get();

        tickets.setStatus("Cancelled");

        ticketsRepository.save(tickets);


        return new TicketsDto(tickets.getId(),
                tickets.getBookedDate(),
                tickets.getOwnerName(),
                tickets.getOwnerPhoneNo(),
                tickets.getFare(),
                tickets.getNoOfSeats(),
                tickets.getStatus(),
                tickets.getPaymentMode(),
                tickets.getBusId(),
                tickets.getUserId(),
                tickets.getPassengers()


                   );
    }

    @Override
    public List<TicketsDto> getTicketsOfUser(int userId) {
        List<Tickets> tickets=ticketsRepository.findByUserId(userId);
        if(tickets.isEmpty()){
            return new ArrayList<>();
        }

        return tickets.stream().map(tickets1 -> new TicketsDto(
                tickets1.getId(),
                tickets1.getBookedDate(),
                tickets1.getOwnerName(),
                tickets1.getOwnerPhoneNo(),
                tickets1.getFare(),
                tickets1.getNoOfSeats(),
                tickets1.getStatus(),
                tickets1.getPaymentMode(),
                tickets1.getBusId(),
                tickets1.getUserId(),
                tickets1.getPassengers()
        )).collect(Collectors.toList());

    }

    @Override
    public List<TicketsDto> getTicketsOfBus(int busId, String date) {
        //convert to date string to date object
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate;
        try {
            parseDate=simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd");
        }

        List<Tickets> ticketsList=ticketsRepository.findByBusIdAndDate(busId,parseDate);
        if (ticketsList.isEmpty()){
            return new ArrayList<>();
        }

        return ticketsList.stream().map(ticket -> new TicketsDto(
                ticket.getId(),
                ticket.getBookedDate(),
                ticket.getOwnerName(),
                ticket.getOwnerPhoneNo(),
                ticket.getFare(),
                ticket.getNoOfSeats(),
                ticket.getStatus(),
                ticket.getPaymentMode(),
                ticket.getBusId(),
                ticket.getUserId(),
                ticket.getPassengers()
        )).collect(Collectors.toList());



    }
}
