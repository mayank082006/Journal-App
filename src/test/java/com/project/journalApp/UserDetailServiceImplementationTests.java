package com.project.journalApp;

import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.UserRepository;
import com.project.journalApp.service.UserDetailsServiceImplementation;
import com.project.journalApp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@Disabled
public class UserDetailServiceImplementationTests {


    // Using Mockito
    @InjectMocks
    private UserDetailsServiceImplementation userDetailsServiceImplementation;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

 // This work like this
   // @Test
//    void loadUserByUsernameTest(){
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((UserEntity) User.builder().username("rema").password("rema").roles(String.valueOf(new ArrayList<>())).build());
//        UserDetails user=userDetailsServiceImplementation.loadUserByUsername("rema");
//        Assertions.assertNotNull(user);
//
//    }

}
