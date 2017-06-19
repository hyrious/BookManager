package bkmgr.wrapper;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BorrowBookLibrary {
    public SimpleIntegerProperty count = new SimpleIntegerProperty(1);
    public SimpleStringProperty  title = new SimpleStringProperty();
    public BorrowBookLibrary(final String title, final Integer count) {
        super();
        this.title.set(title);
        this.count.set(count);
    }
    public SimpleIntegerProperty countProperty() {
        return this.count;
    }
    public int getCount() {
        return this.countProperty().get();
    }
    public String getTitle() {
        return this.titleProperty().get();
    }
    public void setCount(final int count) {
        this.countProperty().set(count);
    }
    public void setTitle(final String title) {
        this.titleProperty().set(title);
    }
    public SimpleStringProperty titleProperty() {
        return this.title;
    }
}
