package com.booking_system.busBooking.repository;


import com.booking_system.busBooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByEmail(String email);


}
