package hr.javafx.domain.controllers;

import hr.javafx.domain.ForumApplication;
import hr.javafx.domain.enums.UserRole;
import hr.javafx.domain.utils.FileUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TheMenuController {

    private static final Logger logger = LoggerFactory.getLogger(TheMenuController.class);

    public void showAddUserScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("addUserScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("Add User Screen");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showCreatePostScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("createPostScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("Create Post Screen");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showForumScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("forumScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("Forum Screen");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showViewUsersScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("viewUsersScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("View Users");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showViewTopicsScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("viewTopicsScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("View Topics");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showAddTopicScreen() {

        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("addTopicScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("Add Topic Screen");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }
    public void userLogOut() {
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("loginScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            ForumApplication.getMainStage().setTitle("Login Screen!");
            ForumApplication.getMainStage().setScene(scene);
            ForumApplication.getMainStage().show();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void showEditUserScreen() {

        if(LoginScreenController.loggedUserRole == UserRole.ADMIN) {

            FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("editUserScreen.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                ForumApplication.getMainStage().setTitle("Edit User Screen");
                ForumApplication.getMainStage().setScene(scene);
                ForumApplication.getMainStage().show();
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OPERATION NOT PREMITTED");
            alert.setHeaderText("GUEST CANT EDIT USERS");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }
    }

    public void showEditTopicScreen() {

        if(LoginScreenController.loggedUserRole == UserRole.ADMIN) {

            FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("editTopicScreen.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                ForumApplication.getMainStage().setTitle("Edit Topic Screen");
                ForumApplication.getMainStage().setScene(scene);
                ForumApplication.getMainStage().show();
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OPERATION NOT PREMITTED");
            alert.setHeaderText("GUEST CANT EDIT TOPICS");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }
    }

}
