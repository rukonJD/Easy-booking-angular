package com.booking_system.busBooking.controller;

import com.booking_system.busBooking.dto.BookingDTO;
import com.booking_system.busBooking.dto.BusDto;
import com.booking_system.busBooking.dto.SeatsDto;
import com.booking_system.busBooking.dto.TicketsDto;
import com.booking_system.busBooking.entity.*;
import com.booking_system.busBooking.repository.SeatsRepository;
import com.booking_system.busBooking.service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.jasperreports.engine.util.JRStyledText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value="customer/api")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    TicketsServiceImpl ticketsService;
    @Autowired
    BusServiceImpl busService;
    @Autowired
    SeatsServiceImpl seatsService;
    @Autowired
    SeatsRepository seatsRepository;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookSeat")
    public ResponseEntity<String> bookSeat(
            @RequestParam int busId,
            @RequestParam String date,
            @RequestParam int noOfSeats) {

        // Call the service to book the seats
        String response = seatsService.bookSeatsbyBus(busId, date, noOfSeats);

        // Return the appropriate response based on booking status
        if ("Seats booked successfully".equals(response)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(400).body(response);  // If not enough seats are available
        }
    }


    @PostMapping("/bookT/{busId}/{userId}")
    public ResponseEntity<TicketsDto> bookTickets(
            @RequestBody Tickets tickets,
            @PathVariable("busId") int busId,
            @PathVariable("userId") int userId) {

        try {
            // Call the service to book the ticket
            TicketsDto ticketsDto = ticketsService.bookTickets(tickets, busId, userId);
            return new ResponseEntity<>(ticketsDto, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            // Handle invalid bus or customer IDs
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            //Handle unexpected error
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cancel/{ticketId}")
    public ResponseEntity<TicketsDto> cancelTickets(@PathVariable int ticketId) {
        try {
            TicketsDto cancelTicketDto = ticketsService.cancelTickets(ticketId);
            return new ResponseEntity<>(cancelTicketDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketsDto>> getTicketsOfUser(@PathVariable("userId") int userId) {
        try {
            List<TicketsDto> ticketsDtos = ticketsService.getTicketsOfUser(userId);
            if (ticketsDtos.isEmpty()) {
                return new ResponseEntity<>(ticketsDtos, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ticketsDtos, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<TicketsDto>> getTicketsOfBus(@PathVariable int busId, @RequestParam String date) {
        try {
            List<TicketsDto> ticketDtos = ticketsService.getTicketsOfBus(busId, date);
            if (ticketDtos.isEmpty()) {
                return new ResponseEntity<>(ticketDtos, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ticketDtos, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }


//    @GetMapping("/search-bus")
//    public ResponseEntity<List<BusDto>> searchBus(@RequestParam String source,
//                                                  @RequestParam String destination,
//                                                  @RequestParam  Date arriveDate){
//
//        try {
//            List<BusDto> buses= busService.getBusByRoutes(source,destination,arriveDate);
//                if(buses.isEmpty()){
//                    return new ResponseEntity<>(buses,HttpStatus.NOT_FOUND);
//                }
//                return new ResponseEntity<>(buses,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    /**
     * Endpoint to get buses based on source, destination, and date.
     *
     * @param source      The source of the bus route.
     * @param destination The destination of the bus route.
     * @param date        The date for which buses are requested (in yyyy-MM-dd format).
     * @return List of BusDto objects.
     */
    @GetMapping("/search-bus")
    public ResponseEntity<List<BusDto>> getBusByRoutes(@RequestParam String source,
                                                       @RequestParam String destination,
                                                       @RequestParam String date) {
        List<BusDto> buses = busService.getBusByRoutes(source, destination, date);
        if (buses.isEmpty()) {
            return new ResponseEntity<>(buses, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }




    @GetMapping("/viewSeats/{busId}")
    public ResponseEntity<List<SeatsDto>> getSeatsbyBus(@PathVariable int busId, @RequestParam String date) {
        try {
            // Call the service method to get the list of seats
            List<SeatsDto> seatsDtoList = seatsService.getSeatsByBusId(busId, date);

            // Return the list of seats in the response body with HTTP status 200 OK
            return new ResponseEntity<>(seatsDtoList, HttpStatus.OK);

        } catch (RuntimeException e) {
            // In case of error, return HTTP 404 (Not Found) with null body
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/seats")
    public ResponseEntity<List<Seats>> getSeatsByBus(
            @RequestParam("busId") int busId,
            @RequestParam("bookDate") String bookDate) {

        // Assuming bookDate is a String or LocalDate, you may need to convert it
        LocalDate date = LocalDate.parse(bookDate);

        // Fetch the seats based on busId and bookDate
        List<Seats> seats = seatsRepository.findByBusAndDate(busId, date);

        if (seats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(seats);
    }




    @PostMapping("/book-seat")
    public SeatsDto bookSeats(
            @RequestParam int busId,
            @RequestParam String bookDate,
            @RequestParam int seatNo) {
        return seatsService.bookSeats(busId, bookDate, seatNo);
    }




//    @PostMapping("/book-seats")
//    public ResponseEntity<Object> bookSeats(
//            @RequestParam int busId,
//            @RequestParam String date,
//            @RequestParam int noOfSeats) {
//        String result = seatsService.bookSeatsbyBus(busId, date, noOfSeats);
//
//        // Return a response with a JSON object
//        if (result.contains("Successfully booked")) {
//            return ResponseEntity.ok(new ApiResponse("success", result));
//        } else {
//            return ResponseEntity.badRequest().body(new ApiResponse("error", result));
//        }
//    }


    @PostMapping("/add-seats")
    public ResponseEntity<String> addSeats(
            @RequestParam int busId,
            @RequestParam String date,
            int noOfSeats) {
        String result = seatsService.addSeatsbyBus(busId, date, noOfSeats);
        if (result.equals("Successfully added %d seats on bus %d for the date %s")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }

    @DeleteMapping("/delete-seats")
    public ResponseEntity<String> removeSeatsbyBus(@RequestParam int busId,@RequestParam String date){
        String result=seatsService.removeSeatsByBus(busId,date);
        if (result.equals("Seates Successfully removed")){
            return new ResponseEntity<>(result,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
    }
}
