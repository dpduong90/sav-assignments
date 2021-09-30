package com.sav.assignments.dto;

import com.sav.assignments.entity.Feed;

import java.io.Serializable;

public class FeedDTO implements Serializable {
    private Long id;
    private String postBy;
    private String message;

    public FeedDTO() {

    }

    public FeedDTO(Feed feed) {
        this.id = feed.getId();
        this.postBy = feed.getUser().getFirstName() + feed.getUser().getLastName();
        this.message = feed.getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostBy() {
        return postBy;
    }

    public void setPostBy(String postBy) {
        this.postBy = postBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FeedDTO{" +
            "id=" + id +
            ", postBy='" + postBy + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
