module com.example.realinstagram {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.zaxxer.hikari;
    requires java.sql;


    opens com.example.realinstagram to javafx.fxml;
    exports com.example.realinstagram;
    exports com.example.realinstagram.controllers;
    opens com.example.realinstagram.controllers to javafx.fxml;
}