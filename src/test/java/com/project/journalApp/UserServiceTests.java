package com.project.journalApp;

import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.UserRepository;
import com.project.journalApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Disabled
//This annotation uses when we dosent use a mockito or if we use this with mockito we have to use @mockBean instead of @mock
public class UserServiceTests {

    // This are for normal testing without mockito


    @Autowired
    private UserRepository userRepository;
    @CsvSource({"ram","yash","manu"})
    @ParameterizedTest
    public  void findByUsernameTest(String name){
         assertNotNull(userRepository.findByUserName(name));

    }










}
