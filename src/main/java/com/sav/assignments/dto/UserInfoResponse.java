package com.sav.assignments.dto;

import com.sav.assignments.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public class UserInfoResponse implements Serializable {
    private String userName;
    private String email;
    private String firstName;
    private String lastName;


    public UserInfoResponse(AppUser userDetails) {
        this.userName = userDetails.getUsername();
        this.email = userDetails.getEmail();
        this.firstName = userDetails.getFirstName();
        this.lastName = userDetails.getLastName();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
