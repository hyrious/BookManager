package bkmgr.controller;

import java.sql.SQLException;
import bkmgr.DataManager;
import bkmgr.SceneManager;
import bkmgr.wrapper.ManageBookLibrary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerManageBook extends ControllerBase {
    @FXML private TextField                              textFieldBookTitle;
    @FXML private TableView<ManageBookLibrary>           tableBooks;
    @FXML private TableColumn<ManageBookLibrary, String> tableColumnBookTitle;
    @FXML private TableColumn<ManageBookLibrary, String> tableColumnBookOwner;
    private ObservableList<ManageBookLibrary>            books = FXCollections.observableArrayList();

    @FXML void initialize() {
        tableColumnBookTitle.setCellValueFactory(new PropertyValueFactory<ManageBookLibrary, String>("title"));
        tableColumnBookOwner.setCellValueFactory(new PropertyValueFactory<ManageBookLibrary, String>("owner"));
    }

    private Integer maxBookID = null;

    public void loadBooks() {
        maxBookID = 0;
        books.clear();
        DataManager.select("select book_id, title, name from view_book", r -> {
            try {
                while (r.next()) {
                    Integer book_id = r.getInt("book_id");
                    maxBookID = Math.max(maxBookID, book_id);
                    books.add(new ManageBookLibrary(book_id, r.getString("title"), r.getString("name")));
                }
            } catch (SQLException e) {}
        });
        tableBooks.setItems(books);
    }
    public void init() {
        super.init();
        loadBooks();
    }
    @FXML void back() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("navigator");
    }
    @FXML void addOne() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        if (!textFieldBookTitle.getText().isEmpty()) {
            DataManager.addOneBook(textFieldBookTitle.getText());
            books.add(new ManageBookLibrary(maxBookID += 1, textFieldBookTitle.getText(), "sa"));
        }
    }
    @FXML void deleteOne() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        ManageBookLibrary book = tableBooks.getSelectionModel().getSelectedItem();
        if (book != null) {
            DataManager.deleteOneBook(book.getId());
            books.remove(book);
        }
    }
    @FXML void updateTextField() {
        textFieldBookTitle.setText(tableBooks.getSelectionModel().getSelectedItem().getTitle());
    }
}
