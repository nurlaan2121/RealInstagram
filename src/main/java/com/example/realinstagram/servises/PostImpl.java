package com.example.realinstagram.servises;

import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.exceptions.NotFound;
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
            if (checkPosts(post)) {
                postDao.addPost(currentUser, post);
                return "successfully added";
            } else throw new NotFound("post is empty");
        } catch (SQLException | NotFound e) {
            return e.getMessage();
        }
    }
    @Override
    public String deletePost(Long id) {
        try {
            if (checkPostsById(id)) {
                postDao.deletePost(id);
                return "successfully deleted";
            } else throw new NotFound("post with id: '"+id+"' not found");
        } catch (SQLException | NotFound e) {
            return e.getMessage();
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
            if (checkPosts(title)) {
                return postDao.findByName(title);
            } else throw new NotFound("post with name: '"+title+"' not found");
        } catch (SQLException | NotFound e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Post findPostById(Long id) {
        try {
            if (checkPostsById(id)) {
                return postDao.findById(id);
            } else throw new NotFound("post with id: '"+id+"' not found");
        } catch (SQLException | NotFound e) {
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
    private boolean checkPosts(String post) {
        return !post.isEmpty();
    }
    private boolean checkPostsById(Long id) throws SQLException {
        return postDao.getAllPosts().stream()
                .anyMatch(post -> post.getId().equals(id));

    }
}
