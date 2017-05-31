package bkmgr;

import java.net.URL;

public class SceneRegister extends SceneBase {
    public SceneRegister(URL resource) {
        super(resource);
    }
    public ControllerRegister getController() {
        return super.<ControllerRegister>_getController();
    }
}
