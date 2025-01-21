package com.booking_system.busBooking.mapper;

import com.booking_system.busBooking.dto.UserDto;
import com.booking_system.busBooking.entity.User;

public class UserMapper {
    // Convert User JPA Entity into UserDto

    public static UserDto mapToUserDto(User user){
        UserDto userDto=new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getSex(),
                user.getPhone(),
                user.getCity(),
                user.getState(),
               user.getPin(),
                user.getRole(),
                user.getTickets(),
                user.getFeedbacks()
        );
        return userDto;
    }

    // convert UserDto into User JPA Entity
    public static User mapToUserEntity(UserDto userDto){
        User user=new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getSex(),
                userDto.getPhone(),
                userDto.getCity(),
                userDto.getState(),
                userDto.getPin(),
                userDto.getRole(),
                userDto.getTickets(),
                userDto.getFeedbacks()
        );
        return user;
    }
}
