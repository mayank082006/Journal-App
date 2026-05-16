package com.project.journalApp.service;

import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
      try {
          UserEntity user= userService.findByUsername(userName);
          journalEntry.setDate(LocalDateTime.now());
          JournalEntry saved = journalEntryRepository.save(journalEntry);
          user.getJournalEntryList().add(saved);
          userService.saveUser(user);
      }
      catch (Exception e){
          throw new RuntimeException("The is something error occur",e);
      }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {

        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById( ObjectId myId ,String userName){
        boolean removed=false;
        try {
            UserEntity user = userService.findByUsername(userName);
             removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(myId));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(myId);
            }

        }
        catch ( Exception e){
            throw new RuntimeException("An erroe occured while deleting via id",e);
        }
        return removed;

    }

}
