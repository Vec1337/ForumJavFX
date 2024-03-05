package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.enums.UserRole;
import hr.javafx.domain.exceptions.RequiredFieldsNotEnteredException;
import hr.javafx.domain.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AddTopicScreenController {

    private static final Logger logger = LoggerFactory.getLogger(AddTopicScreenController.class);

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;


    public void initialize() {

    }

    public void addTopic() {

        String name = nameTextField.getText();
        String description = descriptionTextField.getText();


        //Topic newTopic = new Topic(name, description);
        Topic newTopic = new Topic.Builder(name).withDescription(description).build();

        try {

            if(name.isEmpty() || description.isEmpty()) {
                throw new RequiredFieldsNotEnteredException("Required fields are not entered!");
            }

            List<Topic> topicList = FileUtils.readTopicsFromFile();
            topicList.add(newTopic);
            FileUtils.saveTopicsToFile(topicList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully added new topic!");
            alert.setHeaderText("New topic added!");
            alert.setContentText("Topic: " + name + " saved successfully.");

            alert.showAndWait();

        } catch (RequiredFieldsNotEnteredException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while adding new topic!");
            alert.setHeaderText("New topic not added!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }




    }

}
