package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Generic2;
import hr.javafx.domain.entities.Topic;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.threads.GetTopicsThread;
import hr.javafx.domain.utils.DatabaseUtils;
import hr.javafx.domain.utils.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.w3c.dom.Text;

import java.util.*;
import java.util.stream.Collectors;

public class ViewTopicsScreenController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;

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

        Optional<String> nameOptional = Optional.ofNullable(nameTextField.getText());
        Optional<String> descriptionOptional = Optional.ofNullable(descriptionTextField.getText());

        if(nameOptional.isPresent() && descriptionOptional.isPresent()) {

            List<Topic> filteredTopicList = topicSet.stream().filter( t -> t.getName().contains(nameOptional.get()) && t.getDescription().contains(descriptionOptional.get())).collect(Collectors.toList());

            List<Topic> sortedTopicList = filteredTopicList.stream().sorted(new Comparator<Topic>() {
                @Override
                public int compare(Topic o1, Topic o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).collect(Collectors.toList());
            ObservableList observableUserList = FXCollections.observableList(sortedTopicList);
            topicTableView.setItems(observableUserList);

        } else if(nameOptional.isPresent()) {

            List<Topic> filteredTopicList = topicSet.stream().filter( t -> t.getName().contains(nameOptional.get())).collect(Collectors.toList());

            List<Topic> sortedTopicList = filteredTopicList.stream().sorted().collect(Collectors.toList());
            ObservableList observableUserList = FXCollections.observableList(sortedTopicList);
            topicTableView.setItems(observableUserList);

        } else if(descriptionOptional.isPresent()) {

            List<Topic> filteredTopicList = topicSet.stream().filter( t -> t.getDescription().contains(descriptionOptional.get())).collect(Collectors.toList());

            List<Topic> sortedTopicList = filteredTopicList.stream().sorted(new Comparator<Topic>() {
                @Override
                public int compare(Topic o1, Topic o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).collect(Collectors.toList());
            ObservableList observableUserList = FXCollections.observableList(sortedTopicList);
            topicTableView.setItems(observableUserList);

        }
        else {
            List<Topic> topicList = topicSet.stream().collect(Collectors.toList());

            List<Topic> sortedTopicList = topicList.stream().sorted(new Comparator<Topic>() {
                @Override
                public int compare(Topic o1, Topic o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).collect(Collectors.toList());
            ObservableList observableUserList = FXCollections.observableList(sortedTopicList);
            topicTableView.setItems(observableUserList);
        }


        Map<Integer, String> topicMap = new HashMap<>();
        List<Topic> topicList = topicSet.stream().toList();

        for(int i = 0; i < topicList.size(); i++) {
            topicMap.put(i, topicList.get(i).getName());
        }

        Generic2<Integer, String> topicMapGeneric = new Generic2<>(topicMap);

        for(Map.Entry<Integer, String> entry : topicMapGeneric.getMap().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}
