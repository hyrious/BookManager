package bkmgr.controller;

import bkmgr.DataManager;
import bkmgr.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerPersonalInfo extends ControllerBase {
    private Alert changePasswordFailed  = new Alert(AlertType.ERROR);
    private Alert changePasswordSuccess = new Alert(AlertType.INFORMATION);
    private Alert changeUserNameSuccess = new Alert(AlertType.INFORMATION);

    private void set(Alert x, String title, String content) {
        x.setTitle(title);
        x.setHeaderText(null);
        x.setContentText(content);
    }
    @FXML void initialize() {
        set(changePasswordFailed, "修改密码失败", "原密码不正确。");
        set(changePasswordSuccess, "修改密码成功", "修改密码成功。");
        set(changeUserNameSuccess, "修改用户名成功", "修改用户名成功。");
    }

    @FXML private TextField     textFieldNewName;
    @FXML private Label         labelOriginalName;
    @FXML private PasswordField originalPassword;
    @FXML private PasswordField newPassword;

    @Override public void init() {
        super.init();
        labelOriginalName.setText(DataManager.user.getName());
        originalPassword.clear();
        newPassword.clear();
    }
    @FXML void back() {
        SceneManager.call("navigator");
    }
    @FXML void changePassword() {
        if (DataManager.encrypt(originalPassword.getText()).equals(DataManager.user.getPassword())) {
            DataManager.changePassword(newPassword.getText());
            init();
            changePasswordSuccess.showAndWait();
        } else changePasswordFailed.showAndWait();
    }
    @FXML void changeUserName() {
        DataManager.changeUserName(textFieldNewName.getText());
        init();
        SceneManager.window.setTitle(DataManager.user.getName() + " - 图书管理系统");
        changeUserNameSuccess.showAndWait();
    }
}
