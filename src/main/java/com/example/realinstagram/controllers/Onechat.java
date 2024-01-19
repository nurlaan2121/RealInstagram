package com.example.realinstagram.controllers;

import com.example.realinstagram.Main;
import com.example.realinstagram.daos.ChatDao;
import com.example.realinstagram.servises.ChatImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Onechat {
    ChatDao chatDao = new ChatDao();
    ChatImpl chat = new ChatImpl(chatDao);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addnewpostbtn;

    @FXML
    private Button chatbtn;

    @FXML
    private Button homebtn;
    @FXML
    private Text givemessagetxt;

    @FXML
    private TextField messagefld;

    @FXML
    private Button porifilbtn;

    @FXML
    private Button recomendationbtn;

    @FXML
    private Button searchbtn;
    @FXML
    private Text sendmessagetxt;

    @FXML
    private Button sendbtn;

    @FXML
    private Label userloginlbl;

    @FXML
    void initialize() {
        String sendChat = chat.getChat();
        String sentmessage = " ";
        List<String> split = List.of(sendChat.split(","));
        for (int i = 0; i < split.size(); i++) {
            List<String> split2 = List.of(split.get(i).split("/"));
            String id = String.valueOf(Chat.chooseUser.getId());
            String idCurrendUser = String.valueOf(MainController.currentUser.getId());
            System.out.println(Chat.chooseUser.getId());
            System.out.println(split2);
            if (split2.get(0).contains(id)&&split2.get(0).contains(idCurrendUser)){
                System.out.println("success");
                System.out.println(split2.get(1));
                sentmessage = sentmessage.concat(" " + split2.get(1));
            }
            System.out.println(split2.get(0));
        }

        String give = chat.getChat2();
        String givemessage = " ";
        List<String> givesplit = List.of(give.split(","));
        for (int i = 0; i < givesplit.size(); i++) {
            List<String> split3 = List.of(givesplit.get(i).split("/"));
            String id = String.valueOf(Chat.chooseUser.getId());
            String idcurrenUser = String.valueOf(MainController.currentUser.getId());
            if (split3.get(0).contains(id)&&split3.get(0).contains(idcurrenUser)){
                givemessage = givemessage.concat(" " + split3.get(1));
            }
        }

        System.out.println(split);
        sendmessagetxt.setText(sentmessage);

        System.out.println("Send :::" + sentmessage);
        givemessagetxt.setText(givemessage);
        System.out.println("give:::" + givemessage);
        userloginlbl.setText(Chat.chooseUser.getLogin());
        sendbtn.setOnAction(actionEvent -> {
            String text = messagefld.getText();
            if (text.length() > 1) {
                String sendChat2 = chat.getChat();
                String[] split2 = sendChat2.trim().split(",");
                chat.updateChat(Chat.chooseUser.getId(),("/"+ text));
                System.out.println("Success send message");
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
