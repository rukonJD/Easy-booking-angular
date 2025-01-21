package com.booking_system.busBooking.controller;

import com.booking_system.busBooking.dto.UserDto;
import com.booking_system.busBooking.dto.UserDto2;
import com.booking_system.busBooking.dto.UserDtoPhone;
import com.booking_system.busBooking.entity.User;
import com.booking_system.busBooking.exception.UserNotFoundException;
import com.booking_system.busBooking.service.UserReportService;
import com.booking_system.busBooking.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value="user/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
     UserServiceImpl userService;
    @Autowired
    UserReportService userReportService;

        @GetMapping("/login")
    public ResponseEntity<UserDto> validateUser(@RequestParam String email,@RequestParam String password){
           UserDto validate=userService.validateUser(email,password);
           return new ResponseEntity<UserDto>(validate,HttpStatus.OK);
        }

        @GetMapping("/loginn")
    public ResponseEntity<UserDto2> validateUser2(@RequestParam String email, @RequestParam String password){
           UserDto2 validate2=userService.validateUser2(email,password);
           return new ResponseEntity<UserDto2>(validate2,HttpStatus.OK);
        }

        @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
            System.out.println("In Register User"+userDto);

              UserDto saveUser=  userService.registerUser(userDto);
                return new ResponseEntity<>(saveUser,HttpStatus.CREATED);

        }

//      /  @PutMapping("/update-password")
//   public ResponseEntity<UserDto> updatePassword(@RequestParam String email,String newPass) {
//            try {
//                userService.updatePassword(email,newPass);
//                return ResponseEntity.ok().build();
//            } catch (UserNotFoundException ex) {
//                return ResponseEntity.status(404).body(null);
//            }
//        }

    @PutMapping("/update-password")
    public ResponseEntity<User> updatePassword(@RequestParam String email,@RequestParam String newPass){

               User user= userService.updatePassword(email, newPass);
                return ResponseEntity.ok(user);


    }

    @PutMapping("update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id,@RequestBody UserDto userDto){
        // Set the user ID in the DTO before passing it to the service
            userDto.setId(id);

            try {
                userService.updateUser(userDto);
                return ResponseEntity.ok("User update Successfully");

            }catch (RuntimeException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
            }
    }

    @PutMapping("/update-phone")
    public ResponseEntity<String> updateUserPhone(@RequestBody UserDtoPhone userDtoPhone){


            try {
                userService.updatePhone(userDtoPhone.getEmail(),userDtoPhone.getNewPhone());
                return ResponseEntity.ok("Phone number update Successfully");
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
    }

//    @GetMapping(value = "/reports/{format}",produces = {MediaType.APPLICATION_JSON_VALUE})
//    public Map<String,String> exportReport(@PathVariable String format){
//            String st= userReportService.exportReport(format);
//            Map<String,String> map=new HashMap<>();
//            map.put("key1",st);
//            return map;
//    }

}
