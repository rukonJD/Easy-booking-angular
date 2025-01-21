package com.booking_system.busBooking.repository;

import com.booking_system.busBooking.entity.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Routes,Integer> {

    Routes findBySourceAndDestination(String source,String destination);

}
