package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.CommentDao;
import com.example.realinstagram.daos.LikeDao;
import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.servises.PostImpl;
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

public class Addpost {

    @FXML
    private ResourceBundle resources;
    UserDao userDao = new UserDao();
    CommentDao commentDao = new CommentDao(userDao);
    LikeDao likeDao = new LikeDao(userDao);
    PostDao postDao = new PostDao(userDao, likeDao, commentDao);
    PostImpl post = new PostImpl(postDao);

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
    private Button profilebtn;

    @FXML
    private Button recomandationbtn;

    @FXML
    private Button searchbtn;

    @FXML
    private Button sendbtn;

    @FXML
    private Label textlbl;

    @FXML
    private TextField txtfld;

    public Addpost() throws SQLException {
    }

    @FXML
    void initialize() {
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

        sendbtn.setOnAction(actionEvent -> {
            String s = post.savePosts(MainController.currentUser, txtfld.getText());
            textlbl.setText(s);
    });
}

}
