package bkmgr.controller;

import bkmgr.DataManager;
import bkmgr.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;

public class ControllerChangePassword extends ControllerBase {
    private Alert changeSuccess = new Alert(AlertType.INFORMATION);

    private void set(Alert x, String title, String content) {
        x.setTitle(title);
        x.setHeaderText(null);
        x.setContentText(content);
    }

    @FXML private Label         labelUserName;
    @FXML private PasswordField passwordField;

    @FXML void initialize() {
        set(changeSuccess, "密码修改成功", "强制修改密码成功。");
    }
    @Override public void init() {
        super.init();
        labelUserName.setText(DataManager.forceChangePasswordUserName);
        passwordField.clear();
    }
    @FXML void changePassword() {
        DataManager.forceChangePassword(passwordField.getText());
        changeSuccess.showAndWait();
    }
    @FXML void back() {
        SceneManager.call("manageUser");
    }
}
