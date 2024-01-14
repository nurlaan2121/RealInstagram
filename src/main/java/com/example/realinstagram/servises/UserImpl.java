package com.example.realinstagram.servises;

import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.interfaces.UserInterface;
import com.example.realinstagram.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserImpl implements UserInterface {

    private final UserDao userDao;

    public UserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String saveUser(User user) {
        try {
            userDao.addUser(user);
            return "Successfully";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteUser(User user) {
        try {
            userDao.deleteUser(user);
            return "Successfully deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User logIn(String login, String password) {
        try {
           return userDao.getOneUser(login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserById(Long id) {
        try {
           return userDao.getUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
