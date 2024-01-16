package com.example.realinstagram.controllers;

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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController {



    @FXML
    private ResourceBundle resources;

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

    public MainController() throws SQLException {
    }
    UserDao userDao = new UserDao();
    CommentDao commentDao = new CommentDao(userDao);
    LikeDao likeDao = new LikeDao(userDao);
    PostDao postDao = new PostDao(userDao, likeDao, commentDao);
    UserImpl user = new UserImpl(userDao);
    public  static User currentUser = null;

    @FXML
    void initialize() {
        String mediaPath = "/com/example/realinstagram/mysounds/button.mp3";
        Media media = new Media(Objects.requireNonNull(getClass().getResource(mediaPath)).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        loginbtn.setOnAction(actionEvent -> {
            mediaPlayer.stop();
            mediaPlayer.play();
            if (MyChecks.allcheck(loginfld.getText())) {
                if (MyChecks.allcheck(passwordfld.getText())) {
                    currentUser = user.logIn(loginfld.getText(), passwordfld.getText());
                    System.out.println(currentUser);
                    if (currentUser != null) {
                        textlabel.setText("Success");
                        System.out.println(currentUser);

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
                    }
                    textlabel.setText("Invalid login or password");
                }
            }
        });


        createbtn.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createpage.fxml"));
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
