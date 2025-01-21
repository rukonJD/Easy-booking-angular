package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.RouteDto;
import com.booking_system.busBooking.entity.Routes;
import com.booking_system.busBooking.mapper.RouteMapper;
import com.booking_system.busBooking.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

        @Autowired
         RouteRepository routeRepository;

    @Override
    public List<RouteDto> getAllRoutes() {
        List<Routes> getAll=routeRepository.findAll();

        return getAll.stream().map(RouteMapper::toRouteDto).collect(Collectors.toList());
    }

    @Override
    public RouteDto getRoutesById(int id) {
        Optional<Routes> optionalRoutes=routeRepository.findById(id);
        if(optionalRoutes.isPresent()){
            // If found, map the Route to RouteDto and return it
            Routes routes=optionalRoutes.get();
            return RouteMapper.toRouteDto(routes);
        }else {
            throw new IllegalArgumentException("Route With Id "+id+" Not Found");
        }

    }

    @Override
    public RouteDto addRoutes(Routes routes) {
        // Save the Route entity to the database
        Routes saveRoutes=routeRepository.save(routes);
        return RouteMapper.toRouteDto(saveRoutes);
    }

    @Override
    public String removeRoutes(int id) {

        // Check if the route exists
        if(routeRepository.existsById(id)){
            // Delete the route by its ID
            routeRepository.deleteById(id);

            return "Route with Id "+id+" has been remove Successfully.";
        }else {
            // If the bus doesn't exist, throw an exception
            throw new IllegalArgumentException("Route with id "+id+" Not Found.");
        }
    }

    @Override
    public String updateRoutes(RouteDto routeDto) {
        Optional<Routes> optionalRoutes=routeRepository.findById(routeDto.getId());
        if (optionalRoutes.isPresent()){
            Routes routes=optionalRoutes.get();

            routes.setSource(routeDto.getSource());
            routes.setSourceState(routeDto.getSourceState());
            routes.setDestination(routeDto.getDestination());
            routes.setDestinationState(routeDto.getDestinationState());

            routeRepository.save(routes);
        }else {
            throw new RuntimeException("Route not found with id "+routeDto.getId());
        }


        return "";
    }
}
