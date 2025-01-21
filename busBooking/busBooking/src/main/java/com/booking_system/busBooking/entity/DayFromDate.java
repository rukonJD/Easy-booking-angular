package com.booking_system.busBooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayFromDate {

    private static String[] day= {"sun", "mon", "tues", "wed", "thurs", "fri", "sat"};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "event_date")
    private Date eventDate;

    @Transient // We mark this as a transient property to prevent it from being persisted.
    private String dayOfWeek;

    public static int getDayInt(String date) {
        // Trim any leading or trailing spaces from the date string
        date = date.trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Use java.time.LocalDate to parse the date and get the day of the week
        LocalDate localDate=LocalDate.parse(date,formatter);
        return localDate.getDayOfWeek().getValue();
    }
}
