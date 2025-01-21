package com.booking_system.busBooking.mapper;

import com.booking_system.busBooking.dto.SeatsDto;
import com.booking_system.busBooking.entity.Seats;

import java.util.List;
import java.util.stream.Collectors;

public class SeatsMapper {

    // Convert a single Seats entity into a SeatsDto
    public static SeatsDto toSeatsDto(Seats seats) {
        return new SeatsDto(
                seats.getId(),
                seats.getSeatNo(),
                seats.isBooked(),
                seats.getBookDate(),
                seats.getAvailableSeats(),
                seats.getBusId()
        );
    }

    // Convert a list of Seats entities into a list of SeatsDto
    public static List<SeatsDto> toSeatsDto(List<Seats> seatsList) {
        return seatsList.stream()
                .map(SeatsMapper::toSeatsDto)  // Convert each Seats entity to a SeatsDto
                .collect(Collectors.toList());
    }

    // Convert a single SeatsDto into a Seats entity
    public static Seats toSeatsEntity(SeatsDto seatsDto) {
        return new Seats(
                seatsDto.getId(),
                seatsDto.getSeatNo(),
                seatsDto.isBooked(),
                seatsDto.getBookDate(),
                seatsDto.getAvailableSeats(),
                seatsDto.getBusId()
        );
    }

    // Convert a list of SeatsDto into a list of Seats entities
    public static List<Seats> toSeatsEntities(List<SeatsDto> seatsDtoList) {
        return seatsDtoList.stream()
                .map(SeatsMapper::toSeatsEntity)  // Convert each SeatsDto to a Seats entity
                .collect(Collectors.toList());
    }
}
