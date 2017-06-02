package bkmgr.borrow;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BorrowBookMine {
    public BorrowBookMine(final Integer id, final String title, final String due) {
        super();
        this.id.set(id);
        this.title.set(title);
        this.due.set(due);
    }

    public SimpleIntegerProperty id    = new SimpleIntegerProperty(1);
    public SimpleStringProperty  title = new SimpleStringProperty();
    public SimpleStringProperty  due   = new SimpleStringProperty();

    public SimpleIntegerProperty idProperty() {
        return this.id;
    }
    public int getId() {
        return this.idProperty().get();
    }
    public void setId(final int id) {
        this.idProperty().set(id);
    }
    public SimpleStringProperty titleProperty() {
        return this.title;
    }
    public String getTitle() {
        return this.titleProperty().get();
    }
    public void setTitle(final String title) {
        this.titleProperty().set(title);
    }
    public SimpleStringProperty dueProperty() {
        return this.due;
    }
    public String getDue() {
        return this.dueProperty().get();
    }
    public void setDue(final String due) {
        this.dueProperty().set(due);
    }
}
