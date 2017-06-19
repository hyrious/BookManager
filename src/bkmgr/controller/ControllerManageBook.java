package bkmgr.controller;

import java.sql.SQLException;
import bkmgr.DataManager;
import bkmgr.SceneManager;
import bkmgr.wrapper.ManageBookData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerManageBook extends ControllerBase {
    private ObservableList<ManageBookData>            books     = FXCollections.observableArrayList();
    private Integer                                   maxBookID = null;
    @FXML private TableView<ManageBookData>           tableBooks;
    @FXML private TableColumn<ManageBookData, String> tableColumnBookOwner;
    @FXML private TableColumn<ManageBookData, String> tableColumnBookTitle;
    @FXML private TextField                           textFieldBookTitle;
    @FXML void addOne() {
        if (!textFieldBookTitle.getText().isEmpty()) {
            DataManager.addOneBook(textFieldBookTitle.getText());
            books.add(new ManageBookData(maxBookID += 1, textFieldBookTitle.getText(), "sa"));
        }
    }
    @FXML void back() {
        SceneManager.call("navigator");
    }
    @FXML void deleteOne() {
        ManageBookData book = tableBooks.getSelectionModel().getSelectedItem();
        if (book != null) {
            DataManager.deleteOneBook(book.getId());
            books.remove(book);
        }
    }
    @Override public void init() {
        super.init();
        loadBooks();
    }
    @FXML void initialize() {
        tableColumnBookTitle.setCellValueFactory(new PropertyValueFactory<ManageBookData, String>("title"));
        tableColumnBookOwner.setCellValueFactory(new PropertyValueFactory<ManageBookData, String>("owner"));
    }
    public void loadBooks() {
        maxBookID = 0;
        books.clear();
        DataManager.select("select book_id, title, name from view_book", r -> {
            try {
                while (r.next()) {
                    Integer book_id = r.getInt("book_id");
                    maxBookID = Math.max(maxBookID, book_id);
                    books.add(new ManageBookData(book_id, r.getString("title"), r.getString("name")));
                }
            } catch (SQLException e) {}
        });
        tableBooks.setItems(books);
    }
    @FXML void updateTextField() {
        ManageBookData bookData = tableBooks.getSelectionModel().getSelectedItem();
        if (bookData != null) textFieldBookTitle.setText(bookData.getTitle());
    }
}
