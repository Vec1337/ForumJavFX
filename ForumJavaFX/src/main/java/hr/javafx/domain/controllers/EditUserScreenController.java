package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Change;
import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.enums.UserRole;
import hr.javafx.domain.exceptions.UserNotSelectedException;
import hr.javafx.domain.hashing.Encryptor;
import hr.javafx.domain.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditUserScreenController {

    private static final Logger logger = LoggerFactory.getLogger(EditUserScreenController.class);

    public List<Change> changes = new ArrayList<>();

    @FXML
    private ComboBox<User> usersComboBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTexField;
    @FXML
    private ComboBox<UserRole> userRoleComboBox;

    public void initialize() {
        List<User> userList = FileUtils.readUsersFromFile();

        for(User user : userList) {
            usersComboBox.getItems().add(user);
        }

        userRoleComboBox.getItems().add(UserRole.ADMIN);
        userRoleComboBox.getItems().add(UserRole.GUEST);
    }

    public void changeUsername() {

        String newUsername = usernameTextField.getText();
        String oldUsername = "";

        try {
            checkIfUserIsSelected();

            if(!newUsername.isEmpty()) {

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Are you sure?");
                alert2.setContentText("Do you want to change username?");

                Optional<ButtonType> result = alert2.showAndWait();
                if (result.get() == ButtonType.OK) {

                    List<User> userList = FileUtils.readUsersFromFile();

                    for (User user : userList) {
                        if (user.getUsername().equals(usersComboBox.getValue().getUsername())) {
                            oldUsername = user.getUsername();
                            user.setUsername(newUsername);
                        }
                    }

                    for (User user : userList) {
                        System.out.println(user);
                    }

                    FileUtils.saveUsersToFile(userList);

                    Change change = new Change("Username", oldUsername, newUsername, LocalDateTime.now());
                    FileUtils.serializeChange(change);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFO!");
                    alert.setHeaderText("User changed!");
                    alert.setContentText("New username is " + newUsername);

                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while editing user!");
                alert.setHeaderText("Username is empty!");
                alert.setContentText("ERROR");

                alert.showAndWait();
            }


        } catch (UserNotSelectedException e) {
            logger.error(e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while editing user!");
            alert.setHeaderText("User not selected!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void changePassword() {

        String newPassword = passwordTexField.getText();
        String oldPasswordHash = null;

        try {
            checkIfUserIsSelected();

            if(!newPassword.isEmpty()) {

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Are you sure?");
                alert2.setContentText("Do you want to change user password?");

                Optional<ButtonType> result = alert2.showAndWait();
                if (result.get() == ButtonType.OK) {

                    String passwordHash = null;
                    try {
                        passwordHash = Encryptor.encryptString(newPassword);
                    } catch (NoSuchAlgorithmException e) {
                        logger.error(e.getMessage());
                        System.out.println(e);
                    }

                    List<User> userList = FileUtils.readUsersFromFile();

                    for (User user : userList) {
                        if (user.getUsername().equals(usersComboBox.getValue().getUsername())) {
                            oldPasswordHash = user.getPasswordHash();
                            user.setPasswordHash(passwordHash);
                        }
                    }


                    FileUtils.saveUsersToFile(userList);

                    Change change = new Change("Password", oldPasswordHash, passwordHash, LocalDateTime.now());
                    FileUtils.serializeChange(change);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFO!");
                    alert.setHeaderText("User changed!");
                    alert.setContentText("New password hash is " + passwordHash);

                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while editing user!");
                alert.setHeaderText("Password is empty!");
                alert.setContentText("ERROR");

                alert.showAndWait();
            }


        } catch (UserNotSelectedException e) {
            logger.error(e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while editing user!");
            alert.setHeaderText("User not selected!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeRole() {

        Optional<UserRole> userRoleOptional = Optional.ofNullable(userRoleComboBox.getValue());
        UserRole oldUserRole = null;

        try {
            checkIfUserIsSelected();

            if(userRoleOptional.isPresent()) {

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Are you sure?");
                alert2.setContentText("Do you want to change user role?");

                Optional<ButtonType> result = alert2.showAndWait();
                if (result.get() == ButtonType.OK) {

                    List<User> userList = FileUtils.readUsersFromFile();

                    for (User user : userList) {
                        if (user.getUsername().equals(usersComboBox.getValue().getUsername())) {
                            oldUserRole = user.getRole();
                            user.setRole(userRoleOptional.get());
                        }
                    }


                    FileUtils.saveUsersToFile(userList);

                    Change change = new Change("User Role", oldUserRole.toString(), userRoleOptional.get().toString(), LocalDateTime.now());
                    FileUtils.serializeChange(change);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFO!");
                    alert.setHeaderText("User changed!");
                    alert.setContentText("New user role is " + userRoleOptional.get().toString());

                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while editing user!");
                alert.setHeaderText("User role is not selected!");
                alert.setContentText("ERROR");

                alert.showAndWait();
            }


        } catch (UserNotSelectedException e) {
            logger.error(e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while editing user!");
            alert.setHeaderText("User not selected!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser() {
        try {
            checkIfUserIsSelected();

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Are you sure?");
            alert2.setContentText("Do you want to delete user?");

            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == ButtonType.OK) {

                List<User> userList = FileUtils.readUsersFromFile();
                List<User> newUserList = new ArrayList<>();

                for (User user : userList) {
                    if (user.getUsername().equals(usersComboBox.getValue().getUsername())) {
                        //Don't write selected user
                    } else {
                        newUserList.add(user);
                    }
                }

                FileUtils.saveUsersToFile(newUserList);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFO!");
                alert.setHeaderText("User deleted!");
                alert.setContentText("User deleted!");

                alert.showAndWait();
            }

        } catch (UserNotSelectedException e) {
            logger.error(e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while deleting user!");
            alert.setHeaderText("User not deleted!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }
    }

    private void checkIfUserIsSelected() throws UserNotSelectedException{
        if(usersComboBox.getValue() == null) {
            throw new UserNotSelectedException("User is not selected!");
        }
    }
}
