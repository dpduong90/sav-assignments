package com.sav.assignments.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AddFeedRequest implements Serializable {
    @NotBlank(message = "Message can not be null")
    private String message;

    public AddFeedRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AddFeedRequest{" +
            ", message='" + message + '\'' +
            '}';
    }
}
