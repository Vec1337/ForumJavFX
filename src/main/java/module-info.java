module com.example.onlineforumjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.javafx.domain to javafx.fxml;
    exports hr.javafx.domain;
    exports hr.javafx.domain.controllers;
    opens hr.javafx.domain.controllers to javafx.fxml;
}