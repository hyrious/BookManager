package bkmgr;
import java.util.Stack;
import javafx.stage.Stage;
import javafx.scene.Scene;

public final class SceneManager {
  public static Stack<Scene> stack = new Stack<Scene>();
  public static Stage window;
  public static void setStage(Stage stage) { window = stage; }
  public static void setWindow(Stage stage) { window = stage; }
  public static void push(Scene scene) {
    stack.push(scene);
    window.setScene(scene);
  }
  public static void pop() {
    if (stack.empty()) {
      window.close();
    } else {
      stack.pop();
      window.setScene(stack.peek());
    }
  }
  public static void main(String[] args) {}
}
