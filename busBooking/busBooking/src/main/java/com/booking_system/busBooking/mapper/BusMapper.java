package com.booking_system.busBooking.mapper;

import com.booking_system.busBooking.dto.BusDto;
import com.booking_system.busBooking.entity.Bus;

public class BusMapper {

    //convert busEntity into busDto
    public static BusDto toBusDto(Bus bus){
        BusDto busDto=new BusDto(
                bus.getId(),
                bus.getBusNo(),
                bus.getOperator(),
                bus.getCapacity(),
                bus.getType(),

                bus.getArrivalDate(),
                bus.getDestinationDate(),
                bus.getPrice(),
                bus.getRouteId(),
                bus.getFrequency(),

                bus.getSeats(),
                bus.getTickets(),
                bus.getFeedbacks()
        );
        return busDto;
    }

    //convert busDto into bus Entity
    public static Bus toBusEntity(BusDto busDto){
        Bus bus=new Bus(
                busDto.getId(),
                busDto.getBusNo(),
                busDto.getOperator(),
                busDto.getCapacity(),
                busDto.getType(),

                busDto.getArrivalDate(),
                busDto.getDestinationDate(),
                busDto.getPrice(),

                busDto.getRouteId(),
                busDto.getFrequency(),

                busDto.getSeats(),
                busDto.getTickets(),
                busDto.getFeedbacks()
        );
        return bus;
    }

}
