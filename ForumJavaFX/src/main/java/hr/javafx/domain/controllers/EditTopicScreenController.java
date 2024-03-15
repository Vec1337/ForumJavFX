package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Change;
import hr.javafx.domain.entities.Post;
import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.exceptions.TopicIsNotSelectedException;
import hr.javafx.domain.exceptions.UserNotSelectedException;
import hr.javafx.domain.hashing.Encryptor;
import hr.javafx.domain.threads.GetTopicsThread;
import hr.javafx.domain.utils.DatabaseUtils;
import hr.javafx.domain.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EditTopicScreenController {

    private static final Logger logger = LoggerFactory.getLogger(EditTopicScreenController.class);

    @FXML
    private ComboBox<Topic> topicsComboBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;


    public void initialize() {
        //List<Topic> topicList = FileUtils.readTopicsFromFile();
        List<Topic> topicList = DatabaseUtils.getTopics().stream().collect(Collectors.toList());

        for(Topic topic : topicList) {
            topicsComboBox.getItems().add(topic);
        }

    }

    public void changeName() {

        String name = nameTextField.getText();
        String oldName = null;

        try {
            checkIfTopicIsSelected();

            if(!name.isEmpty()) {

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Are you sure?");
                alert2.setContentText("Do you want to change topic name?");

                Optional<ButtonType> result = alert2.showAndWait();
                if(result.get() == ButtonType.OK) {


                    //List<Topic> topicList = FileUtils.readTopicsFromFile();
                    //Set<Topic> topicSet = DatabaseUtils.getTopics();

                    Set<Topic> topicSet = new HashSet<>();

                    GetTopicsThread getTopicsThread = new GetTopicsThread();
                    Thread thread = new Thread(getTopicsThread);
                    thread.start();
                    try {
                        thread.join();

                        topicSet = getTopicsThread.getResult();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    List<Topic> topicList = topicSet.stream().toList();
                    //List<Post> postList = FileUtils.readPostFromFile();

                    for (Topic topic : topicList) {
                        if (topic.getName().equals(topicsComboBox.getValue().getName())) {
                            oldName = topic.getName();
                            topic.setName(name);
                        }
                    }

                    /*
                    for (Post post : postList) {
                        if (post.getTopic().getName().equals(topicsComboBox.getValue().getName())) {
                            oldName = post.getTopic().getName();
                            post.getTopic().setName(name);
                        }
                    }
                    */
                    //FileUtils.savePostToFile(postList);
                    //FileUtils.saveTopicsToFile(topicList);

                    oldName = topicsComboBox.getValue().getName();

                    DatabaseUtils.changeTopicName(name, oldName);

                    Change change = new Change("Topic name", oldName, name, LocalDateTime.now());
                    FileUtils.serializeChange(change);

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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void changeDescription() {
        String description = descriptionTextField.getText();
        String oldDescription = null;

        try {
            checkIfTopicIsSelected();



            if(!description.isEmpty()) {

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Are you sure?");
                alert2.setContentText("Do you want to change topic description?");

                Optional<ButtonType> result = alert2.showAndWait();
                if(result.get() == ButtonType.OK) {

                    //List<Topic> topicList = FileUtils.readTopicsFromFile();
                    //Set<Topic> topicSet = DatabaseUtils.getTopics();

                    Set<Topic> topicSet = new HashSet<>();

                    GetTopicsThread getTopicsThread = new GetTopicsThread();
                    Thread thread = new Thread(getTopicsThread);
                    thread.start();
                    try {
                        thread.join();

                        topicSet = getTopicsThread.getResult();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    List<Topic> topicList = topicSet.stream().toList();

                    oldDescription = topicsComboBox.getValue().getDescription();

                    //FileUtils.saveTopicsToFile(topicList);
                    DatabaseUtils.changeTopicDescription(description, oldDescription);


                    Change change = new Change("Topic description", oldDescription, description, LocalDateTime.now());
                    FileUtils.serializeChange(change);

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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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


                //List<Topic> topicList = FileUtils.readTopicsFromFile();
                List<Topic> topicList = DatabaseUtils.getTopics().stream().collect(Collectors.toList());
                List<Topic> newtopicList = new ArrayList<>();

                for (Topic topic : topicList) {
                    if (topic.getName().equals(topicsComboBox.getValue().getName())) {
                        //Don't write selected topic
                        DatabaseUtils.deleteTopic(topic.getName());
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
