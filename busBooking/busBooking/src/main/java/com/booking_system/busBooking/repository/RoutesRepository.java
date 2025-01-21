package com.booking_system.busBooking.repository;

import com.booking_system.busBooking.entity.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutesRepository extends JpaRepository<Routes,Integer> {

}
