package bkmgr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    public Book(final String title, final int owner) {
        super();
        this.title.set(title);
        this.owner.set(owner);
    }
    public Book(final Integer id, final String title, final Integer owner) {
        super();
        this.id.set(id);
        this.title.set(title);
        this.owner.set(owner);
    }

    // 我跟你讲，这页代码全是用下面三行生成出来的
    public SimpleIntegerProperty id    = new SimpleIntegerProperty(1);
    public SimpleStringProperty  title = new SimpleStringProperty("");
    public SimpleIntegerProperty owner = new SimpleIntegerProperty(1);

    public final SimpleIntegerProperty idProperty() {
        return this.id;
    }
    public final int getId() {
        return this.idProperty().get();
    }
    public final void setId(final int id) {
        this.idProperty().set(id);
    }
    public final SimpleStringProperty titleProperty() {
        return this.title;
    }
    public final String getTitle() {
        return this.titleProperty().get();
    }
    public final void setTitle(final String title) {
        this.titleProperty().set(title);
    }
    public final SimpleIntegerProperty ownerProperty() {
        return this.owner;
    }
    public final int getOwner() {
        return this.ownerProperty().get();
    }
    public final void setOwner(final int owner) {
        this.ownerProperty().set(owner);
    }
    @Override public String toString() {
        return "Book [id=" + id.get() + ", title=" + title.get() + ", owner=" + owner.get() + "]";
    }
}
