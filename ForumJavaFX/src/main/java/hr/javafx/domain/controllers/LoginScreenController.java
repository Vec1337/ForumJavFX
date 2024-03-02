package hr.javafx.domain.controllers;

import hr.javafx.domain.ForumApplication;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.enums.UserRole;
import hr.javafx.domain.hashing.Encryptor;
import hr.javafx.domain.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;



public class LoginScreenController {

    public static UserRole loggedUserRole;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label errorLabel;

    public void userLogin(ActionEvent event){
        try {
            checkLogin();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error checking login info");
            alert.setHeaderText("Not logged in!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }
    }

    private void checkLogin() throws IOException {

        List<User> userList = FileUtils.readUsersFromFile();

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();


        String passwordHash = null;
        try {
            passwordHash = Encryptor.encryptString(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        for(User user : userList) {
            if(username.equals(user.getUsername())) {
                if(passwordHash.equals(user.getPasswordHash())) {
                    if(user.getRole().toString().equalsIgnoreCase("ADMIN")) {
                        showAdminScreen();
                        loggedUserRole = UserRole.ADMIN;
                    } else {
                        showForumScreen();
                        loggedUserRole = UserRole.GUEST;
                    }
                }
            }
        }

        //showForumScreen();
        errorLabel.setText("Invalid login!");
    }

    public void showForumScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("forumScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("Forum Screen");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAdminScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("adminScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("Admin Screen");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
