package bkmgr.wrapper;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BorrowBookLibrary {
    public BorrowBookLibrary(final String title, final Integer count) {
        super();
        this.title.set(title);
        this.count.set(count);
    }

    public SimpleStringProperty  title = new SimpleStringProperty();
    public SimpleIntegerProperty count = new SimpleIntegerProperty(1);

    public SimpleStringProperty titleProperty() {
        return this.title;
    }
    public String getTitle() {
        return this.titleProperty().get();
    }
    public void setTitle(final String title) {
        this.titleProperty().set(title);
    }
    public SimpleIntegerProperty countProperty() {
        return this.count;
    }
    public int getCount() {
        return this.countProperty().get();
    }
    public void setCount(final int count) {
        this.countProperty().set(count);
    }
}
