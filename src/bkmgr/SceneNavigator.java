package bkmgr;

import java.net.URL;

public class SceneNavigator extends SceneBase {
    public SceneNavigator(URL resource) {
        super(resource);
    }

    public ControllerNavigator getController() {
        return super.<ControllerNavigator>_getController();
    }
}
