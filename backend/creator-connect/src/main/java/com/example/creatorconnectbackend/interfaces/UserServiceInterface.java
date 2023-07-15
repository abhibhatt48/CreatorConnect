package com.example.creatorconnectbackend.interfaces;

import com.example.creatorconnectbackend.models.User;

public interface UserServiceInterface {
	User userRegister(User user);
    boolean userLogin(User user);
}
