package bkmgr;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(final String[] arguments) {
        launch(arguments);
    }

    @Override
    public void start(final Stage window) {
        SceneManager.use(window);
        final Button button = new Button();
        button.setText("Hello World");
        button.setPrefWidth(144);
        button.setPrefHeight(48);
        button.setOnAction(e -> System.out.println("Hello World!"));
        final StackPane root = new StackPane();
        root.getChildren().add(button);
        final Scene scene = new Scene(root, 320, 200);
        window.setTitle("Hello World!");
        window.setScene(scene);
        window.show();
    }
}
