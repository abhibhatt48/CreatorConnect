package com.creatorconnect.creatorconnect.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.creatorconnect.creatorconnect.entity.User;
import com.creatorconnect.creatorconnect.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password));
    }
}