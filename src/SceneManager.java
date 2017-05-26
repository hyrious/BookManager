package bkmgr;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Stack;

public final class SceneManager {
    public static HashMap<String, Scene> hash = new HashMap<String, Scene>();
    public static Stack<Scene> stack = new Stack<Scene>();
    public static Stage window;

    public static void use(Stage stage) {
        window = stage;
    }

    public static void push(Scene scene) {
        stack.push(scene);
        window.setScene(scene);
    }

    public static void push(String id) {
        Scene scene = hash.get(id);
        if (scene != null) push(scene);
    }

    public static void pop() {
        if (stack.empty()) {
            window.close();
        } else {
            stack.pop();
            window.setScene(stack.peek());
        }
    }

    public static void set(String id, Scene scene) {
        hash.put(id, scene);
    }

    public static void main(String[] args) {

    }
}
