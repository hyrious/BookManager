import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private PasswordField pw;

    @FXML
    private TextField db;

    @FXML
    private TextField user;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
        System.out.println("Login " + db.getText() + " by " + user.getText() + " with password " + pw.getText() + ".");
    }

}
