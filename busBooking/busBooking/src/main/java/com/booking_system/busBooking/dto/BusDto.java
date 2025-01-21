package com.booking_system.busBooking.dto;

import com.booking_system.busBooking.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusDto {

    private int id;


    private String busNo;

    private String operator;


    private Byte capacity;


    private BusType type;


    private Date arrivalDate;


    private Date destinationDate;


    private float price;
    



    private Routes routeId;


    private DayWhenRuns frequency;



    private List<Seats> seats = new ArrayList<Seats>();


    private List<Tickets> tickets = new ArrayList<Tickets>();


    private List<Feedback> feedbacks = new ArrayList<Feedback>();


    public void setSeats(List<SeatsDto> seatsDtos) {
    }
}


