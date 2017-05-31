package bkmgr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    public Book(final String title, final int owner) {
        super();
        this.title.set(title);
        this.owner.set(owner);
    }

    public Book(final int id, final String title, final int owner) {
        super();
        this.id.set(id);
        this.title.set(title);
        this.owner.set(owner);
    }

    // 我跟你讲，这页代码全是用下面三行生成出来的
    public SimpleIntegerProperty id    = new SimpleIntegerProperty(1);
    public SimpleStringProperty  title = new SimpleStringProperty("");
    public SimpleIntegerProperty owner = new SimpleIntegerProperty(1);

    // JavaFX 真费劲
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

    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (owner == null) {
            if (other.owner != null) return false;
        } else if (!owner.equals(other.owner)) return false;
        if (title == null) {
            if (other.title != null) return false;
        } else if (!title.equals(other.title)) return false;
        return true;
    }

    @Override public String toString() {
        return "Book [id=" + id.get() + ", title=" + title.get() + ", owner=" + owner.get() + "]";
    }

    public static void main(String[] args) {
        System.out.println(new Book("LOVE", 1));
    }
}
