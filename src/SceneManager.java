package bkmgr;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public final class SceneManager {
    public static Stack<Scene> stack = new Stack<Scene>();
    public static Stage window;

    public static void setStage(Stage stage) {
        window = stage;
    }

    public static void next(Scene scene) {
        stack.push(scene);
        window.setScene(scene);
    }

    public static void retn(Scene scene) {
        if (!stack.empty()) {
            stack.pop();
            window.setScene(stack.peek());
        } else {
            window.close();
        }
    }

    public static void main(String[] args) {

    }
}
