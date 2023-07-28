package com.example.creatorconnectbackend.services;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.example.creatorconnectbackend.auth.JwtUtil;
import com.example.creatorconnectbackend.interfaces.UserServiceInterface;
import com.example.creatorconnectbackend.models.User;

@Service
public class UserService implements UserServiceInterface {
    
    private final JdbcTemplate jdbcTemplate;
    private final EmailService emailService;
    @Autowired
    private JwtUtil jwtUtil;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate, EmailService emailService) {
        this.jdbcTemplate = jdbcTemplate;
        this.emailService = emailService;
        LOGGER.info("UserService Initialized");
    }

    private RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserID(rs.getLong("userID"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUser_type(rs.getString("user_type"));
        LOGGER.info("RowMapper set for User: {}", user);
        return user;
    };

    public RowMapper<User> getUserRowMapper() {
    	LOGGER.info("Fetching User RowMapper");
        return rowMapper;
    }

    private boolean checkDuplicate(String email) {
        // If duplicate found return true, else false
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
            return count > 0;
        }
        catch(NullPointerException | EmptyResultDataAccessException e) {
            LOGGER.info("Error while checking duplicates!");
            LOGGER.error(String.valueOf(e));
            return false;
        }
    }
    
    public Map<String, Object> userRegister(User user) {
        Map<String, Object> map = new HashMap<>();
        if (checkDuplicate(user.getEmail())) {
            map.put("ok", false);
            map.put("message", "Email already exists!");
            return map;
        }
        String sql = "INSERT INTO users (email, password, user_type) VALUES (?, ?, ?)";
        LOGGER.info("Registering User: {}", user.getEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUser_type());
            return ps;
        }, keyHolder);

        Long userId = keyHolder.getKey().longValue();
        user.setUserID(userId);
        String jwt = jwtUtil.generateToken(user.getEmail());
        LOGGER.info("Registered User ID: {}", userId);
        map.put("ok", true);
        map.put("jwt", jwt);
        map.put("message", "Registered Successfully!");
        map.put("data", user);
        return map;
    }
    
    public Map<String, Object> userLogin(User user) {
        Map<String, Object> map = new HashMap<>();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        LOGGER.info("Logging in User: {}", user.getEmail());
        List<User> users = jdbcTemplate.query(sql, rowMapper, user.getEmail(), user.getPassword());
        if(users.isEmpty()){
            LOGGER.warn("Failed Login for User: {}", user.getEmail());
            map.put("ok", false);
            map.put("message", "Login error! Incorrect Email or password!");
            return map;
        }
        String jwt = jwtUtil.generateToken(user.getEmail());
        
        map.put("ok", true);
        map.put("jwt", jwt);
        map.put("message", "Login Successful!");
        map.put("data", users);

        LOGGER.info("Successful Login for User: {}", user.getEmail());
        return map;
    }

    public Map<String, Object> forgotPassword(String email) {
        Map<String, Object> map = new HashMap<>();
        if (checkDuplicate(email)) {
            map.put("ok", false);
            map.put("message", "Email does not exist! Please register or create a new account!");
            LOGGER.info("Forgot password request for Email " + email + ". It doesn't exist! ");
            return map;
        }
        String token = UUID.randomUUID().toString();
        String sql = "UPDATE users SET reset_token = ? WHERE email = ?";
        jdbcTemplate.update(sql, token, email);
        LOGGER.info("Reset token generated for User: {}", email);
        // Send email with the reset password link. Have to change this link with hosted API 
        String resetPasswordLink = "https://asdc-project-group2.onrender.com/api/users/reset-password?token=" + token;
        emailService.sendEmail(email, "Reset Password", "To reset your password, click the following link: " + resetPasswordLink);
        map.put("ok", true);
        map.put("message", "Password reset link sent to user!");
        LOGGER.info("Password reset link sent to User: {}", email);
        return map;
    }

    public void resetPassword(String token, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE reset_token = ?";
        jdbcTemplate.update(sql, newPassword, token);
        LOGGER.info("Password reset with token: {}", token);
    }
}
