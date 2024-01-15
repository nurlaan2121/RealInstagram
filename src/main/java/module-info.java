module com.example.realinstagram {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires com.zaxxer.hikari;


    opens com.example.realinstagram to javafx.fxml;
    exports com.example.realinstagram;
    exports com.example.realinstagram.controllers;
    opens com.example.realinstagram.controllers to javafx.fxml;
}