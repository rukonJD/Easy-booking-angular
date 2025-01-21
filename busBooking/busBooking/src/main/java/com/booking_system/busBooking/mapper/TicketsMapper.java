package com.booking_system.busBooking.mapper;

import com.booking_system.busBooking.dto.TicketsDto;
import com.booking_system.busBooking.entity.Tickets;

public class TicketsMapper {
    //convert tickets Entity into ticketsDto
    public static TicketsDto toTicketsDto(Tickets tickets){
        TicketsDto ticketsDto=new TicketsDto(
                tickets.getId(),
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
        return ticketsDto;
    }

    //convert ticketsDto into ticketsEntity
    public static Tickets toTicketsEntity(TicketsDto ticketsDto){
        Tickets tickets=new Tickets(
                ticketsDto.getId(),
                ticketsDto.getBookedDate(),
                ticketsDto.getOwnerName(),
                ticketsDto.getOwnerPhoneNo(),
                ticketsDto.getFare(),
                ticketsDto.getNoOfSeats(),
                ticketsDto.getStatus(),
                ticketsDto.getPaymentMode(),
                ticketsDto.getBusId(),
                ticketsDto.getUserId(),
                ticketsDto.getPassengers()
        );
        return tickets;
    }
}
