package bkmgr;

import java.util.HashMap;
import java.util.Stack;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public final class SceneManager {
    @Getter @Setter private static Stage window = null;
    private static Stack<Scene> stack = new Stack<Scene>();
    private static HashMap<String, Scene> hash = new HashMap<String, Scene>();

    public static void set(String id, Scene scene) {
        hash.put(id, scene);
    }

    public static Scene get(String id) {
        return hash.get(id);
    }

    public static void call(String id) {
        call(get(id));
    }

    public static void call(Scene scene) {
        window.setScene(scene);
        stack.push(scene);
    }

    public static void retn() {
        window.setScene(stack.pop());
    }

    public static void exit() {
        stack.clear();
        window.setScene(null);
        window.close();
    }
}
