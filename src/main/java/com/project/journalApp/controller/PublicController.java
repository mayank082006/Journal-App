package com.project.journalApp.controller;

import com.project.journalApp.dto.UserDto;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.service.UserService;
import com.project.journalApp.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("health-check")
    public String heactlhCheck(){
        return "ok";

    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto user){
        UserEntity newUser= new UserEntity();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setSentimentAnalysis(user.isSentimentAnalysis());
        userService.saveNewUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUserName());
           String jwt= jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.CREATED);
        }
        catch ( Exception e){
          log.error("The logging making error");
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }



    }

}
