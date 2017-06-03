package bkmgr.controller;

import bkmgr.DataManager;
import bkmgr.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerNavigator extends ControllerBase {
    @FXML private Label  labelUsername;
    @FXML private Button buttonBorrow;
    @FXML private Button buttonManageBook;
    @FXML private Button buttonManageUser;
    @FXML private Button buttonChangePassword;

    @Override public void init() {
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
    @FXML void personalInfo() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("personalInfo");
    }
    @FXML void manageBook() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("manageBook");
    }
    @FXML void manageUser() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("manageUser");
    }
    @FXML void logout() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("login");
    }
}
