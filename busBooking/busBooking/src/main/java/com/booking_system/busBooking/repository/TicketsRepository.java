package com.booking_system.busBooking.repository;

import com.booking_system.busBooking.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets,Integer> {
        @Query("select t from Tickets t where t.userId.id = :userId")
    List<Tickets> findByUserId(@Param("userId")int userId);

        @Query("select t from Tickets t where t.busId.id = :busId and t.bookedDate=:date")
    List<Tickets> findByBusIdAndDate(@Param("busId")int busId, @Param("date")Date date);

}
