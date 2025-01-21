package com.booking_system.busBooking.dto;

import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.DivisionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
    private int id;
    private String source;


    private DivisionType sourceState;
    private String Destination;

    private DivisionType DestinationState;

    private List<Bus> buses=new ArrayList<Bus>();
}
