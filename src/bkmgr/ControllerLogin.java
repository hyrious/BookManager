package bkmgr;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerLogin {
    @FXML private TextField     textFieldDatabase;
    @FXML private TextField     textFieldUsername;
    @FXML private PasswordField textFieldPassword;
    @FXML public void login() {
        System.out.println(getClass().getName() + "#login();");
    }
}
