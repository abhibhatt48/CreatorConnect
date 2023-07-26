package com.example.creatorconnectbackend.models;

import org.hibernate.validator.constraints.Email;

public class EmailBody {
	
	@Email(message = "Email should be valid")
    private String email;

    // getter and setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
