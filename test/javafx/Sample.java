import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Sample extends Application {
    
    @Override
    public void start(final Stage primaryStage) {
        // Creating the Java button
        final Button button = new Button();
        // Setting text to button
        button.setText("Hello World");
        // Registering a handler for button
        button.setOnAction((ActionEvent event) -> {
            // Printing Hello World! to the console
            System.out.println("Hello World!");
        });
        // Initializing the StackPane class
        final StackPane root = new StackPane();
        // Adding all the nodes to the FlowPane
        root.getChildren().add(button);
        // Creating a scene object
        final Scene scene = new Scene(root, 300, 250);
        // Adding the title to the window (primaryStage)
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        // Show the window(primaryStage)
        primaryStage.show();
    }
 
    /**
     * Main function that opens the "Hello World!" window
     * 
     * @param args the command line arguments
     */
    public static void main(final String[] arguments) {
        launch(arguments);
    }
}
