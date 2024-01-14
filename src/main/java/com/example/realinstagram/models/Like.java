package com.example.realinstagram.models;

import java.util.Date;

public class Like {
    private Long id;
    private String userLogin;
    private Date createdAd;

    public Like() {
    }

    public Like(String user, Date createdAd) {
        this.userLogin = user;
        this.createdAd = createdAd;
    }

    public String getUser() {
        return userLogin;
    }

    public void setUser(String user) {
        this.userLogin = user;
    }

    public Date getCreatedAd() {
        return createdAd;
    }

    public void setCreatedAd(Date createdAd) {
        this.createdAd = createdAd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", user=" + userLogin +
                ", created_ad=" + createdAd +
                '}';
    }
}
