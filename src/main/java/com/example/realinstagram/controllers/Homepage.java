package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.CommentDao;
import com.example.realinstagram.daos.LikeDao;
import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.servises.UserImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
        searchbtn.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("searchpage.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 735, 427);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Hello!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        });
        addpostbtn.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addpost.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 735, 427);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Hello!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        });
        recomendationbtn.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("recomendation.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 735, 427);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Hello!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        });
        chatbtn.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chat.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 735, 427);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Hello!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        });


    }

}
