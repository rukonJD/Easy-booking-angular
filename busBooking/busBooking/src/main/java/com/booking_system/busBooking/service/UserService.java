package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.UserDto;
import com.booking_system.busBooking.dto.UserDto2;
import com.booking_system.busBooking.entity.User;

public interface UserService {

    public UserDto validateUser(String email, String pass);
    public UserDto2 validateUser2(String email, String pass);

    public UserDto getUser(String email);

    public UserDto registerUser(UserDto userDto);

    public User updatePassword(String email, String newPass);

    public void updateUser(UserDto userDto);

    public void updatePhone(String email, Long newPhone);
}
