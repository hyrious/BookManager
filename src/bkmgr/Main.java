package bkmgr;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import bkmgr.scene.SceneBase;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static String camelize(final String line) {
        return Character.toLowerCase(line.charAt(0)) + line.substring(1);
    }
    private void register(final String id) {
        try {
            SceneManager.set(camelize(id), (SceneBase) Class.forName("bkmgr.scene.Scene" + id).getConstructor(URL.class)
                    .newInstance(getClass().getResource("UI" + id + ".fxml")));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            System.err.println("What happened when register \"" + id + "\" ?");
            e.printStackTrace();
        }
    }
    private void register(final String... ids) {
        for (String id : ids)
            register(id);
    }
    @Override public void start(Stage window) {
        register("Login", "Navigator", "Borrow", "ChangePassword", "ManageBook");
        SceneManager.start(window, "login");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
