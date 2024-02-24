package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.utils.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.List;

public class ViewTopicsScreenController {

    @FXML
    private TableView<User> topicTableView;
    @FXML
    private TableColumn<Topic, String> topicNameTableColumn;
    @FXML
    private TableColumn<Topic, String> topicDescriptionTableColumn;

    public void initialize() {
        topicNameTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Topic,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Topic, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getName());
                    }
                });

        topicDescriptionTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Topic,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Topic, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getDescription());
                    }
                });

    }

    public void topicSearch() {
        List<Topic> topicList = FileUtils.readTopicsFromFile();

        ObservableList observableUserList = FXCollections.observableList(topicList);

        topicTableView.setItems(observableUserList);
    }

}
