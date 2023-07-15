package com.example.creatorconnectbackend.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.example.creatorconnectbackend.models.User;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {
	
    private UserService userService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(jdbcTemplate);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setUser_type("user");

        // Given
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            PreparedStatementCreator creator = (PreparedStatementCreator) args[0];
            KeyHolder holder = (KeyHolder) args[1];
            try (Connection connection = mock(Connection.class);
                 PreparedStatement preparedStatement = mock(PreparedStatement.class)) {
                when(connection.prepareStatement(any(), anyInt())).thenReturn(preparedStatement);
                when(preparedStatement.execute()).thenReturn(true);
                creator.createPreparedStatement(connection);
                // assuming the primary key of the user is an integer
                Field field = GeneratedKeyHolder.class.getDeclaredField("keyList");
                field.setAccessible(true);
                List<Map<String, Object>> keyList = new ArrayList<>();
                Map<String, Object> keyMap = new HashMap<>();
                keyMap.put("SCOPE_IDENTITY()", 1L); // assuming your auto-generated ID is a Long
                keyList.add(keyMap);
                field.set(holder, keyList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

        // When
        User registeredUser = userService.userRegister(user);

        // Then
        verify(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));
        assertEquals(1L, registeredUser.getUserID());
        assertEquals(user.getEmail(), registeredUser.getEmail());
        assertEquals(user.getPassword(), registeredUser.getPassword());
        assertEquals(user.getUser_type(), registeredUser.getUser_type());
    }


    @Test
    public void testLoginUserValid() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        List<User> users = new ArrayList<>();
        users.add(user);

        when(jdbcTemplate.query(any(String.class), any(RowMapper.class), any(String.class), any(String.class)))
                .thenReturn(users);

        boolean loggedIn = userService.userLogin(user);

        assertTrue(loggedIn);
    }
}
