package com.project.journalApp.entity;

import java.time.LocalDateTime;

import com.project.journalApp.enums.Sentiment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter //We can use @Data it is a combination of the getter setter and the Required arg constructor and more
@Setter
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}
