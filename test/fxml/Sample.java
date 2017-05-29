import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

public class Sample extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
    Scene scene = new Scene(root);
    stage.setTitle("FXML Welcome");
    stage.setScene(scene);
    stage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
