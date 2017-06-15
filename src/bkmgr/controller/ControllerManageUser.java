package bkmgr.controller;

import java.sql.SQLException;
import bkmgr.DataManager;
import bkmgr.SceneManager;
import bkmgr.wrapper.ManageUserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class ControllerManageUser extends ControllerBase {
    private Alert deleteConfirmation = new Alert(AlertType.CONFIRMATION);
    private Alert cantModifySelf     = new Alert(AlertType.ERROR);
    private Alert cantDeleteSelf     = new Alert(AlertType.ERROR);

    private void set(Alert x, String title, String content) {
        x.setTitle(title);
        x.setHeaderText(null);
        x.setContentText(content);
    }

    @FXML private TableView<ManageUserData>            tableUsers;
    @FXML private TableColumn<ManageUserData, String>  tableColumnUserName;
    @FXML private TableColumn<ManageUserData, Boolean> tableColumnCanBorrow;
    @FXML private TableColumn<ManageUserData, Boolean> tableColumnCanManageBook;
    @FXML private TableColumn<ManageUserData, Boolean> tableColumnCanManageUser;
    @FXML private GridPane                             gridControlBar;
    @FXML private Label                                labelUserName;
    private ObservableList<ManageUserData>             users = FXCollections.observableArrayList();

    @FXML void initialize() {
        set(deleteConfirmation, "确认删除", "确定删除该用户？此操作不可恢复。");
        set(cantModifySelf, "反身禁止", "不可以修改自己的权限。");
        set(cantDeleteSelf, "反身禁止", "不可以删除自己。");
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
        updateControlBar();
    }
    @FXML void changePassword() {
        ManageUserData userData = tableUsers.getSelectionModel().getSelectedItem();
        if (userData == null) return;
        DataManager.forceChangePasswordUserID = userData.getId();
        DataManager.forceChangePasswordUserName = userData.getName();
        SceneManager.call("changePassword");
    }
    @FXML void deleteUser() {
        ManageUserData userData = tableUsers.getSelectionModel().getSelectedItem();
        if (userData == null) return;
        if (userData.getId() == DataManager.user.getId()) {
            cantDeleteSelf.showAndWait();
            return;
        }
        if (deleteConfirmation.showAndWait().get().getButtonData() == ButtonData.OK_DONE) {
            DataManager.deleteUser(userData.getId());
            users.remove(userData);
        }
    }
    @FXML void modifyPermission() {
        ManageUserData userData = tableUsers.getSelectionModel().getSelectedItem();
        if (userData == null) return;
        if (userData.getId() == DataManager.user.getId()) {
            cantModifySelf.showAndWait();
            return;
        }
        DataManager.modifyPermissionUserID = userData.getId();
        DataManager.modifyPermissionUserName = userData.getName();
        DataManager.modifyPermissionUserCanBorrow = userData.isCanBorrow();
        DataManager.modifyPermissionUserCanManageBook = userData.isCanManageBook();
        DataManager.modifyPermissionUserCanManageUser = userData.isCanManageUser();
        SceneManager.call("modifyPermission");
    }
    @FXML void updateControlBar() {
        ManageUserData userData = tableUsers.getSelectionModel().getSelectedItem();
        if (userData != null) {
            gridControlBar.setDisable(false);
            labelUserName.setText(userData.getName());
        } else {
            gridControlBar.setDisable(true);
            labelUserName.setText("<未选中>");
        }
    }
    @FXML void back() {
        SceneManager.call("navigator");
    }
}
