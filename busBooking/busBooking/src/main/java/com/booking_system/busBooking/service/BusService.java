package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.BusDto;
import com.booking_system.busBooking.entity.Bus;

import java.util.Date;
import java.util.List;

public interface BusService {
  public List<BusDto> getAllBus();
  public BusDto getBusById(int id);
  public BusDto addBus(Bus bus,int routeId);
  public String removeBus(int id);
  public BusDto updateBus(BusDto busDto);
  public List<BusDto> getBusByRoutes(String source, String destination, String date);




}
