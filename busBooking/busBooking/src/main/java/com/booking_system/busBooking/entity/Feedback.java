package com.booking_system.busBooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private byte rating;
    @Column(length = 500)
    private String comment;
    @ManyToOne
    @JoinColumn(name="busId")
    private Bus busId;
    @ManyToOne
    @JoinColumn(name="userId")
    private User userId;
}
