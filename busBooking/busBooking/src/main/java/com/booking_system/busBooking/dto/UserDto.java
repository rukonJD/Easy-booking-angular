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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private SexType sex;
    private Long phone;
    private String city;
    private DivisionType state;
    private int pin;
    private CustomerRoleType role;
    private List<Tickets> tickets = new ArrayList<Tickets>();
    private List<Feedback> feedbacks = new ArrayList<Feedback>();


}
