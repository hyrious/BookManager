package bkmgr;

import java.net.URL;

public class SceneBorrow extends SceneBase {
    public SceneBorrow(URL resource) {
        super(resource);
    }

    public ControllerBorrow getController() {
        return super.<ControllerBorrow>_getController();
    }
}
