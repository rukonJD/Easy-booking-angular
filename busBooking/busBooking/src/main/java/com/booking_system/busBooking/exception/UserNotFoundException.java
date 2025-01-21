package com.booking_system.busBooking.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String massage){
        super(massage);
    }
}
