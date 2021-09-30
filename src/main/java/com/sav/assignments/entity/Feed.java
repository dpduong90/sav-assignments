package com.sav.assignments.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "feed")
public class Feed implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "created_timestamp")
    @CreationTimestamp
    private Instant createdTimestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Instant createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feed book = (Feed) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Feed{" +
            "id=" + id +
            ", message='" + message + '\'' +
            ", createdTimestamp=" + createdTimestamp +
            ", user=" + user +
            '}';
    }
}
