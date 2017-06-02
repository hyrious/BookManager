package bkmgr.controller;

import java.sql.SQLException;
import bkmgr.DataManager;
import bkmgr.borrow.BorrowBookLibrary;
import bkmgr.borrow.BorrowBookMine;
import bkmgr.scene.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerBorrow extends ControllerBase {
    @FXML private TableView<BorrowBookLibrary>            tableLibrary;
    @FXML private TableView<BorrowBookMine>               tableMine;
    @FXML private TableColumn<BorrowBookLibrary, String>  tableColumnLibraryTitle;
    @FXML private TableColumn<BorrowBookLibrary, Integer> tableColumnLibraryCount;
    @FXML private TableColumn<BorrowBookMine, String>     tableColumnMineTitle;
    @FXML private TableColumn<BorrowBookMine, String>     tableColumnMineDue;
    private ObservableList<BorrowBookLibrary>             libraryBooks = FXCollections.observableArrayList();
    private ObservableList<BorrowBookMine>                myBooks      = FXCollections.observableArrayList();

    @FXML void initialize() {
        tableColumnLibraryTitle.setCellValueFactory(new PropertyValueFactory<BorrowBookLibrary, String>("title"));
        tableColumnLibraryCount.setCellValueFactory(new PropertyValueFactory<BorrowBookLibrary, Integer>("count"));
        tableColumnMineTitle.setCellValueFactory(new PropertyValueFactory<BorrowBookMine, String>("title"));
        tableColumnMineDue.setCellValueFactory(new PropertyValueFactory<BorrowBookMine, String>("due"));
    }
    public void loadLibraryBooks() {
        libraryBooks.clear();
        DataManager.select("select title, count(*) as count from book where owner = 1 group by title", r -> {
            try {
                while (r.next())
                    libraryBooks.add(new BorrowBookLibrary(r.getString("title"), r.getInt("count")));
            } catch (SQLException e) {}
        });
        tableLibrary.setItems(libraryBooks);
    }
    public void loadMyBooks() {
        myBooks.clear();
        DataManager.select("select book_id, title, due from view_borrow where user_id = ?", r -> {
            try {
                while (r.next())
                    myBooks.add(new BorrowBookMine(r.getInt("book_id"), r.getString("title"), r.getString("due")));
            } catch (SQLException e) {}
        }, DataManager.user.getId());
        tableMine.setItems(myBooks);
    }
    public void init() {
        super.init();
        loadLibraryBooks();
        loadMyBooks();
    }
    @FXML void borrow() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        BorrowBookLibrary books = tableLibrary.getSelectionModel().getSelectedItem();
        if (books != null && DataManager.borrow(books.getTitle())) {
            books.setCount(books.getCount() - 1);
            if (books.getCount() == 0) libraryBooks.remove(books);
            myBooks.add(new BorrowBookMine(DataManager.getBorrowBookID(), books.getTitle(),
                                           DataManager.getBorrowDue()));
        }
    }
    @FXML void ret() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        BorrowBookMine book = tableMine.getSelectionModel().getSelectedItem();
        if (book != null && DataManager.ret(book.getId())) {
            boolean notContainsFlag = true;
            for (BorrowBookLibrary books : libraryBooks)
                if (books.getTitle().equals(book.getTitle())) {
                    books.setCount(books.getCount() + 1);
                    notContainsFlag = false;
                    break;
                }
            if (notContainsFlag) libraryBooks.add(new BorrowBookLibrary(book.getTitle(), 1));
            myBooks.remove(book);
        }
    }
    @FXML void back() {
        System.out.println(getClass().getName() + '#' + Thread.currentThread().getStackTrace()[1].getMethodName());
        SceneManager.call("navigator");
    }
}
