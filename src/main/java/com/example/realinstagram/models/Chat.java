package com.example.realinstagram.models;

import java.util.List;
import java.util.Map;

public class Chat {
    private Long id;
    private Map<User, List<String>> map;

    public Chat() {
    }

    public Chat(Map<User, List<String>> map) {
        this.map = map;
    }

    public Map<User, List<String>> getMap() {
        return map;
    }

    public void setMap(Map<User, List<String>> map) {
        this.map = map;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", map=" + map +
                '}';
    }
}
