package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Post;
import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.exceptions.TopicIsNotSelectedException;
import hr.javafx.domain.exceptions.UserNotSelectedException;
import hr.javafx.domain.hashing.Encryptor;
import hr.javafx.domain.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditTopicScreenController {

    private static final Logger logger = LoggerFactory.getLogger(EditTopicScreenController.class);

    @FXML
    private ComboBox<Topic> topicsComboBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;


    public void initialize() {
        List<Topic> topicList = FileUtils.readTopicsFromFile();

        for(Topic topic : topicList) {
            topicsComboBox.getItems().add(topic);
        }

    }

    public void changeName() {

        String name = nameTextField.getText();

        try {
            checkIfTopicIsSelected();

            if(!name.isEmpty()) {

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Are you sure?");
                alert2.setContentText("Do you want to delete topic?");

                Optional<ButtonType> result = alert2.showAndWait();
                if(result.get() == ButtonType.OK) {

                    List<Topic> topicList = FileUtils.readTopicsFromFile();
                    List<Post> postList = FileUtils.readPostFromFile();

                    for (Topic topic : topicList) {
                        if (topic.getName().equals(topicsComboBox.getValue().getName())) {
                            topic.setName(name);
                        }
                    }

                    for (Post post : postList) {
                        if (post.getTopic().getName().equals(topicsComboBox.getValue().getName())) {
                            post.getTopic().setName(name);
                        }
                    }

                    FileUtils.savePostToFile(postList);
                    FileUtils.saveTopicsToFile(topicList);


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFO!");
                    alert.setHeaderText("Topic changed!");
                    alert.setContentText("New topic name is " + name);

                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while editing topic!");
                alert.setHeaderText("Name is empty!");
                alert.setContentText("ERROR");

                alert.showAndWait();
            }


        } catch (TopicIsNotSelectedException e) {
            logger.error(e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while editing topic!");
            alert.setHeaderText("Topic not selected!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }

    }

    public void changeDescription() {
        String description = descriptionTextField.getText();

        try {
            checkIfTopicIsSelected();



            if(!description.isEmpty()) {

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Are you sure?");
                alert2.setContentText("Do you want to change topic description?");

                Optional<ButtonType> result = alert2.showAndWait();
                if(result.get() == ButtonType.OK) {

                    List<Topic> topicList = FileUtils.readTopicsFromFile();


                    for (Topic topic : topicList) {
                        if (topic.getName().equals(topicsComboBox.getValue().getName())) {
                            topic.setDescription(description);
                        }
                    }

                    FileUtils.saveTopicsToFile(topicList);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFO!");
                    alert.setHeaderText("Topic changed!");
                    alert.setContentText("New topic description is " + description);

                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while editing topic!");
                alert.setHeaderText("Description is empty!");
                alert.setContentText("ERROR");

                alert.showAndWait();
            }


        } catch (TopicIsNotSelectedException e) {
            logger.error(e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while editing topic!");
            alert.setHeaderText("Topic not selected!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }
    }

    public void deleteTopic() {

        try {
            checkIfTopicIsSelected();

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Are you sure?");
            alert2.setContentText("Do you want to delete topic?");

            Optional<ButtonType> result = alert2.showAndWait();
            if(result.get() == ButtonType.OK) {


                List<Topic> topicList = FileUtils.readTopicsFromFile();
                List<Topic> newtopicList = new ArrayList<>();

                for (Topic topic : topicList) {
                    if (topic.getName().equals(topicsComboBox.getValue().getName())) {
                        //Don't write selected topic
                    } else {
                        newtopicList.add(topic);
                    }
                }

                FileUtils.saveTopicsToFile(newtopicList);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFO!");
                alert.setHeaderText("Topic deleted!");
                alert.setContentText("Topic deleted!");

                alert.showAndWait();
            }

        } catch (TopicIsNotSelectedException e) {
            logger.error(e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while deleting topic!");
            alert.setHeaderText("Topic not selected!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }
    }

    private void checkIfTopicIsSelected() throws TopicIsNotSelectedException {
        if(topicsComboBox.getValue() == null) {
            throw new TopicIsNotSelectedException("Topic is not selected!");
        }
    }

}
