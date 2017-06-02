package bkmgr.controller;

import bkmgr.scene.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class ControllerChangePassword extends ControllerBase {
    @FXML private PasswordField originalPassword;
    @FXML private PasswordField newPassword;

    @FXML void back() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("navigator");
    }
    @FXML void changePassword() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
