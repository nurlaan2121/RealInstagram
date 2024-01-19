package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.CommentDao;
import com.example.realinstagram.daos.LikeDao;
import com.example.realinstagram.daos.PostDao;
import com.example.realinstagram.daos.UserDao;
import com.example.realinstagram.models.Comment;
import com.example.realinstagram.models.Like;
import com.example.realinstagram.models.Post;
import com.example.realinstagram.models.User;
import com.example.realinstagram.servises.UserImpl;
import javafx.event.ActionEvent;
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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Searchpage {
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
    private Button btn;

    @FXML
    private Button chatbtn;

    @FXML
    private Button commentbtn;

    @FXML
    private Label countlikecomment;

    @FXML
    private Label countlikelbl;

    @FXML
    private Button homebtn;

    @FXML
    private Button likebtn;

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

    @FXML
    void opencomment(ActionEvent event) {

    }

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
                List<Post> allPostInUser;
                if (userById != null) {
                    try {
                        allPostInUser = postDao.getAllPostInUser(userById);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    if (!allPostInUser.isEmpty()) {
                        allPostInUser.sort(comparator);
                        Post post = allPostInUser.get(0);
                        postlbl.setText(post.getPost());
                        try {
                            List<Like> allLikes = likeDao.getAllLikes(post);
                            List<Comment> allComments = commentDao.getAllComments(post);
                            countlikelbl.setText(String.valueOf(allLikes.size()));
                            countlikecomment.setText(String.valueOf(allComments.size()));
                            likebtn.setOnAction(actionEvent1 -> {
                                try {
                                    likeDao.addLikeToPost(MainController.currentUser, post.getId());
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }

//                                for (int i = 0; i < post.getLikes().size(); i++) {
//                                    if (post.getLikes().get(i).getUser().equalsIgnoreCase(MainController.currentUser.getLogin())) {
//                                        try {
//                                        } catch (SQLException e) {
//                                            throw new RuntimeException(e);
//                                        }
//                                    }
//                                }
                            });
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }


                    }
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

    Comparator<Post> comparator = new Comparator<Post>() {

        @Override
        public int compare(Post o1, Post o2) {
            return o2.getLikes().size() - o1.getLikes().size();
        }
    };

}
