package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.ChatDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.models.User;
import com.example.realinstagram.servises.ChatImpl;
import com.example.realinstagram.servises.UserImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Chat {
    public static User chooseUser = null;
    ChatDao chatDao = new ChatDao();
    ChatImpl chat = new ChatImpl(chatDao);
    UserDao userDao = new UserDao();
    UserImpl user = new UserImpl(userDao);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addchatbtn;

    @FXML
    private Button addpostbtn;

    @FXML
    private Button chatbtn;

    @FXML
    private Button firstuserbtn;

    @FXML
    private Label fisrusercatlbl;

    @FXML
    private Button homebtn;

    @FXML
    private Button profilebtn;

    @FXML
    private Button recomendationbtn;

    @FXML
    private Label messagelbl;
    @FXML
    private Button searchbtn;

    @FXML
    private Button searchloginbtn;

    @FXML
    private Button seconduserbtn;

    @FXML
    private Label seconduserchatlbl;
    @FXML
    private TextField loginfld;
    @FXML
    private Label threeuserchatlbl;

    @FXML
    private Button treeuserbtn;

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
        homebtn.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homepage.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 735, 427);
                Stage stage = new Stage();
                stage.setTitle("Hello!");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        searchloginbtn.setOnAction(actionEvent -> {
            String text = loginfld.getText();
            try {
                User searchUser = user.findUserById(text.trim());
                if (searchUser != null) {
                    chooseUser = searchUser;
                    messagelbl.setText(searchUser.getLogin());
                    addchatbtn.setOnAction(actionEvent1 -> {
                        System.out.println(chat.check(Chat.chooseUser.getId()));
                        if (!chat.check(Chat.chooseUser.getId())) {
                            chat.addNewChat(chooseUser.getId());
                        }
                        fisrusercatlbl.setText(searchUser.getLogin());
                        firstuserbtn.setOnAction(actionEvent2 -> {
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("onechat.fxml"));
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
                    });
                } else {
                    messagelbl.setText("Not found");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

    }

}
