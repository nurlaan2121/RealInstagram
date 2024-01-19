package com.example.realinstagram.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatDao {
    public String getAllChatCurrentUserAndChooseUser(Long currentUserId) throws SQLException {
        String get = "select * from chats where id_send_user = ?";
        String message = "";
        PreparedStatement preparedStatement = UserDao.connection.prepareStatement(get);
        preparedStatement.setLong(1, currentUserId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            message = resultSet.getString("send_chat_array");
            String mess = resultSet.getString("give_chat_array");
            System.out.println(mess);
            System.out.println(message);
        }
        return message;
    }

    public String getAllChatCurrentUserAndChooseUser2(Long currentUserId) throws SQLException {
        String get = "select * from chats where id_give_user = ?";
        String mess = "";
        PreparedStatement preparedStatement = UserDao.connection.prepareStatement(get);
        preparedStatement.setLong(1, currentUserId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String message = resultSet.getString("send_chat_array");
            mess = resultSet.getString("give_chat_array");

        }
        return mess;
    }

    public void addChatCurrentAndChooseUser(Long currentUserId, Long chooseUserId) throws SQLException {
        String add = "insert into chats(id_send_user,id_give_user,send_chat_array,give_chat_array) values(?,?,?,?) ";
        String send = currentUserId + "-" + chooseUserId + "/ ,";
        //1-4 sdfs,
        String give = chooseUserId + "-" + chooseUserId + "/ ,";
        PreparedStatement preparedStatement = UserDao.connection.prepareStatement(add);
        preparedStatement.setLong(1, currentUserId);
        preparedStatement.setLong(2, chooseUserId);
        preparedStatement.setString(3, send);
        preparedStatement.setString(4, give);
        int i = preparedStatement.executeUpdate();
    }

    public boolean check(Long currentUserID, Long chooseUserId) throws SQLException {
        String check = "select send_chat_array from chats where id_send_user = ? and id_give_user = ?";
        PreparedStatement preparedStatement = UserDao.connection.prepareStatement(check);
        preparedStatement.setLong(1, currentUserID);
        preparedStatement.setLong(2, chooseUserId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String ff = resultSet.getString("send_chat_array");
            if (ff!=null){
                return true;
            }
        }
        return false;
    }

    public void updateChatCurrentAndChooseUser(Long currentUserId, Long chooseUserId, String message) throws SQLException {
        String select = "select * from chats where id_send_user = ?";
        PreparedStatement currenUser = UserDao.connection.prepareStatement(select);
        currenUser.setLong(1, currentUserId);
        ResultSet resultSet = currenUser.executeQuery();
        while (resultSet.next()) {
            String mess = resultSet.getString("send_chat_array");
            String select2 = "select * from chats where id_give_user = ?";
            PreparedStatement chooseUser = UserDao.connection.prepareStatement(select2);
            chooseUser.setLong(1, chooseUserId);
            ResultSet resultSet1 = chooseUser.executeQuery();
            while (resultSet1.next()) {
                String mess2 = resultSet1.getString("give_chat_array");
                String update2 = "update  chats set give_chat_array = ? where id_give_user = ?";
                PreparedStatement preparedStatement = UserDao.connection.prepareStatement(update2);
                preparedStatement.setString(1, (mess2 + message));
                preparedStatement.setLong(2, chooseUserId);
                int i = preparedStatement.executeUpdate();
            }
            String update = "update chats set send_chat_array = ? where id_send_user = ?";
            PreparedStatement preparedStatement = UserDao.connection.prepareStatement(update);
            preparedStatement.setString(1, (mess + message));
            preparedStatement.setLong(2, currentUserId);
            int i = preparedStatement.executeUpdate();
            if (i > 0) System.out.println("Success send message");
        }


    }
}
