package bkmgr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

public class ManageController {
    @FXML private Tab tab_borrow;
    @FXML private TextField borrow_search;
    @FXML private TableView<?> borrow_table;
    @FXML private TextField return_search;
    @FXML private TableView<?> return_table;
    @FXML private Tab tab_book;
    @FXML private TextField book_search;
    @FXML private TableView<?> book_table;
    @FXML private TextField book_control_text;
    @FXML private Tab tab_user;
    @FXML private TextField user_search;
    @FXML private TableView<?> user_table;
    @FXML private TextField user_control_text;
    @FXML private CheckBox user_borrow;
    @FXML private CheckBox user_book;
    @FXML private CheckBox user_user;

    @FXML void bookAddOne(ActionEvent event) {

    }

    @FXML void bookDeleteOne(ActionEvent event) {

    }

    @FXML void borrow(ActionEvent event) {

    }

    @FXML void borrowUpdateSearch(InputMethodEvent event) {

    }

    @FXML void deleteUser(ActionEvent event) {

    }

    @FXML void ret(ActionEvent event) {

    }

    @FXML void returnUpdateSearch(InputMethodEvent event) {

    }

    @FXML void searchBook(InputMethodEvent event) {

    }

    @FXML void searchUser(InputMethodEvent event) {

    }

    @FXML void updateUser(ActionEvent event) {

    }

    @FXML void updateUserText(MouseEvent event) {

    }

}
