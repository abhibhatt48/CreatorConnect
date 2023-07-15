package com.example.creatorconnectbackend.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creatorconnectbackend.models.User;
import com.example.creatorconnectbackend.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
        this.userService = userService;
    }


	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
	    if(bindingResult.hasErrors()){
	        // convert the list of ObjectError objects into a single string
	        String errorMsg = bindingResult.getAllErrors().stream()
	            .map(ObjectError::getDefaultMessage)
	            .collect(Collectors.joining(", "));

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
	    }
	    user.setUser_type(user.getUser_type());
	    
	    User registeredUser = userService.userRegister(user);
	    return ResponseEntity.ok("Registered successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@Valid @RequestBody User user, BindingResult bindingResult) {
	    if(bindingResult.hasErrors()){
	        // convert the list of ObjectError objects into a single string
	        String errorMsg = bindingResult.getAllErrors().stream()
	            .map(ObjectError::getDefaultMessage)
	            .collect(Collectors.joining(", "));

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
	    }
	    boolean loggedIn = userService.userLogin(user);
	    return loggedIn ? ResponseEntity.ok("Logged in successfully") : ResponseEntity.badRequest().build();
	}
}


