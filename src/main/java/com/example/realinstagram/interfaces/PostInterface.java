package com.example.realinstagram.interfaces;

import com.example.realinstagram.models.Post;
import com.example.realinstagram.models.User;

import java.util.List;

public interface PostInterface {
    String savePosts(User currentUser, String post);
    String deletePost(Long id);
    List<Post> getAllPostInUser(User user);
    List<Post> findPostsByName(String title);
    Post findPostById(Long id);
    List<Post> findPostByUser(User user);
}
