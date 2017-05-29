package bkmgr;

import java.util.Stack;

import javafx.scene.Scene;
import javafx.stage.Stage;

public final class SceneManager {
    private static Stage window = null;
    private static Stack<Scene> stack = new Stack<Scene>();

    public static void call(Scene scene) {
        window.setScene(scene);
        stack.push(scene);
    }

    public static void retn() {
        window.setScene(stack.pop());
    }

    public static void exit() {
        window.setScene(null);
    }
}
