package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.CommentDao;
import com.example.realinstagram.daos.LikeDao;
import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.models.Post;
import com.example.realinstagram.servises.PostImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class Recomandation {

    @FXML
    private ResourceBundle resources;
    UserDao userDao = new UserDao();
    CommentDao commentDao = new CommentDao(userDao);
    LikeDao likeDao = new LikeDao(userDao);
    PostDao postDao = new PostDao(userDao, likeDao, commentDao);
    PostImpl post = new PostImpl(postDao);



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

    public Recomandation() throws SQLException {
    }

    @FXML
    void initialize() {
        restartbtn.setOnAction(actionEvent -> {
            List<Post> allPosts = post.getAllPosts();
            allPosts.sort(comparator);
            if (allPosts.size()>1){
                onelbl.setText(allPosts.get(0).getPost());
            }else if (allPosts.size()>2){
                onelbl.setText(allPosts.get(0).getPost());
                twolbl.setText(allPosts.get(1).getPost());
            }else  if (allPosts.size()>3){
                onelbl.setText(allPosts.get(0).getPost());
                twolbl.setText(allPosts.get(1).getPost());
                twolbl.setText(allPosts.get(2).getPost());
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
    Comparator<Post> comparator = new Comparator<Post>() {

        @Override
        public int compare(Post o1, Post o2) {
            return o2.getLikes().size() - o1.getLikes().size() ;
        }
    };

}
