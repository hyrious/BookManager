package bkmgr;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override public void start(Stage window) {
        window.setTitle("图书管理系统");
        SceneManager.set("login", new SceneLogin(getClass().getResource("UILogin.fxml")));
        SceneManager.start(window, "login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
