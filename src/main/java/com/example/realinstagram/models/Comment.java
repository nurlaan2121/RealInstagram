package com.example.realinstagram.models;

import java.util.Date;

public class Comment {
private Long id;
    private String comment;
    private String userLogin;
    private Date createdAt;

    public Comment() {
    }

    public Comment(String comment, String user, Date createdAt) {
        this.comment = comment;
        this.userLogin = user;
        this.createdAt = createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", user=" + userLogin +
                ", created_at=" + createdAt +
                '}';
    }
}
