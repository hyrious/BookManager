package bkmgr;

import java.io.IOException;
import java.net.URL;
import bkmgr.controller.ControllerBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import lombok.Data;

public @Data class SceneWrapper {
    private ControllerBase controller;
    private Scene          scene;
    public SceneWrapper(URL resource) {
        FXMLLoader loader = new FXMLLoader(resource);
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            System.err.println("Load resource " + resource + " failed.");
            e.printStackTrace();
        } finally {
            controller = loader.getController();
        }
    }
    public void initController() {
        controller.init();
    }
}
