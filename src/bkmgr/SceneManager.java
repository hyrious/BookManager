package bkmgr;

import java.util.HashMap;
import java.util.Stack;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class SceneManager {
    public static Stage                      window;
    public static Scene                      scene;
    public static HashMap<String, SceneBase> hash  = new HashMap<String, SceneBase>();
    public static Stack<String>              stack = new Stack<String>();
    public static void set(String id, SceneBase scene) {
        hash.put(id, scene);
    }
    public static SceneBase get(String id) {
        return hash.get(id);
    }
    public static void call(String id) {
        call(get(stack.push(id)));
    }
    public static void call(SceneBase scene) {
        window.setScene(SceneManager.scene = scene.getScene());
    }
    public static void retn() {
        if (stack.empty()) exit();
        else window.setScene(SceneManager.scene = get(stack.pop()).getScene());
    }
    public static void exit() {
        stack.clear();
        window.setScene(null);
        window.close();
    }
    public static void start(Stage window, String id) {
        SceneManager.window = window;
        call(id);
        window.show();
    }
}
