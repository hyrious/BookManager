package bkmgr.controller;

import bkmgr.DataManager;
import bkmgr.scene.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerNavigator extends ControllerBase {
    @FXML private Label  labelUsername;
    @FXML private Button buttonBorrow;
    @FXML private Button buttonManageBook;
    @FXML private Button buttonManageUser;
    @FXML private Button buttonChangePassword;

    public void init() {
        super.init();
        SceneManager.window.setTitle(DataManager.user.getName() + " - 图书管理系统");
        labelUsername.setText(DataManager.user.getName());
        buttonBorrow.setDisable(!DataManager.user.isCanBorrow());
        buttonManageBook.setDisable(!DataManager.user.isCanManageBook());
        buttonManageUser.setDisable(!DataManager.user.isCanManageUser());
    }
    @FXML void borrow() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("borrow");
    }
    @FXML void changePassword() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("changePassword");
    }
    @FXML void manageBook() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    @FXML void manageUser() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    @FXML void logout() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("login");
    }
}
