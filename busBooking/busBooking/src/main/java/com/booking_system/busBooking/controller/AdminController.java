package com.booking_system.busBooking.controller;

import com.booking_system.busBooking.dto.BusDto;
import com.booking_system.busBooking.dto.RouteDto;
import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.Routes;
import com.booking_system.busBooking.service.BusServiceImpl;
import com.booking_system.busBooking.service.RouteService;
import com.booking_system.busBooking.service.RouteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value="bus/api")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
     BusServiceImpl busService;
    @Autowired
     RouteServiceImpl routeService;

    @GetMapping("/bus-all")
    public ResponseEntity<List<BusDto>> getAllBus(){
        List<BusDto> busDtoList=busService.getAllBus();
        if (busDtoList.isEmpty()){
            // Return 204 if no buses are found
            return ResponseEntity.noContent().build();
        }
        // Return 200 OK and list of buses
        return ResponseEntity.ok(busDtoList);
    }

    @GetMapping("/bus/{id}")
    public ResponseEntity<BusDto> getBusById(@PathVariable("id") int id){
        try {
            BusDto busDto=busService.getBusById(id);
            return new ResponseEntity<>(busDto,HttpStatus.OK);

        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping("/add-bus/{routeId}")
    public ResponseEntity<BusDto> addBus(@RequestBody Bus bus, @PathVariable("routeId") int routeId){
        try {
            BusDto saveBusDto=busService.addBus(bus,routeId);
            return new  ResponseEntity<>(saveBusDto, HttpStatus.CREATED);

        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/delete-bus/{id}")
    public ResponseEntity<String> removeBus(@PathVariable("id") int id){
        try {
            // Call the service to remove the bus by ID
            String resMessage=busService.removeBus(id);
            return new ResponseEntity<>(resMessage,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-bus/{id}")
    public ResponseEntity<BusDto> updateBus(@PathVariable("id") int id, @RequestBody BusDto busDto){
        busDto.setId(id);
        try {
         BusDto updateBusDto=   busService.updateBus(busDto);
            return new ResponseEntity<>(updateBusDto,HttpStatus.OK);
        } catch (Exception e) {
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//==================routes service==========================
    @GetMapping("/routes")
    public ResponseEntity<List<RouteDto>> getAllRoutes(){
        List<RouteDto> routeDtoList=routeService.getAllRoutes();
        if(routeDtoList.isEmpty()){

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(routeDtoList);
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable("id") int id){
        try {
            // Call service to get route by id
            RouteDto routeDto=routeService.getRoutesById(id);
            return new ResponseEntity<>(routeDto,HttpStatus.OK);
        }catch (IllegalArgumentException e){
            // Handle case when the route is not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-routes")

    public ResponseEntity<RouteDto> addRoutes(@RequestBody Routes routes){
        try {
            // Call the service to add the new route
            RouteDto routeDto=routeService.addRoutes(routes);
            return new ResponseEntity<>(routeDto,HttpStatus.CREATED);

        }catch (Exception e){
            // Handle any unexpected errors
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @DeleteMapping("/delete-routes/{id}")
    public ResponseEntity<String> removeRoutes(@PathVariable("id") int id){
        try {
            // Call the service to remove the bus by ID
            String resMessage=routeService.removeRoutes(id);
            return new ResponseEntity<>(resMessage,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-route/{id}")
    public ResponseEntity<String> updateRoutes(@PathVariable("id") int id,@RequestBody RouteDto routeDto){
          routeDto.setId(id);
        try {
            routeService.updateRoutes(routeDto);
            return ResponseEntity.ok("{}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Routes not found");

        }
    }
}
