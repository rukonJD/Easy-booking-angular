package com.booking_system.busBooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "daywhenruns")
public class DayWhenRuns {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean sun;
    private boolean mon;
    private boolean tues;
    private boolean wed;
    private boolean thus;
    private boolean fri;
    private boolean sat;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="busId")
    private Bus busId;


    public boolean isRuns(int day) {
        System.out.println("day = " + day);
        if( day==0 )
            return this.sun;
        if(day==1)
            return this.mon;
        if(day==2)
            return this.tues;
        if(day==3)
            return this.wed;
        if(day==4)
            return this.thus;
        if(day==5)
            return this.fri;
        if(day==6)
            return this.sat;
        return false;
    }

}
