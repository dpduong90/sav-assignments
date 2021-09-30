package com.sav.assignments.dto;

import java.io.Serializable;

public class AddFeedRequest implements Serializable {
    private String userName;
    private String message;

    public AddFeedRequest(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
            "userName=" + userName +
            ", message='" + message + '\'' +
            '}';
    }
}
