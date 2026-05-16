package com.project.journalApp.controller;

import com.project.journalApp.cache.AppCache;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private AppCache appCache;
    @Autowired
   private UserService userService;
    @GetMapping("/all-user")
     public ResponseEntity<?> getALlUser(){
         List<UserEntity> all = userService.findALl();
         if (all!=null && !all.isEmpty()){
             return new ResponseEntity<>(all, HttpStatus.OK);
         }
         else
             return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
     @PostMapping("/add-admin")
        public  ResponseEntity<?> addAdmin(@RequestBody UserEntity user){
        userService.saveNewAdmin(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
     }

     @GetMapping("clear-app-cache")
     public void appCache(){
        appCache.init();
     }



}
