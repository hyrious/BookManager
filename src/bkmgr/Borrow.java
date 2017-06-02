package bkmgr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Borrow {
    public Borrow(final Integer userID, final Integer bookID, final String title, final String name, final String due) {
        super();
        this.userID.set(userID);
        this.bookID.set(bookID);
        this.title.set(title);
        this.name.set(name);
        this.due.set(due);
    }

    // 我跟你讲，这页代码全是用下面五行生成出来的
    public SimpleIntegerProperty userID = new SimpleIntegerProperty(1);
    public SimpleIntegerProperty bookID = new SimpleIntegerProperty(1);
    public SimpleStringProperty  title  = new SimpleStringProperty();
    public SimpleStringProperty  name   = new SimpleStringProperty();
    public SimpleStringProperty  due    = new SimpleStringProperty();

    public final SimpleIntegerProperty userIDProperty() {
        return this.userID;
    }
    public final int getUserID() {
        return this.userIDProperty().get();
    }
    public final void setUserID(final int userID) {
        this.userIDProperty().set(userID);
    }
    public final SimpleIntegerProperty bookIDProperty() {
        return this.bookID;
    }
    public final int getBookID() {
        return this.bookIDProperty().get();
    }
    public final void setBookID(final int bookID) {
        this.bookIDProperty().set(bookID);
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
    public final SimpleStringProperty nameProperty() {
        return this.name;
    }
    public final String getName() {
        return this.nameProperty().get();
    }
    public final void setName(final String name) {
        this.nameProperty().set(name);
    }
    public final SimpleStringProperty dueProperty() {
        return this.due;
    }
    public final String getDue() {
        return this.dueProperty().get();
    }
    public final void setDue(final String due) {
        this.dueProperty().set(due);
    }
    @Override public String toString() {
        return "Borrow [user_id=" + userID.get() + ", book_id=" + bookID.get() + ", title=" + title.get() + ", name="
                + name.get() + ", due=" + due.get() + "]";
    }
}
