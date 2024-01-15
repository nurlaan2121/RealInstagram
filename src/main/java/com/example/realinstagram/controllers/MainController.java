package com.example.realinstagram.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import com.example.realinstagram.Main;
import com.example.realinstagram.generics.MyChecks;
import com.example.realinstagram.models.User;
import com.example.realinstagram.servises.UserImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController {
    UserImpl user;


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
                    User deleteUSer = null;
                    try {
                        deleteUSer = user.logIn(loginfld.getText(), passwordfld.getText());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (deleteUSer != null) {
                        textlabel.setText("Success");
                        User currentUser = deleteUSer;


                    }
                    textlabel.setText("Invalid login or password");
                }
            }
        });


        createbtn.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createpage.fxml"));
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
