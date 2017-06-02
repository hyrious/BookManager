package bkmgr;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import lombok.Data;

public @Data abstract class SceneBase {
    public Scene      scene;
    public FXMLLoader loader;

    public SceneBase(URL resource) {
        loader = new FXMLLoader(resource);
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            System.err.println("Load resource " + resource + " failed.");
            e.printStackTrace();
        }
    }

    public <T> T _getController() {
        return loader.<T>getController();
    }

    public void initController() {
        loader.<ControllerBase>getController().init();
    }
}
