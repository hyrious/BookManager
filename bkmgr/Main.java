package bkmgr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override public void start(Stage window) throws Exception {
        SceneManager.setWindow(window);

        SceneManager.set("login", new Scene(FXMLLoader.load(getClass().getResource("login.fxml"))));
        // SceneManager.set("borrow", new
        // Scene(FXMLLoader.load(getClass().getResource("borrow.fxml"))));

        DataManager.init();
        SceneManager.call("login");

        window.setTitle("图书管理系统");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
