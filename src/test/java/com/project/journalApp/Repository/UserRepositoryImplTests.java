package com.project.journalApp.Repository;

import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@Disabled
@SpringBootTest
public class UserRepositoryImplTests {
    @Autowired
    private UserRepositoryImpl userRepository;
    @Test
    public void testUserCriteria(){
        List<UserEntity> users= userRepository.getUserSA();
        System.out.println(users);
    }
}
