package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.TicketsDto;
import com.booking_system.busBooking.entity.Tickets;

import java.util.List;

public interface TicketsService {
  public TicketsDto bookTickets(Tickets tickets, int busId, int userId);
  public TicketsDto cancelTickets(int ticketId);
  public List<TicketsDto> getTicketsOfUser(int userId);
  public List<TicketsDto> getTicketsOfBus(int busId,String date);


}
