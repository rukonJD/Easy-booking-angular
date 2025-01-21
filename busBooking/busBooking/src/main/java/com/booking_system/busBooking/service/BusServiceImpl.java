package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.BusDto;
import com.booking_system.busBooking.dto.SeatsDto;
import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.DayFromDate;
import com.booking_system.busBooking.entity.Routes;
import com.booking_system.busBooking.entity.Seats;
import com.booking_system.busBooking.mapper.BusMapper;
import com.booking_system.busBooking.mapper.SeatsMapper;
import com.booking_system.busBooking.repository.BusRepository;
import com.booking_system.busBooking.repository.RouteRepository;
import com.booking_system.busBooking.repository.SeatsRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class BusServiceImpl implements BusService {


    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SeatsRepository seatsRepository;

    @Override
    public List<BusDto> getAllBus() {
        List<Bus> getAll = busRepository.findAll();

        return getAll.stream().map(BusMapper::toBusDto).collect(Collectors.toList());
    }

    @Override
    public BusDto getBusById(int id) {
        Optional<Bus> optionalBus = busRepository.findById(id);
        if (optionalBus.isPresent()) {
            Bus bus = optionalBus.get();
            return BusMapper.toBusDto(bus);
        } else {
            throw new IllegalArgumentException("Bus with Id " + id + "Not Found");
        }

    }

    @Override
    public BusDto addBus(Bus bus, int routeId) {
        // Fetch the Route based on the routeId
        Routes routes = routeRepository.findById(routeId).orElseThrow(() -> new IllegalArgumentException("Route not found with id " + routeId));
        // Set the route to the bus
        bus.setRouteId(routes);
        // Save the bus to the database
        Bus saveBus = busRepository.save(bus);
        // Return the BusDto of the saved bus

        return BusMapper.toBusDto(saveBus);
    }

    @Override
    public String removeBus(int id) {
        // Check if the bus exists
        if (busRepository.existsById(id)) {
            // Delete the bus by its ID
            busRepository.deleteById(id);

            return "Bus with Id " + id + " has been remove Successfully.";
        } else {
            // If the bus doesn't exist, throw an exception
            throw new IllegalArgumentException("Bus with id " + id + " Not Found.");
        }


    }



//    @Override
//    public List<BusDto> getBusByRoutes(String source, String destination, String date) {
//        System.out.println("In get Bus By Routes");
//        System.out.println(source);
//        System.out.println(destination);
//        System.out.println(date);
//
//        // Determine the day from the date if needed
//        int day= DayFromDate.getDayInt(date);
//
//        // Find the route by source and destination
//        Routes routes=routeRepository.findBySourceAndDestination(source,destination);
//
//        // Alternatively, use a custom method in the repository if available
////        Routes route= busRepository.findBusesBySourceAndDestination(source,destination);
//
//        if(routes==null){
//            // handle the case when the route was not found
//            System.out.println("Route not found");
//            return Collections.emptyList();
//        }
//
//        // Fetch buses by routes
//        List<Bus> buses=busRepository.findByRouteId(routes);
//
//        if (buses.isEmpty()) {
//            System.out.println("No buses available for the route");
//            return Collections.emptyList();
//        }
//
//        //Convert each bus entity to busDto
//
//        List<BusDto> busDtos=buses.stream().map(BusMapper::toBusDto).collect(Collectors.toList());
//        System.out.println(busDtos);
//
//
//        return busDtos;
//    }



    @Override
    public List<BusDto> getBusByRoutes(String source, String destination, String date) {
        System.out.println("In get Bus By Routes");
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Date: " + date);

        // Determine the day from the date if needed
        int day = DayFromDate.getDayInt(date);

        // Find the route by source and destination
        Routes routes = routeRepository.findBySourceAndDestination(source, destination);

        // Handle case when the route is not found
        if (routes == null) {
            System.out.println("Route not found");
            return Collections.emptyList();
        }

        // Fetch buses based on the found route
        List<Bus> buses = busRepository.findByRouteId(routes);

        // For each bus, fetch the seats and map to BusDto
        List<BusDto> busDtos = buses.stream().map(bus -> {
            // Convert Bus entity to BusDto
            BusDto busDto = BusMapper.toBusDto(bus);

            // Fetch seats for the current bus
            List<Seats> seats = seatsRepository.findByBusId(bus);

            // Map seats to SeatsDto
            List<SeatsDto> seatsDtos = SeatsMapper.toSeatsDto(seats);

            // Add seats to the BusDto
            busDto.setSeats(seatsDtos);

            return busDto;
        }).collect(Collectors.toList());

        System.out.println(busDtos);
        return busDtos;
    }




    @Override
    public BusDto updateBus(BusDto busDto) {

        Optional<Bus> optionalBus=busRepository.findById(busDto.getId());
        if (optionalBus.isPresent()){

            Bus bus=optionalBus.get();

            bus.setBusNo(busDto.getBusNo());
            bus.setArrivalDate(busDto.getArrivalDate());
            bus.setDestinationDate(busDto.getDestinationDate());
            bus.setCapacity(busDto.getCapacity());


            

            bus.setPrice(busDto.getPrice());
            bus.setType(busDto.getType());
            bus.setSeats(busDto.getSeats());
            bus.setTickets(busDto.getTickets());
            bus.setFeedbacks(busDto.getFeedbacks());

            if(busDto.getCapacity()<=0){
                throw new IllegalArgumentException("Capacity must be grater than zero");
            }

            busRepository.save(bus);

            BusDto updateBusDto=BusMapper.toBusDto(bus);
            return updateBusDto;

        }else {
            throw new RuntimeException("Bus not found with this id "+busDto.getId());
        }



    }













//    @Override
//        public List<BusDto> getBusByRoutes(String source, String destination, Date arriveDate) {
//            // Fetch the list from the repository (no need to filter again in service if repository handles it)
//            List<Bus> buses = busRepository.findBySourceAndDestinationAndDate(source, destination, arriveDate);
//            if (buses == null) {
//                return new ArrayList<>();
//            }
//
//            // Convert Bus entities to BusDto
//            return buses.stream()
//                    .map(bus -> new BusDto(
//                            bus.getId(),
//                            bus.getBusNo(),
//                            bus.getCapacity(),
//                            bus.getType(),
//                            bus.getSource(),
//                            bus.getDestination(),
//                            bus.getArrivalDate(),
//                            bus.getDestinationDate(),
//                            bus.getPrice(),
//                            bus.getRoutes(),
//                            bus.getFrequency(),
//                            bus.getDate(),
//                            bus.getSeats(),
//                            bus.getTickets(),
//                            bus.getFeedbacks() != null ? bus.getFeedbacks() : new ArrayList<>()))
//                    .collect(Collectors.toList());
//
//    }










}



