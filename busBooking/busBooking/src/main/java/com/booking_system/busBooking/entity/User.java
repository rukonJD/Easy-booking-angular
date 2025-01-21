package com.booking_system.busBooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private SexType sex;

    @Column(length = 15, nullable = false)
    private Long phone;

    @Column(length = 25, nullable = false)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private DivisionType state;

    @Column(nullable = false)
    private int pin;

    // Getters and setters
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private CustomerRoleType role;
    public void setRoleAsString(String roleAsString) {
        this.role = role;
    }


    // Convert the CustomerRoleType to String
    public String getRoleAsString() {
        return this.role != null ? this.role.name() : "UNKNOWN";  // Default value "UNKNOWN" in case the role is null
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tickets> tickets = new ArrayList<Tickets>();

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<Feedback>();




    public User(String email, String password) {
    }

    public User(String email, Long newPhone) {
    }



}
