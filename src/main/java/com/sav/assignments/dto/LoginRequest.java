package com.sav.assignments.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LoginRequest implements Serializable {

    @NotBlank(message = "Username cannot be null")
    @Min(value = 4, message = "Username should not be less than 4")
    private String userName;

    @NotBlank(message = "Password cannot be null")
    @Min(value = 6, message = "Password should not be less than 6")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
