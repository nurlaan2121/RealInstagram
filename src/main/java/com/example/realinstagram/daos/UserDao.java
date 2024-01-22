package com.example.realinstagram.daos;

import com.example.realinstagram.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://rosie.db.elephantsql.com:5432/ipslotcr", "ipslotcr", "87xlYrK7AqXosaDu--G1qAhfoImf9GRE");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //    This for registrasia
    public void addUser(User user) throws SQLException {
        String add = "insert into users(login,password) values (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(add)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            int i = preparedStatement.executeUpdate();
            if (i > 0) System.out.println("User successfully saved");
            else System.out.println("Failed to save the user");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteUser(User user) throws SQLException {
        String delete = "DELETE FROM users WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, user.getLogin());
            int i = preparedStatement.executeUpdate();
            if (i > 0) System.out.println("user successfully deleted");
            else System.out.println("Failed to delete the user");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteUserById(Long userId) throws SQLException {
        String delete = "DELETE FROM users WHERE id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, userId);
            int i = preparedStatement.executeUpdate();
            if (i > 0) System.out.println("user successfully deleted");
            else System.out.println("Failed to delete the user");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Map<Integer, String> onlyUsersLogin() throws SQLException {
        Map<Integer, String> list = new HashMap<>();
        String get = "select id,login from users";
        PreparedStatement preparedStatement = connection.prepareStatement(get);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.put(resultSet.getInt("id"), resultSet.getString("login"));
        }
        return list;
    }

    //This for login
    public User getOneUser(String login, String password) throws SQLException {
        String getUser = "SELECT * FROM users WHERE login = ? AND password = ?";

        PreparedStatement getUserPre = connection.prepareStatement(getUser);
        getUserPre.setString(1, login);
        getUserPre.setString(2, password);
        try (ResultSet getUserRes = getUserPre.executeQuery()) {
            if (getUserRes.next()) {
                User user = new User();
                user.setId((long) getUserRes.getInt("id"));
                user.setLogin(getUserRes.getString("login"));
                user.setPassword(getUserRes.getString("password"));
                return user;
            }
        }

        return null;
    }

    //This for search
    public List<User> getAllUsers() throws SQLException {
        String getUser = "select * from users";
        List<User> users = new ArrayList<>();


        PreparedStatement getUserPre = connection.prepareStatement(getUser);
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

    public User getUserById(String login) throws SQLException {
        String getUserById = "SELECT * FROM users WHERE login = ?";

        try (
                PreparedStatement getUserByIdPre = connection.prepareStatement(getUserById)) {
            getUserByIdPre.setString(1, login);
            ResultSet getUserByIdRes = getUserByIdPre.executeQuery();

            if (getUserByIdRes.next()) {
                User user = new User();
                user.setId((long) getUserByIdRes.getInt("id"));
                user.setLogin(getUserByIdRes.getString("login"));
                user.setPassword(getUserByIdRes.getString("password"));

                return user;
            }
        }
        return null;
    }

    public User getUserById(Long id) throws SQLException {
        String getUserById = "SELECT * FROM users WHERE id = ?";

        try (
                PreparedStatement getUserByIdPre = connection.prepareStatement(getUserById)) {
            getUserByIdPre.setLong(1, id);
            ResultSet getUserByIdRes = getUserByIdPre.executeQuery();

            if (getUserByIdRes.next()) {
                User user = new User();
                user.setId(id);
                user.setLogin(getUserByIdRes.getString("login"));
                user.setPassword(getUserByIdRes.getString("password"));

                return user;
            }
        }
        return null;
    }

    public void updateUser(User user,String login,String password) throws SQLException {
        String update = "UPDATE users SET login = ?, password = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3, user.getId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) System.out.println("user successfully updated");
            else System.out.println("Failed to update the user");
        }
    }

}
