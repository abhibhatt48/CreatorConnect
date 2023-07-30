/**
 * -----------------------------------------------------------------------------
 *                 User Controller
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'UserController' class is an essential controller within the 
 * 'com.example.creatorconnectbackend.controllers' package. It provides endpoints 
 * to manage user-related operations in the application, such as registration, 
 * login, and password reset functionalities.
 *
 * Key Features:
 * - User Registration: Enables new users to register their details.
 * - User Login: Validates user credentials and grants access.
 * - Password Reset: Provides a mechanism for users to reset their password if forgotten.
 *
 * Annotations:
 * - @RestController: Denotes that this class offers RESTful web service endpoints.
 * - @CrossOrigin: Allows for cross-origin requests, facilitating frontend-backend communication.
 * - @RequestMapping: Determines that the endpoints in this controller will use a prefix of "/api/users".
 *
 * Dependencies:
 * - UserService: This service layer component contains logic and operations related 
 *   to user functionalities.
 *
 * Core Endpoints:
 * - /register: Accepts user details and processes their registration.
 * - /login: Validates user credentials and offers access if correct.
 * - /forgot-password: Sends a password reset link to the specified email.
 * - /reset-password: Resets the user's password using a provided token.
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.creatorconnectbackend.models.EmailBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.example.creatorconnectbackend.models.User;
import com.example.creatorconnectbackend.services.UserService;


@RestController

@CrossOrigin

@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Constructor-based dependency injection for UserService.
	 */
	public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to register a new user.
     */
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		logger.info("Attempt to register a new user.");
		if (bindingResult.hasErrors()) {
			// Convert the list of ObjectError objects into a single string and log the error messages.
			String errorMsg = bindingResult.getAllErrors().stream()
					.map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(", "));
			Map<String, Object> response = new HashMap<>();
			response.put("ok", false);
			response.put("message", "Binding error: " + errorMsg);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		// Register the user and retrieve the response.
		Map<String, Object> map = userService.userRegister(user);
		logger.info("User registered successfully with ID: {}", user.getUserID());
		return ResponseEntity.ok(map);
	}
	
	/**
	 * Endpoint for user login.
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		logger.info("Attempt to login user.");
		if (bindingResult.hasErrors()) {
			// Convert the list of ObjectError objects into a single string and log the error messages.
	        String errorMsg = bindingResult.getAllErrors().stream()
	            .map(ObjectError::getDefaultMessage)
	            .collect(Collectors.joining(", "));
			Map<String, Object> map1 = new HashMap<>();
			map1.put("ok", "false");
			map1.put("message", "Binding error!");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map1);
	    }
		Map<String, Object> map = userService.userLogin(user);
		boolean loggedIn = (boolean) map.get("ok");
	    if (!loggedIn) {
	        logger.info("User login failed");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
	    } else {
	        logger.info("User login successful");
	        return ResponseEntity.ok(map);
	    }
	}

	/**
	 * Endpoint to handle forgot password requests.
	 */
	@PostMapping("/forgot-password")
	public ResponseEntity<Map<String, Object>> forgotPassword(@RequestBody EmailBody emailBody) {
		logger.info("Processing forgot password request for email: {}", emailBody.getEmail());
		// Send reset password link to the user's email.
		Map<String, Object> map = userService.forgotPassword(emailBody.getEmail());
		return ResponseEntity.ok(map);
	}

	/**
	 * Endpoint to reset a user's password.
	 */
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
		String token = request.get("token");
		logger.info("Processing password reset request for token: {}", token);
		// Set the user's password to the new password.
		String newPassword = request.get("password");
		userService.resetPassword(token, newPassword);
		return ResponseEntity.ok("Password has been reset");
	}
}
