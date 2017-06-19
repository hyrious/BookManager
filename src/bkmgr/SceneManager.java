package bkmgr;

import java.util.HashMap;
import javafx.stage.Stage;

public final class SceneManager {
    public static HashMap<String, SceneWrapper> hash = new HashMap<String, SceneWrapper>();
    public static Stage                         window;
    public static void call(SceneWrapper scene) {
        window.setScene(scene.getScene());
        scene.initController();
    }
    public static void call(String id) {
        call(get(id));
    }
    public static void exit() {
        window.setScene(null);
        window.close();
    }
    public static SceneWrapper get(String id) {
        return hash.get(id);
    }
    public static void set(String id, SceneWrapper scene) {
        hash.put(id, scene);
    }
    public static void start(Stage window, String id) {
        SceneManager.window = window;
        call(id);
        window.show();
    }
}
