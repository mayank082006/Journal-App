package com.project.journalApp.Repository;

import com.project.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Disabled
@SpringBootTest
public class UserScheduleTest {
    @Autowired
   private UserScheduler userScheduler;

    @Test
    public void schedulerTest(){
    userScheduler.fetchUserAndSendMail();
    }
}
