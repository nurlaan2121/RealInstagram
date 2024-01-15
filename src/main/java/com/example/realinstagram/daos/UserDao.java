package com.example.realinstagram.daos;

import com.example.realinstagram.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {


    Connection connect = DriverManager.getConnection("jdbc:postgresql://rosie.db.elephantsql.com:5432/ipslotcr", "ipslotcr", "87xlYrK7AqXosaDu--G1qAhfoImf9GRE");

    public UserDao() throws SQLException {
    }


    //This for registrasia
    public void addUser(User user) throws SQLException {
        String add = "insert into users(login,password) values (?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(add);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        int i = preparedStatement.executeUpdate();
    }

    public void deleteUser(User user) throws SQLException {
        String delete = "delete users where login = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(delete);
        preparedStatement.setString(1, user.getLogin());
        int i = preparedStatement.executeUpdate();
    }


    public Map<Integer, String> onlyUsersLogin() throws SQLException {
        Map<Integer, String> list = new HashMap<>();
        String get = "select id,login from users";
        PreparedStatement preparedStatement = connect.prepareStatement(get);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.put(resultSet.getInt("id"), resultSet.getString("login"));
        }
        return list;
    }


    //This for login
    public User getOneUser(String login, String password) throws SQLException {
        String getUser = "select * from users where login = ? and password = ?";
        User user = new User();
        PreparedStatement getUserPre = connect.prepareStatement(getUser);
        getUserPre.setString(1, login);
        getUserPre.setString(2, password);
        ResultSet getUserRes = getUserPre.executeQuery();

        while (getUserRes.next()) {
            user.setId((long) getUserRes.getInt("id"));
            user.setLogin(getUserRes.getString("login"));
            user.setPassword(getUserRes.getString("password"));
        }
        return user;
    }

    //This for search
    public List<User> getAllUsers() throws SQLException {
        String getUser = "select * from users";
        List<User> users = new ArrayList<>();

        PreparedStatement getUserPre = connect.prepareStatement(getUser);
        ResultSet getUserRes = getUserPre.executeQuery();

        while (getUserRes.next()) {
            User user = new User();
            user.setId((long) getUserRes.getInt("id"));
            user.setLogin(getUserRes.getString("login"));
            user.setPassword(getUserRes.getString("password"));

            users.add(user);
        }
        return users;
    }

    public User getUserById(long userId) throws SQLException {
        String getUserById = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement getUserByIdPre = connect.prepareStatement(getUserById)) {
            getUserByIdPre.setLong(1, userId);
            ResultSet getUserByIdRes = getUserByIdPre.executeQuery();

            if (getUserByIdRes.next()) {
                User user = new User();
                user.setId(userId);
                user.setLogin(getUserByIdRes.getString("login"));
                user.setPassword(getUserByIdRes.getString("password"));

                return user;
            }
        }

        return null;  // Возвращаем null, если пользователь с указанным id не найден
    }

}
