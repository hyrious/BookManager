package bkmgr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField db;
    @FXML private TextField user;
    @FXML private PasswordField pw;

    @FXML void exit(ActionEvent event) {
        SceneManager.exit();
    }

    @FXML void login(ActionEvent event) {
        DataManager.setUser(new User(user.getText(), DataManager.encrypt(pw.getText())));
        DataManager.login();
        if (DataManager.loggedin()) {
            System.out.println("Logged in by " + DataManager.getUser());
            System.out.println("SceneManager.call(\"borrow\")");
        }
    }

}
