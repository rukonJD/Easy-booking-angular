package com.booking_system.busBooking.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bus {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    private String busNo;

    @Column(length = 30, nullable = false)
    private String operator;



    @Column( nullable = false)
    private Byte capacity;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private BusType type;




    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Dhaka")
    @Column(nullable = false)
    private Date arrivalDate;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Dhaka")
    @Column(nullable = false)
    private Date destinationDate;

    @Column(nullable = false)
    private float price;



    @ManyToOne
    @JoinColumn(name = "routeId", nullable = false)
    @JsonBackReference
    private Routes routeId;

    @OneToOne(mappedBy = "busId", cascade = CascadeType.ALL, orphanRemoval = true)
    private DayWhenRuns frequency;



    @JsonIgnore
    @OneToMany(mappedBy = "busId", cascade = CascadeType.ALL)
    private List<Seats> seats = new ArrayList<Seats>();

    @JsonIgnore
    @OneToMany(mappedBy = "busId", cascade = CascadeType.ALL)
    private List<Tickets> tickets = new ArrayList<Tickets>();

    @JsonIgnore
    @OneToMany(mappedBy = "busId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<Feedback>();



}
