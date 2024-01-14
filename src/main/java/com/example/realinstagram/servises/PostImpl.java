package com.example.realinstagram.servises;

import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.interfaces.PostInterface;
import com.example.realinstagram.models.Post;
import com.example.realinstagram.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostImpl implements PostInterface {
    private final PostDao postDao;

    public PostImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public String savePosts(User currentUser, String post) {
        try {
            postDao.addPost(currentUser, post);
            return "successfully added";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deletePost(Long id) {
        try {
            postDao.deletePost(id);
            return "successfully deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAllPostInUser(User user) {
        try {
            return postDao.getAllPostInUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> findPostsByName(String title) {
        try {
           return postDao.findByName(title);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post findPostById(Long id) {
        try {
            return postDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> findPostByUser(User user) {
        try {
           return postDao.getAllPostInUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
