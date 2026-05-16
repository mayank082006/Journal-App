package com.project.journalApp.Repository;


import com.project.journalApp.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class EmailServiceTest {

     @Autowired
    private EmailService emailService;

     @Test
    public void testSendMail(){
         emailService.sendEmail("mayankmishra0804@gmail.com","Testing java mail sender","This is the body of it");
     }
}
