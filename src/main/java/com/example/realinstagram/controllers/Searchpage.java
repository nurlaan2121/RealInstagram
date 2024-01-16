package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.CommentDao;
import com.example.realinstagram.daos.LikeDao;
import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.models.User;
import com.example.realinstagram.servises.UserImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Searchpage {

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
    private Button btn;

    @FXML
    private Button chatbtn;

    @FXML
    private Button homebtn;

    @FXML
    private TextField loginfld;

    @FXML
    private Label postlbl;

    @FXML
    private Button profilebtn;

    @FXML
    private Button recamendationbtn;

    @FXML
    private Button searchbtn;

    @FXML
    private Label usernamelbl;

    public Searchpage() throws SQLException {
    }

    @FXML
    void initialize() {
        String mediaPath = "/com/example/realinstagram/mysounds/button.mp3";
        Media media = new Media(Objects.requireNonNull(getClass().getResource(mediaPath)).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        btn.setOnAction(actionEvent -> {
            mediaPlayer.stop();
            mediaPlayer.play();

            String userLogin = loginfld.getText();
            if (userLogin.length() > 2) {
                User userById = null;
                try {
                    userById = user.findUserById(userLogin);
                    System.out.println(userById);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (userById != null) {
                    usernamelbl.setText(userById.getLogin());
                    usernamelbl.setStyle("-fx-background-color: blue;");
                } else {
                    usernamelbl.setText("Not found");
                    usernamelbl.setStyle("-fx-background-color: red;");
                }
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
        recamendationbtn.setOnAction(actionEvent -> {
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


    }

}
