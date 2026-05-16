package com.project.journalApp.controller;

import com.project.journalApp.api.response.WeatherResponse;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.service.UserService;
import com.project.journalApp.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private WeatherService weatherService;
//    @GetMapping
//    public List<UserEntity>getAll(){
//        return userService.findALl();
//    }
    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
       WeatherResponse weatherResponse= weatherService.getWeather("Pune");
       String greeting="";
       if(weatherResponse!=null){
           greeting=",Weather feels like "+weatherResponse.getCurrent().getTemperature() + weatherResponse.getLocation().getRegion();
       }
        return new ResponseEntity<>("Hi "+authentication.getName()+greeting ,HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<?> addUserByID(@RequestBody UserEntity user){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String userName=authentication.getName();
       UserEntity oldUserInfo=userService.findByUsername(userName);
        if (oldUserInfo != null){
            oldUserInfo.setUserName(user.getUserName());
            oldUserInfo.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(oldUserInfo);
        return ResponseEntity.ok().body("Updated Successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
