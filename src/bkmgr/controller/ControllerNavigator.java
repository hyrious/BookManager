package bkmgr.controller;

import bkmgr.DataManager;
import bkmgr.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerNavigator extends ControllerBase {
    @FXML private Button buttonBorrow;
    @FXML private Button buttonChangePassword;
    @FXML private Button buttonManageBook;
    @FXML private Button buttonManageUser;
    @FXML private Label  labelUsername;
    @FXML void borrow() {
        SceneManager.call("borrow");
    }
    @Override public void init() {
        super.init();
        SceneManager.window.setTitle(DataManager.user.getName() + " - 图书管理系统");
        labelUsername.setText(DataManager.user.getName());
        buttonBorrow.setDisable(!DataManager.user.getCanBorrow());
        buttonManageBook.setDisable(!DataManager.user.getCanManageBook());
        buttonManageUser.setDisable(!DataManager.user.getCanManageUser());
    }
    @FXML void logout() {
        SceneManager.call("login");
    }
    @FXML void manageBook() {
        SceneManager.call("manageBook");
    }
    @FXML void manageUser() {
        SceneManager.call("manageUser");
    }
    @FXML void personalInfo() {
        SceneManager.call("personalInfo");
    }
}
