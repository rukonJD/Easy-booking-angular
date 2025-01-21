package com.booking_system.busBooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date bookedDate;
    private String ownerName;
    private transient String ownerPhoneNo;
    private float fare;
    private int noOfSeats;
    private String status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentMode;
    @ManyToOne
    @JoinColumn(name="busId")
    private Bus busId;
    @ManyToOne
    @JoinColumn(name="userId")
    private User userId;
    @JsonIgnore
    @OneToMany(mappedBy = "ticketId",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Passenger> passengers=new ArrayList<Passenger>();

}
