package com.booking_system.busBooking.mapper;

import com.booking_system.busBooking.dto.UserDtoPhone;
import com.booking_system.busBooking.entity.User;

public class UserMapperPhone {
    //convert user into userDtoPhone
    public static UserDtoPhone toUserDtoPhone(User user){
        UserDtoPhone userDtoPhone=new UserDtoPhone(
                user.getEmail(),
                user.getPhone()
        );
        return userDtoPhone;
    }

    //convert userDtoPhone into user Entity
    public static User toUserEntity(UserDtoPhone userDtoPhone){
        User user=new User(
                userDtoPhone.getEmail(),
                userDtoPhone.getNewPhone()
        );
        return user;
    }
}
