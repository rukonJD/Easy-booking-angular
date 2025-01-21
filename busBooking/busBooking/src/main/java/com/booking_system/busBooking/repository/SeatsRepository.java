package com.booking_system.busBooking.repository;

import com.booking_system.busBooking.dto.SeatsDto;
import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.SeatState;
import com.booking_system.busBooking.entity.Seats;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatsRepository extends JpaRepository<Seats,Integer> {

    @Query("select s from Seats s where s.busId.id=:busId and s.bookDate=:date")
    List<Seats> findByBusAndDate(@Param("busId")int busId, @Param("date") LocalDate date);

    @Query("select s from Seats s where s.busId.id=:busId and s.bookDate=:date")
    List<Seats> findByBusIdAndBookDate(@Param("busId") int busId, @Param("date") LocalDate date);

    @Query("select s from Seats s where s.busId.id=:busId and s.bookDate=:date")
    Seats findByBusIdAndRemoveDate(@Param("busId") int busId, @Param("date") LocalDate date);

    List<Seats> findByBusId(Bus busId);

    @Modifying
    @Transactional
    @Query("UPDATE Seats s SET s.availableSeats = :availableSeats WHERE s.id = :id")
    int updateSeatAvailability(@Param("id") int id, @Param("availableSeats") byte availableSeats);



//    @Query("SELECT s FROM Seats s WHERE s.busId.id = :busId AND s.bookDate = :bookDate AND s.state = :state")
//    List<Seats> findSeatsByBusAndDateAndState(@Param("busId") int busId,
//                                              @Param("bookDate") LocalDate bookDate,
//                                              @Param("state") int noOfSeats);


}
