package bkmgr.controller;

import java.sql.SQLException;
import bkmgr.DataManager;
import bkmgr.SceneManager;
import bkmgr.wrapper.ManageUserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class ControllerManageUser extends ControllerBase {
    @FXML private TableView<ManageUserData>            tableUsers;
    @FXML private TableColumn<ManageUserData, String>  tableColumnUserName;
    @FXML private TableColumn<ManageUserData, Boolean> tableColumnCanBorrow;
    @FXML private TableColumn<ManageUserData, Boolean> tableColumnCanManageBook;
    @FXML private TableColumn<ManageUserData, Boolean> tableColumnCanManageUser;
    @FXML private GridPane                             gridControlBar;
    @FXML private Label                                labelUserName;
    private ObservableList<ManageUserData>             users = FXCollections.observableArrayList();

    @FXML void initialize() {
        tableColumnCanBorrow.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumnCanBorrow));
        tableColumnCanManageBook.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumnCanManageBook));
        tableColumnCanManageUser.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumnCanManageUser));
        tableColumnUserName.setCellValueFactory(new PropertyValueFactory<ManageUserData, String>("name"));
        tableColumnCanBorrow.setCellValueFactory(new PropertyValueFactory<ManageUserData, Boolean>("canBorrow"));
        tableColumnCanManageBook
                .setCellValueFactory(new PropertyValueFactory<ManageUserData, Boolean>("canManageBook"));
        tableColumnCanManageUser
                .setCellValueFactory(new PropertyValueFactory<ManageUserData, Boolean>("canManageUser"));
    }
    public void loadUsers() {
        users.clear();
        DataManager.select("select id, name, password, permission from user", r -> {
            try {
                while (r.next())
                    users.add(new ManageUserData(r.getInt("id"), r.getString("name"), r.getString("password"),
                                                 r.getInt("permission")));
            } catch (SQLException e) {}
        });
        tableUsers.setItems(users);
    }
    @Override public void init() {
        super.init();
        loadUsers();
    }
    @FXML void changePassword() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    @FXML void deleteUser() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    @FXML void modifyPermission() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    @FXML void updateControlBar() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        ManageUserData userData = tableUsers.getSelectionModel().getSelectedItem();
        if (userData != null) labelUserName.setText(userData.getName());
    }
    @FXML void back() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("navigator");
    }
}
