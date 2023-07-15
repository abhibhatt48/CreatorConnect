package com.example.creatorconnectbackend.services;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.example.creatorconnectbackend.interfaces.UserServiceInterface;
import com.example.creatorconnectbackend.models.User;

@Service
public class UserService implements UserServiceInterface {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    @Lazy
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserID(rs.getLong("userID"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUser_type(rs.getString("user_type"));
        return user;
    };

    public RowMapper<User> getUserRowMapper() {
        return rowMapper;
    }
    
    public User userRegister(User user) {
        String sql = "INSERT INTO users (email, password, user_type) VALUES (?, ?, ?)";

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
        return user;
    }
    
    public boolean userLogin(User user) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        List<User> users = jdbcTemplate.query(sql, rowMapper, user.getEmail(), user.getPassword());
        return !users.isEmpty();
    }
}
