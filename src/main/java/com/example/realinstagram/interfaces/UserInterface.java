package com.example.realinstagram.interfaces;

import com.example.realinstagram.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserInterface {

    String saveUser(User user) throws SQLException;
    String deleteUser(User user);
    User logIn(String login, String password);
    List<User> getAllUsers();
    User findUserById(Long id);
}

// home -> posts
// search -> by user login - by posts id -> like and comment
