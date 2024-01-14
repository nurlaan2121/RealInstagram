module com.example.realinstagram {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.realinstagram to javafx.fxml;
    exports com.example.realinstagram;
}