package bkmgr.scene;

import java.util.HashMap;
import javafx.stage.Stage;

public final class SceneManager {
    public static Stage                      window;
    public static HashMap<String, SceneBase> hash = new HashMap<String, SceneBase>();

    public static void set(String id, SceneBase scene) {
        hash.put(id, scene);
    }
    public static SceneBase get(String id) {
        return hash.get(id);
    }
    public static void call(String id) {
        call(get(id));
    }
    public static void call(SceneBase scene) {
        window.setScene(scene.getScene());
        scene.initController();
    }
    public static void exit() {
        window.setScene(null);
        window.close();
    }
    public static void start(Stage window, String id) {
        SceneManager.window = window;
        call(id);
        window.show();
    }
}
