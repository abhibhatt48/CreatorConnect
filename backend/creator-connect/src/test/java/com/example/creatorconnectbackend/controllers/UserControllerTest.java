package com.example.creatorconnectbackend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.example.creatorconnectbackend.models.EmailBody;
import com.example.creatorconnectbackend.models.User;
import com.example.creatorconnectbackend.services.UserService;

public class UserControllerTest {
    private UserService userService;
    private UserController userController;

    @BeforeEach
    public void setup() {
        // Initialize a mock UserService and UserController
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void testRegisterUser() {
        // Prepare test data
        User user = new User();
        user.setUserID(1L);
        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");

        // Mock the behavior of the UserService
        when(userService.userRegister(user)).thenReturn(Collections.singletonMap("ok", true));

        // Execute the controller method
        ResponseEntity<Map<String, Object>> response = userController.registerUser(user, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().get("ok"));
    }

    @Test
    public void testLoginUserFail() {
        // Prepare test data
        User testUser = new User();
        testUser.setUserID(123L);
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");

        BindingResult bindingResult = new BeanPropertyBindingResult(testUser, "testUser");

        // Mock the behavior of the UserService with login failure
        Map<String, Object> map = new HashMap<>();
        map.put("ok", false);
        map.put("message", "Invalid credentials");
        when(userService.userLogin(testUser)).thenReturn(map);

        // Execute the controller method
        ResponseEntity<Map<String, Object>> response = userController.loginUser(testUser, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(false, response.getBody().get("ok"));
        assertEquals("Invalid credentials", response.getBody().get("message"));
    }

    @Test
    public void testRegisterUserWithBindingErrors() {
        // Prepare test data
        User user = new User();
        user.setUserID(1L);
        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
        bindingResult.rejectValue("userID", "error.userID", "User ID is not valid");

        // Execute the controller method
        ResponseEntity<Map<String, Object>> response = userController.registerUser(user, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody().get("ok"));
    }

    @Test
    public void testLoginUser() {
        // Prepare test data
        User user = new User();
        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");

        // Mock the behavior of the UserService with successful login
        when(userService.userLogin(user)).thenReturn(Collections.singletonMap("ok", true));

        // Execute the controller method
        ResponseEntity<Map<String, Object>> response = userController.loginUser(user, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().get("ok"));
    }

    @Test
    public void testLoginUserWithBindingErrors() {
        // Prepare test data
        User user = new User();
        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
        bindingResult.rejectValue("userID", "error.userID", "User ID is not valid");

        // Execute the controller method
        ResponseEntity<Map<String, Object>> response = userController.loginUser(user, bindingResult);

        // Verify the result
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("false", response.getBody().get("ok"));
    }

    @Test
    public void testForgotPassword() {
        // Prepare test data
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail("test@example.com");

        // Mock the behavior of the UserService for forgot password
        when(userService.forgotPassword("test@example.com")).thenReturn(Collections.singletonMap("ok", true));

        // Execute the controller method
        ResponseEntity<Map<String, Object>> response = userController.forgotPassword(emailBody);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().get("ok"));
    }

    @Test
    public void testResetPassword() {
        // Execute the controller method
        ResponseEntity<String> response = userController.resetPassword(Collections.singletonMap("token", "test-token"));

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password has been reset", response.getBody());
    }
}
