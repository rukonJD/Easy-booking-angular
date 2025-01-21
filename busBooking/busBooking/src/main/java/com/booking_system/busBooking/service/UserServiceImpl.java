package com.booking_system.busBooking.service;

import com.booking_system.busBooking.dto.UserDto;
import com.booking_system.busBooking.dto.UserDto2;
import com.booking_system.busBooking.dto.UserDtoPhone;
import com.booking_system.busBooking.entity.User;
import com.booking_system.busBooking.exception.UserNotFoundException;
import com.booking_system.busBooking.mapper.UserMapper;
import com.booking_system.busBooking.mapper.UserMapper2;
import com.booking_system.busBooking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
//    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDto validateUser(String email, String pass) {
      Optional<User> optionalUser=userRepository.findByEmail(email);
      if(optionalUser.isPresent()){
          User user =optionalUser.get();
          if(user.getPassword().equals(pass)){
              return UserMapper.mapToUserDto(user);
          }else {
              throw new RuntimeException("Invailed passoword");
          }
      }else {
          throw new  RuntimeException("User Not found with Email"+email);
      }

    }

    @Override
    public UserDto2 validateUser2(String email, String pass) {
        Optional<User> optionalUser=userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            User user =optionalUser.get();
            if(user.getPassword().equals(pass)){
                return UserMapper2.mapToUserDto(user);
            }else {
                throw new RuntimeException("Invailed passoword");
            }
        }else {
            throw new  RuntimeException("User Not found with Email"+email);
        }

    }

    @Override
    public UserDto getUser(String email) {
        return null;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user=UserMapper.mapToUserEntity(userDto);
        User registerUser=userRepository.save(user);
        UserDto registerUserDto=UserMapper.mapToUserDto(registerUser);
        return registerUserDto;
    }

    @Override
    public User updatePassword(String email, String newPass) {
//    Optional<User> optionalUser=userRepository.findByEmail(email);
//    if(optionalUser.isEmpty()){
//        // User found, now we can update the password
//        User user=optionalUser.get();
//        // Encode the new password
////        String encodedPassword=passwordEncoder.encode(newPass);
//        // Set the new encoded password to the user
////        user.setPassword(encodedPassword);
//        // Save the updated user back to the database
//        userRepository.save(user);
//    }else {
//        throw new UserNotFoundException(" User not found with email "+email);
//    }


        Optional<User> optionalUser=userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found with this email "+email);



        }else {
            User user=optionalUser.get();

            //set the new plain text password to the user
            user.setPassword(newPass);
            // Save the updated user back to the database
          return   userRepository.save(user);

        }

    }

    @Override
    public void updateUser(UserDto userDto) {
       Optional<User> optionalUser=userRepository.findById(userDto.getId());

       if (optionalUser.isPresent()){
           User user=optionalUser.get();
           //update user information

           user.setName(userDto.getName());
           user.setEmail(userDto.getEmail());
           user.setPassword(userDto.getPassword());
           user.setSex(userDto.getSex());
           user.setPhone(userDto.getPhone());
           user.setCity(userDto.getCity());
           user.setState(userDto.getState());
           user.setPin(userDto.getPin());
           // Save the updated user back to the database
           userRepository.save(user);
       }else {
           throw new RuntimeException("User not found with this id "+userDto.getId());
       }

    }

    @Override
    public void updatePhone(String email, Long newPhone) {
        Optional<User> optionalUser=userRepository.findByEmail(email);

        if (optionalUser.isPresent()){
            User user=optionalUser.get();
            //update the phone number
            user.setPhone(newPhone);
            // Save the updated user back to the database
            userRepository.save(user);
        }else {
            throw new RuntimeException("User not found with email "+email);
        }

    }
}
