package hr.javafx.domain.controllers;

import hr.javafx.domain.ForumApplication;
import hr.javafx.domain.enums.UserRole;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AdminScreenController {

    private static final Logger logger = LoggerFactory.getLogger(AdminScreenController.class);

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

}
