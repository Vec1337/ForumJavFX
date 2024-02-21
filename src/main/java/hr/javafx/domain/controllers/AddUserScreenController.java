package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.User;
import hr.javafx.domain.enums.UserRole;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddUserScreenController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ComboBox<String> userRoleComboBox;

    public void initialize() {
        userRoleComboBox.getItems().add("ADMIN");
        userRoleComboBox.getItems().add("GUEST");
    }

    public void addUser() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        String roleString = userRoleComboBox.getValue();

        //GUEST DEFAULT
        UserRole role = UserRole.GUEST;

        if(roleString == "ADMIN") {
            role = UserRole.ADMIN;
        }

        User newUser = new User(25, username, password, role);


    }

}
