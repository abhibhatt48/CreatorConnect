	package com.example.creatorconnectbackend.models;
	
	import javax.validation.constraints.Pattern;
	import javax.validation.constraints.Size;
	
	
	public class User {
	
		private Long userID;	
		@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email should be valid")
	    private String email;
		@Size(min = 8, message = "Password should have at least 8 characters")
	    private String password;
		@Pattern(regexp="^(Influencer|Organization)$", message = "User type must be either Influencer or Organization")
	    private String user_type;
	    private String reset_token;
	
		public Long getUserID() {
			return userID;
		}
	
		public void setUserID(Long userID) {
			this.userID = userID;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
	
		public String getUser_type() {
			return user_type;
		}
	
		public void setUser_type(String user_type) {
			this.user_type = user_type;
		}
		
		public String getReset_token() {
	        return reset_token;
	    }
	
	    public void setReset_token(String reset_token) {
	        this.reset_token = reset_token;
	    }
	}
