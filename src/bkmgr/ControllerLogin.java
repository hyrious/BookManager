package bkmgr;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerLogin {
    @FXML private TextField     textFieldDatabase;
    @FXML private TextField     textFieldUsername;
    @FXML private PasswordField textFieldPassword;
    // private User getUser() {
    // return new User(textFieldUsername.getText(),
    // DataManager.encrypt(textFieldPassword.getText()));
    // }
    private Alert               emptyError   = new Alert(AlertType.ERROR);
    private Alert               loginFailed  = new Alert(AlertType.ERROR);
    private Alert               registFailed = new Alert(AlertType.ERROR);

    @SuppressWarnings("unchecked") private <T> void set(T x, String title, String content) {
        ((Dialog<String>) x).setTitle(title);
        ((Dialog<String>) x).setHeaderText(null);
        ((Dialog<String>) x).setContentText(content);
    }

    @FXML void initialize() {
        set(emptyError, "空白的输入", "什么都不输入是不合法的。");
        set(loginFailed, "登录失败", "用户名与密码不匹配。");
        set(registFailed, "注册失败", "已经存在该用户。");
    }

    @FXML void login() {
        System.out.println(getClass().getName() + "#login();");
        // DataManager.init(textFieldDatabase.getText());
        // if (DataManager.login(getUser()))
        // System.out.println("SceneManager.call(next_scene)");
        // else loginFailed.showAndWait();
    }

    @FXML void register() {
        System.out.println(getClass().getName() + "#register();");
        // DataManager.init(textFieldDatabase.getText());
        // if (textFieldUsername.getText().isEmpty()) emptyError.showAndWait();
        // else if (DataManager.regist(getUser()))
        // System.out.println("SceneManager.call(next_scene)");
        // else registFailed.showAndWait();
    }
}
