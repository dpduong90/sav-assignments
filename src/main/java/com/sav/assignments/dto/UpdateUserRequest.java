package com.sav.assignments.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UpdateUserRequest implements Serializable {

    @NotBlank(message = "First name is not null")
    private String firstName;

    @NotBlank(message = "Last name is not null")
    private String lastName;

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
