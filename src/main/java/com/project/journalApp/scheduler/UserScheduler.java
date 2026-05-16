package com.project.journalApp.scheduler;

import com.project.journalApp.cache.AppCache;
import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.enums.Sentiment;
import com.project.journalApp.repository.UserRepositoryImpl;
import com.project.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private AppCache appCache;
    @Autowired
    private UserRepositoryImpl userRepository;


    //@Scheduled(cron = "0 0 5 ? * SUN *")
    public void  fetchUserAndSendMail(){
        List<UserEntity> userEntities=userRepository.getUserSA();
        for( UserEntity user: userEntities){
            List<JournalEntry>journalEntryList=user.getJournalEntryList();
             List<Sentiment> sentiments = journalEntryList.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x-> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment , Integer> sentimentCount=new HashMap<>();
            for (Sentiment sentiment: sentiments){
                if(sentiment != null)
                    sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0)+1);
            }
            Sentiment mostFrequentSentiment=null;
            int maxCount =0;
            for (Map.Entry<Sentiment , Integer>entry:sentimentCount.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount=entry.getValue();
                    mostFrequentSentiment=entry.getKey();
                }
            }
            if (mostFrequentSentiment !=null){
                emailService.sendEmail(user.getEmail(),"Sentiments of last 7 days",mostFrequentSentiment.toString());
            }

        }
    }

//    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppcache(){
        appCache.init();
    }
}
