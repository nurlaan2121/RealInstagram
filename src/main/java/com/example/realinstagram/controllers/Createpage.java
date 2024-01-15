package com.example.realinstagram.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.CommentDao;
import com.example.realinstagram.daos.LikeDao;
import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.generics.MyChecks;
import com.example.realinstagram.models.User;
import com.example.realinstagram.servises.UserImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Createpage {


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
    private Button createbtn;

    @FXML
    private ImageView frameOne;

    @FXML
    private Button loginbtn;

    @FXML
    private TextField loginfld;

    @FXML
    private TextField passwordfld;

    @FXML
    private Label textlabel;

    public Createpage() throws SQLException {
    }


    @FXML
    void initialize() throws SQLException {

        String mediaPath = "/com/example/realinstagram/mysounds/button.mp3";
        Media media = new Media(Objects.requireNonNull(getClass().getResource(mediaPath)).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        createbtn.setOnAction(actionEvent -> {
            mediaPlayer.stop();
            mediaPlayer.play();
            String login = loginfld.getText();
            String password = passwordfld.getText();
            if (MyChecks.checkForUnical(login, user.getAllUsers())) {
                if (password.length() > 4) {
                    User user1 = new User();
                    user1.setLogin(login);
                    user1.setPassword(password);
                    String s = user.saveUser(user1);
                    textlabel.setText(s);
                } else textlabel.setText("password write again");
            } else textlabel.setText("login write again");
        });


        loginbtn.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
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
