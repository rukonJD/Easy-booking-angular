package com.booking_system.busBooking.mapper;

import com.booking_system.busBooking.dto.RouteDto;
import com.booking_system.busBooking.entity.Routes;

public class RouteMapper {

    // convert route Entity to routeDto
    public static RouteDto toRouteDto(Routes routes){
        RouteDto routeDto=new RouteDto(
                routes.getId(),
                routes.getSource(),
                routes.getSourceState(),
                routes.getDestination(),
                routes.getDestinationState(),
                routes.getBuses()
        );
        return routeDto;
    }

    //convert routeDto into routeEntity
    public static Routes toRouteEntity(RouteDto routeDto){
        Routes routes=new Routes(
                routeDto.getId(),
                routeDto.getSource(),
                routeDto.getSourceState(),
                routeDto.getDestination(),
                routeDto.getDestinationState(),
                routeDto.getBuses()
        );
        return routes;
    }
}
