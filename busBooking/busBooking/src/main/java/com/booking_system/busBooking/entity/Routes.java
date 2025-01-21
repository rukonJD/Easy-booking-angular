package com.booking_system.busBooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "routes")
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String source;

    @Enumerated(EnumType.STRING)
    @Column(length = 25, nullable = false)
    private DivisionType sourceState;

    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(length = 25, nullable = false)
    private DivisionType destinationState;

    @JsonIgnore
    @OneToMany(mappedBy = "routeId", orphanRemoval = true)
    @JsonManagedReference
    private List<Bus> buses=new ArrayList<Bus>();
}
