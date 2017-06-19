package bkmgr.wrapper;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BorrowBookMine {
    public SimpleStringProperty  due   = new SimpleStringProperty();
    public SimpleIntegerProperty id    = new SimpleIntegerProperty(1);
    public SimpleStringProperty  title = new SimpleStringProperty();
    public BorrowBookMine(final Integer id, final String title, final String due) {
        super();
        this.id.set(id);
        this.title.set(title);
        this.due.set(due);
    }
    public SimpleStringProperty dueProperty() {
        return this.due;
    }
    public String getDue() {
        return this.dueProperty().get();
    }
    public int getId() {
        return this.idProperty().get();
    }
    public String getTitle() {
        return this.titleProperty().get();
    }
    public SimpleIntegerProperty idProperty() {
        return this.id;
    }
    public void setDue(final String due) {
        this.dueProperty().set(due);
    }
    public void setId(final int id) {
        this.idProperty().set(id);
    }
    public void setTitle(final String title) {
        this.titleProperty().set(title);
    }
    public SimpleStringProperty titleProperty() {
        return this.title;
    }
}
