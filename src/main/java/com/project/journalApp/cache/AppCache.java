package com.project.journalApp.cache;

import com.project.journalApp.entity.ConfigJournalAppEntity;
import com.project.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum  keys{
        WEATHER_API;
    }
    @Autowired
    private  ConfigJournalAppRepository configJournalAppRepository;
    public Map<String,String> app_Cache;

    @PostConstruct
    public  void  init(){
        app_Cache=new HashMap<>();
        List<ConfigJournalAppEntity>all=configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            app_Cache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }

    }
}
