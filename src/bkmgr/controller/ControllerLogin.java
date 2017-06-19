package bkmgr.controller;

import bkmgr.DataManager;
import bkmgr.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerLogin extends ControllerBase {
    private Alert               loginFailed   = new Alert(AlertType.ERROR);
    private Alert               registFailed  = new Alert(AlertType.ERROR);
    private Alert               registSuccess = new Alert(AlertType.INFORMATION);
    private Alert               syntaxError   = new Alert(AlertType.ERROR);
    @FXML private TextField     textFieldDatabase;
    @FXML private PasswordField textFieldPassword;
    @FXML private TextField     textFieldUsername;
    private boolean check() {
        if (getDatabase().isEmpty() || getName().isEmpty()) {
            syntaxError.showAndWait();
            return false;
        }
        return true;
    }
    private String getDatabase() {
        String text = textFieldDatabase.getText();
        return text.isEmpty() ? textFieldDatabase.getPromptText() : text;
    }
    private String getName() {
        return textFieldUsername.getText();
    }
    private String getPassword() {
        return textFieldPassword.getText();
    }
    @Override public void init() {
        super.init();
        SceneManager.window.setTitle("图书管理系统");
        textFieldPassword.clear();
        textFieldUsername.requestFocus();
    }
    @FXML void initialize() {
        set(loginFailed, "登录失败", "用户名与密码不匹配。");
        set(registFailed, "注册失败", "已经存在该用户。");
        set(syntaxError, "格式不对", "请检查输入（数据库和用户名都不能为空）。");
        set(registSuccess, "注册成功", "你可以使用该账户登录。");
    }
    @FXML void login() {
        if (check()) {
            DataManager.init(getDatabase());
            if (DataManager.login(getName(), getPassword())) SceneManager.call("navigator");
            else loginFailed.showAndWait();
        }
    }
    @FXML void register() {
        if (check()) {
            DataManager.init(getDatabase());
            if (DataManager.regist(getName(), getPassword())) {
                textFieldPassword.clear();
                registSuccess.showAndWait();
            } else registFailed.showAndWait();
        }
    }
    private void set(Alert x, String title, String content) {
        x.setTitle(title);
        x.setHeaderText(null);
        x.setContentText(content);
    }
}
