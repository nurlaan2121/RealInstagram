package com.example.realinstagram.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Post {
    private Long id;
    private List<Like> likes;
    private String post;
    private User owner;
    private List<Comment> comments;
    private Date createdAd;

    public Post() {
    }

    public Post(List<Like> likes, User owner, List<Comment> comments, Date createdAd) {
        this.likes = likes;
        this.owner = owner;
        this.comments = comments;
        this.createdAd = createdAd;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
        return "Post{" +
                "id=" + id +
                ", likes=" + likes +
                ", post='" + post + '\'' +
                ", owner=" + owner +
                ", comments=" + comments +
                ", createdAd=" + createdAd +
                '}';
    }
}
