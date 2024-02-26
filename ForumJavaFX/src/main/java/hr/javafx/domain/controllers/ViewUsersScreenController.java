package hr.javafx.domain.controllers;

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

public class ViewUsersScreenController {

    @FXML
    private TableView<User> usersTableView;
    @FXML
    private TableColumn<User, String> userIdTableColumn;
    @FXML
    private TableColumn<User, String> userNameTableColumn;
    @FXML
    private TableColumn<User, String> userPassTableColumn;
    @FXML
    private TableColumn<User, String> userRoleTableColumn;


    public void initialize() {
        userIdTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getUserID().toString());
                    }
                });
        userNameTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getUsername());
                    }
                });
        userPassTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getPasswordHash());
                    }
                });
        userRoleTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getRole().toString());
                    }
                });
    }

    public void userSearch() {
        List<User> userList = FileUtils.readUsersFromFile();

        ObservableList observableUserList = FXCollections.observableList(userList);

        usersTableView.setItems(observableUserList);
    }

}
