package com.project.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.journalApp.entity.JournalEntry;

import java.util.List;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    List<JournalEntry> Id(ObjectId id);
}
