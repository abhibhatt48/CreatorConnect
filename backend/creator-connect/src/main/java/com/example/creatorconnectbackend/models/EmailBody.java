package com.example.creatorconnectbackend.models;

import org.hibernate.validator.constraints.Email;

// This class represents the body of the email
public class EmailBody {
	
    // The email field of the body. It's validated to ensure it adheres to the email format.
    @Email(message = "Email should be valid")
    private String email;

    // Getter for the email field
    public String getEmail() {
        return email;
    }

    // Setter for the email field
    public void setEmail(String email) {
        this.email = email;
    }
}
