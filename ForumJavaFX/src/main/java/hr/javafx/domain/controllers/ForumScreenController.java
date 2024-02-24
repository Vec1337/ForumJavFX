package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Post;
import hr.javafx.domain.utils.FileUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.List;

public class ForumScreenController {

    @FXML
    private ListView<String> postsListView;
    @FXML
    private Text titleText;

    String currentPost;

    public void initialize() {

        List<Post> postList = FileUtils.readPostFromFile();

        for(Post p : postList) {
            String str = p.getTitle() + " : " + p.getTopic().getName();
            postsListView.getItems().add(str);
        }

        postsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentPost = postsListView.getSelectionModel().getSelectedItem();

                titleText.setText(currentPost);
            }
        });

    }

}
