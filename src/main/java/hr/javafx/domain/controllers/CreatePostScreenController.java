package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Post;
import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.List;

public class CreatePostScreenController {
    @FXML
    private ComboBox<String> topicComboBox;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextArea contentTextArea;

    public void initialize() {
        List<Topic> topicList = FileUtils.readTopicsFromFile();

        for(Topic topic : topicList) {
            topicComboBox.getItems().add(topic.getName());
        }

    }

    public void createPost() {
        String topicString = topicComboBox.getValue();
        Topic topic = null;

        List<Topic> topicList = FileUtils.readTopicsFromFile();

        String title = titleTextField.getText();
        String content = contentTextArea.getText();

        try {
            if(title.isEmpty() || content.isEmpty() || topicString.isEmpty()) {
                throw new Exception();
            }


            for(Topic t : topicList) {
                if(topicString.toUpperCase().equals(t.getName().toUpperCase())) {
                    topic = t;
                }
            }

            Post post = new Post(topic, title, content);

            List<Post> postList = FileUtils.readPostFromFile();
            postList.add(post);
            FileUtils.savePostToFile(postList);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully created new post!");
            alert.setHeaderText("New post created!");
            alert.setContentText("Post: " + title + " created successfully.");

            alert.showAndWait();

        } catch (Exception e) {

            //System.out.println(topic.getName());
            System.out.println(title);
            System.out.println(content);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while creating new post!");
            alert.setHeaderText("New post not created!");
            alert.setContentText("ERROR");

            alert.showAndWait();
        }

    }
}
