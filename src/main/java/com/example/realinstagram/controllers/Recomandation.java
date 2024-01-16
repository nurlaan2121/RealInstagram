package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Recomandation {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addpostbtn;

    @FXML
    private Button chatbtn;

    @FXML
    private Button homebtn;

    @FXML
    private Label onelbl;

    @FXML
    private Button profilebtn;

    @FXML
    private Button recomandationbtn;

    @FXML
    private Button restartbtn;

    @FXML
    private Button searchbtn;

    @FXML
    private Label threelbl;

    @FXML
    private Label twolbl;

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
    }

}
