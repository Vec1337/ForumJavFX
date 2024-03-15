package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Change;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.threads.ShowUserRoleThread;
import hr.javafx.domain.utils.FileUtils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ViewChangesScreenController {

    @FXML
    private TableView<Change> changesTableView;
    @FXML
    private TableColumn<Change, String> fieldTableColumn;
    @FXML
    private TableColumn<Change, String> oldValueTableColumn;
    @FXML
    private TableColumn<Change, String> newValueTableColumn;
    @FXML
    private TableColumn<Change, String> localDateTimeTableColumn;

    public void initialize() {



        fieldTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Change,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Change, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getField());
                    }
                });
        oldValueTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Change,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Change, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getOldValue());
                    }
                });
        newValueTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Change,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Change, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getNewValue());
                    }
                });
        localDateTimeTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Change,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Change, String> param) {

                        LocalDateTime dateTime = param.getValue().getTimestamp();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formattedDateTime = dateTime.format(formatter);

                        return new ReadOnlyStringWrapper(formattedDateTime);
                    }
                });

        ShowUserRoleThread thread = new ShowUserRoleThread();
        Thread starter = new Thread(thread);
        starter.start();

    }


    public void viewChanges() throws FileNotFoundException {

        List<Change> changesList = new ArrayList<>();

        changesList = FileUtils.deserializeChanges();

        ObservableList observableChangesList = FXCollections.observableList(changesList);
        changesTableView.setItems(observableChangesList);

    }
}
