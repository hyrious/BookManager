package bkmgr;

import java.net.URL;

public class SceneLogin extends SceneBase {
    public SceneLogin(URL resource) {
        super(resource);
    }

    public ControllerLogin getController() {
        return super.<ControllerLogin>_getController();
    }
}
