package hr.javafx.domain.controllers;

import hr.javafx.domain.entities.Generic1;
import hr.javafx.domain.entities.User;
import hr.javafx.domain.utils.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ViewUsersScreenController {


    @FXML
    private TextField usernameTextField;
    @FXML
    private ComboBox<String> userRoleComboBox;

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

        userRoleComboBox.getItems().add("");
        userRoleComboBox.getItems().add("ADMIN");
        userRoleComboBox.getItems().add("GUEST");

        userIdTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<User,String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                        return new ReadOnlyStringWrapper(param.getValue().getId().toString());
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

        Optional<String> username = Optional.ofNullable(usernameTextField.getText());

        Optional<String> userRole = Optional.ofNullable(userRoleComboBox.getValue());

        if(username.isPresent() && userRole.isPresent()) {

            List<User> filteredUserList = userList.stream().filter(u -> u.getRole().toString().contains(userRole.get()) && u.getUsername().contains(username.get())).collect(Collectors.toList());

            ObservableList observableUserList = FXCollections.observableList(filteredUserList);
            usersTableView.setItems(observableUserList);

        } else if(userRole.isPresent()) {

            List<User> filteredUserList = userList.stream().filter(u -> u.getRole().toString().contains(userRole.get())).collect(Collectors.toList());

            ObservableList observableUserList = FXCollections.observableList(filteredUserList);
            usersTableView.setItems(observableUserList);

        } else if(username.isPresent()) {

            List<User> filteredUserList = userList.stream().filter(u -> u.getUsername().contains(username.get())).collect(Collectors.toList());

            ObservableList observableUserList = FXCollections.observableList(filteredUserList);
            usersTableView.setItems(observableUserList);
        } else {

            ObservableList observableUserList = FXCollections.observableList(userList);
            usersTableView.setItems(observableUserList);
        }

        Generic1<User> userGeneric1 = new Generic1<>(userList.get(0));

        System.out.println(userGeneric1);

    }

}
