package com.cforum.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class LoginForm {
	
    @NotBlank(message = "username must not be empty")
    @Size(min=3, max = 60, message="length must be atleast of 3 character and max 60")
    private String username;
 
    @NotBlank(message = "password must not be empty")
    @Size(min = 6, max = 100, message="length must be atleast of 6 character and max 100")
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
