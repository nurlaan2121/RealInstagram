package com.example.realinstagram.servises;

import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.exceptions.NotFound;
import com.example.realinstagram.interfaces.UserInterface;
import com.example.realinstagram.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserImpl implements UserInterface {

    private final UserDao userDao;

    public UserImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public String saveUser(User user) {
        try {
            if (checkUniq(user.getLogin()) && checkUniqLength(user.getLogin())
                    && checkUniqLength(user.getPassword())) {
                userDao.addUser(user);
                return "";
            } else throw new NotFound("Incorrect , please write more 4 symbol");
        } catch (SQLException | NotFound e) {
            return e.getMessage();
        }
    }
    @Override
    public String deleteUser(User user) {
        try {
            if (checkUniq(user.getLogin()) && checkUniqLength(user.getLogin())
                    && checkUniqLength(user.getPassword())) {
                userDao.deleteUser(user);
                return "";
            } else throw new NotFound("Incorrect");
        } catch (SQLException | NotFound e) {
            return e.getMessage();
        }
    }
    public String deleteUserById(Long userId){
        try {
            if (checkUserId(userId)){
                userDao.deleteUserById(userId);
                return "";
            } else throw new NotFound("User with id: '"+userId+"' not found");
        } catch (SQLException | NotFound e) {
            return e.getMessage();
        }
    }
    @Override
    public User logIn(String login, String password) {
        try {
            if (checkUniq(login) && checkUniqLength(login) && checkUniqLength(password)) {
                return userDao.getOneUser(login, password);
            } else throw new NotFound("Incorrect user name or password");
        } catch (SQLException | NotFound e) {
            System.out.println(e.getMessage());
        }
        return null;
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
            if (checkUserId(id)) {
                return userDao.getUserById(id);
            } else throw new NotFound("user with id: '"+id+"' not found");
        } catch (SQLException | NotFound e) {
            throw new RuntimeException(e);
        }
    }
    private boolean checkUniq(String str) throws SQLException {
//        return userDao.getAllUsers().stream()
//                .anyMatch(user -> !user.getLogin().equalsIgnoreCase(str));
        return true;
    }
    private boolean checkUniqLength(String str){
        return str.length() > 3;
    }
    private boolean checkUserId(Long userId) throws SQLException {
        return userDao.getAllUsers().stream()
                .anyMatch(user -> user.getId().equals(userId));
    }
}
