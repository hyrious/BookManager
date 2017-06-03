package bkmgr;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static String camelize(final String line) {
        return Character.toLowerCase(line.charAt(0)) + line.substring(1);
    }
    private void register(final String id) {
        SceneManager.set(camelize(id), new SceneWrapper(getClass().getResource("UI" + id + ".fxml")));
    }
    private void register(final String... ids) {
        for (String id : ids)
            register(id);
    }
    @Override public void start(Stage window) {
        register("Login", "Navigator", "Borrow", "ChangePassword", "ManageBook", "ManageUser");
        SceneManager.start(window, "login");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
