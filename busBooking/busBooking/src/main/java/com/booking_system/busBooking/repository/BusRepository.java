package com.booking_system.busBooking.repository;

import com.booking_system.busBooking.entity.Bus;
import com.booking_system.busBooking.entity.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {

    // Custom query to find buses by route id
    List<Bus> findByRouteId(Routes route);

    // Alternatively, use @Query for more complex queries
    @Query("SELECT b from Bus b where b.routeId.source=:source And b.routeId.destination=:destination")
    Routes findBusesBySourceAndDestination(@Param("source") String source,
                                         @Param("destination") String destination);

    Optional<Bus> findByBusNo(String busNo);

    //@Query("SELECT b FROM Bus b JOIN b.seats s WHERE b.id = :id AND s.bookDate = :bookDate")
    //Bus findByBusNoAndSeats_BookDate1(int id, LocalDate bookDate);

    @Query("SELECT b FROM Bus b JOIN b.seats s WHERE b.id = :id")
    Bus findByBusNoAndSeats_BookDate(int id);


}
