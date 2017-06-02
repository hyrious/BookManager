package bkmgr;

import bkmgr.scene.SceneBorrow;
import bkmgr.scene.SceneChangePassword;
import bkmgr.scene.SceneLogin;
import bkmgr.scene.SceneManager;
import bkmgr.scene.SceneNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override public void start(Stage window) {
        SceneManager.set("login", new SceneLogin(getClass().getResource("UILogin.fxml")));
        SceneManager.set("navigator", new SceneNavigator(getClass().getResource("UINavigator.fxml")));
        SceneManager.set("borrow", new SceneBorrow(getClass().getResource("UIBorrow.fxml")));
        SceneManager.set("changePassword", new SceneChangePassword(getClass().getResource("UIChangePassword.fxml")));
        SceneManager.start(window, "login");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
