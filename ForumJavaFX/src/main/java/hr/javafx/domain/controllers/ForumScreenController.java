package hr.javafx.domain.controllers;

import hr.javafx.domain.ForumApplication;
import hr.javafx.domain.entities.Post;
import hr.javafx.domain.utils.FileUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ForumScreenController {


    @FXML
    private ListView<String> postsListView;
    @FXML
    private Text titleText;
    @FXML
    private Text topicText;
    @FXML
    private Label contentText;
    @FXML
    private ScrollPane scrollPane;

    String currentPost;

    public void initialize() {

        contentText.setWrapText(true);
        scrollPane.setFitToWidth(true);

        List<Post> postList = FileUtils.readPostFromFile();

        for(Post p : postList) {
            String str = p.getTitle();
            postsListView.getItems().add(str);
        }

        postsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentPost = postsListView.getSelectionModel().getSelectedItem();

                Post post = null;
                for(Post p : postList) {
                    if(currentPost.contains(p.getTitle())) {
                        post = p;
                    }
                }

                titleText.setText(currentPost);
                topicText.setText(post.getTopic().getName());
                contentText.setText(post.getContent());

            }
        });

    }

}
