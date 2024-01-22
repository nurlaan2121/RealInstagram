package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.CommentDao;
import com.example.realinstagram.daos.LikeDao;
import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.generics.MyChecks;
import com.example.realinstagram.servises.UserImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Updateprofile {

    @FXML
    private ResourceBundle resources;
    UserDao userDao = new UserDao();
    CommentDao commentDao = new CommentDao(userDao);
    LikeDao likeDao = new LikeDao(userDao);
    PostDao postDao = new PostDao(userDao, likeDao, commentDao);
    UserImpl user = new UserImpl(userDao);

    @FXML
    private URL location;

    @FXML
    private Button addpostbtn;

    @FXML
    private Button chatbtn;

    @FXML
    private Button homebtn;

    @FXML
    private TextField loginfld;

    @FXML
    private PasswordField passwordfld;

    @FXML
    private Button recomandationbtn;

    @FXML
    private Button searchbtn;

    @FXML
    private Button updatebtn;

    @FXML
    private Label usernamelbl;

    public Updateprofile() throws SQLException {
    }

    @FXML
    void initialize() {
        updatebtn.setOnAction(actionEvent -> {
            String text = loginfld.getText();
            String text1 = passwordfld.getText();
            if (text.length() > 3) {
                if (MyChecks.checkForUnical(text, user.getAllUsers())) {
                    try {
                        if (text1.length()>4){
                            userDao.updateUser(MainController.currentUser,text,text1);
                        }else userDao.updateUser(MainController.currentUser,text,MainController.currentUser.getPassword());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }else System.out.println("This email is not unic");
            }
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
    }

}
