package bkmgr.controller;

import bkmgr.DataManager;
import bkmgr.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;

public class ControllerChangePassword extends ControllerBase {
    private Alert changeFailed = new Alert(AlertType.ERROR);
    private Alert changeSuccess = new Alert(AlertType.INFORMATION);

    private void set(Alert x, String title, String content) {
        x.setTitle(title);
        x.setHeaderText(null);
        x.setContentText(content);
    }
    @FXML void initialize() {
        set(changeFailed, "修改密码失败", "原密码不正确。");
        set(changeSuccess, "修改密码成功", "修改密码成功。");
    }

    @FXML private PasswordField originalPassword;
    @FXML private PasswordField newPassword;

    @Override public void init() {
        super.init();
        originalPassword.clear();
        newPassword.clear();
    }
    @FXML void back() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("navigator");
    }
    @FXML void changePassword() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        if (DataManager.encrypt(originalPassword.getText()).equals(DataManager.user.getPassword())) {
            DataManager.changePassword(newPassword.getText());
            init();
            changeSuccess.showAndWait();
        } else changeFailed.showAndWait();
    }
}
