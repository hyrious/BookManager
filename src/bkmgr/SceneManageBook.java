package bkmgr;

import java.net.URL;

public class SceneManageBook extends SceneBase {
    public SceneManageBook(URL resource) {
        super(resource);
    }
    public ControllerManageBook getController() {
        return super.<ControllerManageBook>_getController();
    }
}
