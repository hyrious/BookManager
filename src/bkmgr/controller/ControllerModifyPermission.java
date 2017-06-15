package bkmgr.controller;

import bkmgr.DataManager;
import bkmgr.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class ControllerModifyPermission extends ControllerBase {
    @FXML private ToggleButton buttonBorrow;
    @FXML private ToggleButton buttonManageBook;
    @FXML private ToggleButton buttonManageUser;
    @FXML private Label        labelUserName;

    @Override public void init() {
        labelUserName.setText(DataManager.modifyPermissionUserName);
        buttonBorrow.setSelected(DataManager.modifyPermissionUserCanBorrow);
        buttonManageBook.setSelected(DataManager.modifyPermissionUserCanManageBook);
        buttonManageUser.setSelected(DataManager.modifyPermissionUserCanManageUser);
    }
    @FXML void back() {
        SceneManager.call("manageUser");
    }
    @FXML void confirm() {
        DataManager.modifyPermission((buttonBorrow.isSelected() ? 1 : 0) | (buttonManageBook.isSelected() ? 2 : 0)
                | (buttonManageUser.isSelected() ? 4 : 0));
        back();
    }
    @FXML void toggleBorrow() {
        buttonManageUser.setSelected(false);
    }
    @FXML void toggleBook() {}
    @FXML void toggleUser() {
        buttonBorrow.setSelected(false);
    }
}
