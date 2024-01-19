package com.example.realinstagram.servises;

import com.example.realinstagram.controllers.MainController;
import com.example.realinstagram.daos.ChatDao;

import java.sql.SQLException;

public class ChatImpl {
    private final ChatDao chatDao;

    public ChatImpl(ChatDao chatDao) {
        this.chatDao = chatDao;
    }
    public void addNewChat(Long chooseUserID){
        try {
            chatDao.addChatCurrentAndChooseUser(MainController.currentUser.getId(),chooseUserID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateChat(Long chooseUserID,String message){
        String mess = MainController.currentUser.getId() + "-" + chooseUserID + " " + message + " ,";
        //1-7 sjdfojfjsofsjdfsj ,
        try {
            chatDao.updateChatCurrentAndChooseUser(MainController.currentUser.getId(),chooseUserID,mess);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getChat(){
        try {
            return chatDao.getAllChatCurrentUserAndChooseUser(MainController.currentUser.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }public String getChat2(){
        try {
            return chatDao.getAllChatCurrentUserAndChooseUser2(MainController.currentUser.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean check(Long chooseUSerId){
        try {
            return chatDao.check(MainController.currentUser.getId(),chooseUSerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
