package com.example.creatorconnectbackend.interfaces;

import com.example.creatorconnectbackend.models.User;

import java.util.Map;

//defining the contract for services related to user authentication and account management.
public interface UserServiceInterface {

    // Register a new user in the system and return a map containing details about the registration outcome.
    Map<String, Object> userRegister(User user);

    // Authenticate a user based on their credentials and return a map containing details about the login outcome.
    Map<String, Object> userLogin(User user);

    // Process a password recovery request based on the user's email and return a map containing details about the recovery request outcome.
    Map<String, Object> forgotPassword(String email);

    // Reset the user's password based on a token (typically sent to the user's email) and the new password.
    void resetPassword(String token, String newPassword);
}
