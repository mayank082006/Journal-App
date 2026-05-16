package com.project.journalApp.controller;

import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.service.JournalEntryService;
import com.project.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntityControllerv1 {

    @Autowired
    private JournalEntryService journalEntryService;
   @Autowired
     private UserService userService;


    @GetMapping
    public ResponseEntity<?> getAllJournalEntryOfUser()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user  = userService.findByUsername(userName);
        List<JournalEntry> all= user.getJournalEntryList();
       if(all != null&& !all.isEmpty()){
           return new ResponseEntity<>(all,HttpStatus.OK);
       }
        return new ResponseEntity<>(    HttpStatus.NOT_FOUND);
    }
    @PostMapping()
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry  )
    {
        try{
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
          String userName=  authentication.getName();
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> journalEntryById(@PathVariable String myId){
        ObjectId objectId =new ObjectId(myId);
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user =userService.findByUsername(userName);
       List<JournalEntry> collect= user.getJournalEntryList().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=journalEntryService.findById(objectId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String myId){
        ObjectId objectId =new ObjectId(myId);
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user =userService.findByUsername(userName);
        boolean removed=this.journalEntryService.deleteById(objectId, userName);
        if(removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> PutEntryById(@PathVariable String myId,@RequestBody JournalEntry newEntry) {
        ObjectId objectId =new ObjectId(myId);
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        UserEntity user=userService.findByUsername(userName);
        List<JournalEntry>collect= user.getJournalEntryList().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry>journalEntry= journalEntryService.findById(objectId);
            if (journalEntry.isPresent()) {
                JournalEntry old = journalEntry.get();
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
       }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
