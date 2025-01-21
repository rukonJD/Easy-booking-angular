package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.RouteDto;
import com.booking_system.busBooking.entity.Routes;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface RouteService {

    public List<RouteDto> getAllRoutes();
    public RouteDto getRoutesById(int id);
    public RouteDto addRoutes(Routes routes);
    public String removeRoutes(int id);
    public  String updateRoutes(RouteDto routeDto);
}
