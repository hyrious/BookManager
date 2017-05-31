package bkmgr;

import java.net.URL;

public class SceneManageUser extends SceneBase {
    public SceneManageUser(URL resource) {
        super(resource);
    }
    public ControllerManageUser getController() {
        return super.<ControllerManageUser>_getController();
    }
}
