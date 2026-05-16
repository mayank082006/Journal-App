package com.project.journalApp.service;
import com.project.journalApp.entity.UserEntity;
import com.project.journalApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    public void saveNewUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        userRepository.save(user);
    }
    public void saveNewAdmin(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User","ADMIN"));
        userRepository.save(user);
    }


    public List<UserEntity> findALl() {
        return this.userRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public UserEntity findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

}