package hr.javafx.domain;

import hr.javafx.domain.controllers.AddTopicScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ForumApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(AddTopicScreenController.class);

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {

        logger.info("App starting");

        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ForumApplication.class.getResource("loginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Login Screen!");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        launch();
    }
}