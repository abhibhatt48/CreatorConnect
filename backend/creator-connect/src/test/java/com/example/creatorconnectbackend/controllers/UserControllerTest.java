package com.example.creatorconnectbackend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.creatorconnectbackend.models.User;
import com.example.creatorconnectbackend.services.UserService;

public class UserControllerTest {

	private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testRegisterUserValid() {
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.userRegister(user)).thenReturn(user);

        ResponseEntity<String> response = userController.registerUser(user, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registered successfully", response.getBody());
    }

    @Test
    public void testRegisterUserInvalid() {
        User user = new User();
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("user", "Email is required"));
        errors.add(new ObjectError("user", "Password must be at least 6 characters"));
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(errors);

        ResponseEntity<String> response = userController.registerUser(user, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is required, Password must be at least 6 characters", response.getBody());
    }
    
    @Test
    public void testLoginUserValid() {
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.userLogin(user)).thenReturn(true);

        ResponseEntity<String> response = userController.loginUser(user, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Logged in successfully", response.getBody());
    }

    @Test
    public void testLoginUserInvalid() {
        User user = new User();
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("user", "Email is required"));
        errors.add(new ObjectError("user", "Password is required"));
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(errors);

        ResponseEntity<String> response = userController.loginUser(user, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is required, Password is required", response.getBody());
    }
}