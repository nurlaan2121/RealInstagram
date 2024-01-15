package com.example.realinstagram.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.realinstagram.daos.CommentDao;
import com.example.realinstagram.daos.LikeDao;
import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.servises.UserImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Homepage {
    UserDao userDao = new UserDao();
    CommentDao commentDao = new CommentDao(userDao);
    LikeDao likeDao = new LikeDao(userDao);
    PostDao postDao = new PostDao(userDao, likeDao, commentDao);
    UserImpl user = new UserImpl(userDao);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addpostbtn;

    @FXML
    private Button chatbtn;

    @FXML
    private Label commentCountLbl;

    @FXML
    private Button homebtn;

    @FXML
    private Label likeCountLbl;

    @FXML
    private Button myprofilebtn;

    @FXML
    private Label postLbl;

    @FXML
    private Button recomendationbtn;

    @FXML
    private Button searchbtn;

    @FXML
    private Label userNameLbl;

    public Homepage() throws SQLException {
    }

    @FXML
    void initialize() {


    }

}
