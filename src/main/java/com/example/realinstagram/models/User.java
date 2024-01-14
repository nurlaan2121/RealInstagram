package com.example.realinstagram.models;

import java.util.*;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Post> posts;
    private Map<Date,Chat> chatMap;

    public User() {
    }

    public User(String login, String password, List<Post> posts, Map<Date, Chat> chatMap) {
        this.login = login;
        this.password = password;
        this.posts = posts;
        this.chatMap = chatMap;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Map<Date, Chat> getChatMap() {
        return chatMap;
    }

    public void setChatMap(Map<Date, Chat> chatMap) {
        this.chatMap = chatMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", posts=" + posts +
                ", chatMap=" + chatMap +
                '}';
    }
}
