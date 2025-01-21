package com.booking_system.busBooking.mapper;

import com.booking_system.busBooking.dto.UserDto;
import com.booking_system.busBooking.dto.UserDto2;
import com.booking_system.busBooking.entity.User;

public class UserMapper2 {
// convert user into userDto2
    public static UserDto2 mapToUserDto(User user){
        UserDto2 userDto2=new UserDto2(

                user.getEmail(),
                user.getPassword()

        );
        return userDto2;
    }

    // convert UserDto into User JPA Entity
    public static User mapToUserEntity(UserDto2 userDto2){
        User user=new User(


                userDto2.getEmail(),
                userDto2.getPassword()

        );
        return user;
    }
}
