/**
 * -----------------------------------------------------------------------------
 *                  User Service Interface
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'UserServiceInterface' outlines a clear contract for services linked to 
 * user authentication and account management within the 
 * 'com.example.creatorconnectbackend' application. By defining this interface, 
 * a consistent procedure for user-related operations like registration, 
 * login, and password management is established.
 *
 * Key Features:
 * 1. User Registration: Allows a new user to sign up and join the platform.
 * 2. User Login: Facilitates user authentication and access control based on 
 *    provided credentials.
 * 3. Password Assistance: Provides mechanisms for users who have forgotten 
 *    their passwords to initiate a recovery process.
 * 4. Password Reset: Enables users to securely change their passwords using 
 *    a token-based approach.
 *
 * Data Models:
 * - User: Represents the structure of a user, including attributes like 
 *   email, password, roles, and other pertinent details.
 *
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.interfaces;

import com.example.creatorconnectbackend.models.User;

import java.util.Map;

public interface UserServiceInterface {


    Map<String, Object> userRegister(User user);


    Map<String, Object> userLogin(User user);

    Map<String, Object> forgotPassword(String email);

    void resetPassword(String token, String newPassword);
}
