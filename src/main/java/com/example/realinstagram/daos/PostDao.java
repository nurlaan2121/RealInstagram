package com.example.realinstagram.daos;

import com.example.realinstagram.models.Post;
import com.example.realinstagram.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    final UserDao userDao;
    final LikeDao likeDao;
    final CommentDao commentDao;
    Connection connect = DriverManager.getConnection("jdbc:postgresql://rosie.db.elephantsql.com:5432/ipslotcr", "ipslotcr", "87xlYrK7AqXosaDu--G1qAhfoImf9GRE");


    public PostDao(UserDao userDao, LikeDao likeDao, CommentDao commentDao) throws SQLException {
        this.userDao = userDao;
        this.likeDao = likeDao;
        this.commentDao = commentDao;
    }
    public void addPost(User user,String post) throws SQLException {
        String addPost = "insert into posts(data,id_owner,post) values(current_date,?,?)";
        PreparedStatement addPre  = connect.prepareStatement(addPost);
        addPre.setLong(1,user.getId());
        addPre.setString(2,post);
        int i = addPre.executeUpdate();
    }
    public void deletePost(Long id) throws SQLException {
        String delete = "delete posts where id = ?";
        PreparedStatement deletePre = connect.prepareStatement(delete);
        deletePre.setLong(1,id);
        int i = deletePre.executeUpdate();
    }
    public List<Post> getAllPostInUser(User user) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String getAllPosts = "select * from posts where id_owner = ?";
        PreparedStatement getAllPre = connect.prepareStatement(getAllPosts);
        getAllPre.setLong(1,user.getId());
        ResultSet resultSet = getAllPre.executeQuery();
        while (resultSet.next()) {
            Post post = new Post();
            post.setId((long) resultSet.getInt("id"));
            post.setLikes(likeDao.getAllLikes(post));
            post.setComments(commentDao.getAllComments(post));
            post.setPost(resultSet.getString("post"));
            post.setOwner(user);
            post.setCreatedAd(resultSet.getDate("data"));
            posts.add(post);
        }
        return posts;
    }
    public Post findById(long postId) throws SQLException {
        String findByIdQuery = "SELECT * FROM posts WHERE id = ?";
        try (PreparedStatement findByIdPre = connect.prepareStatement(findByIdQuery)) {
            findByIdPre.setLong(1, postId);
            ResultSet resultSet = findByIdPre.executeQuery();

            if (resultSet.next()) {
                Post post = new Post();
                post.setId(postId);
                post.setLikes(likeDao.getAllLikes(post));
                post.setComments(commentDao.getAllComments(post));
                post.setPost(resultSet.getString("post"));
                User owner = userDao.getUserById(resultSet.getLong("id_owner"));
                post.setOwner(owner);
                post.setCreatedAd(resultSet.getDate("data"));
                return post;
            }
        }
        return null;
    }
    public List<Post> findByName(String username) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String getUserByNameQuery = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement getUserPre = connect.prepareStatement(getUserByNameQuery)) {
            getUserPre.setString(1, username);
            ResultSet userResultSet = getUserPre.executeQuery();

            if (userResultSet.next()) {
                User user = new User();
                user.setId(userResultSet.getLong("id"));

                String getAllPostsQuery = "SELECT * FROM posts WHERE id_owner = ?";
                try (PreparedStatement getAllPre = connect.prepareStatement(getAllPostsQuery)) {
                    getAllPre.setLong(1, user.getId());
                    ResultSet resultSet = getAllPre.executeQuery();

                    while (resultSet.next()) {
                        Post post = new Post();
                        post.setId(resultSet.getLong("id"));
                        post.setLikes(likeDao.getAllLikes(post));
                        post.setComments(commentDao.getAllComments(post));
                        post.setPost(resultSet.getString("post"));
                        post.setOwner(user);
                        post.setCreatedAd(resultSet.getDate("data"));
                        posts.add(post);
                    }
                }
            }
        }

        return posts;
    }
}
