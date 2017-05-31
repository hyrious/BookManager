package bkmgr;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    public User(final String name, final String password) {
        super();
        this.name.set(name);
        this.password.set(password);
    }

    public User(final int id, final String name, final String password, final boolean canBorrow,
            final boolean canManageBook, final boolean canManageUser) {
        super();
        this.id.set(id);
        this.name.set(name);
        this.password.set(password);
        this.canBorrow.set(canBorrow);
        this.canManageBook.set(canManageBook);
        this.canManageUser.set(canManageUser);
    }

    // 我跟你讲，这页代码全是用下面六行生成出来的
    public SimpleIntegerProperty id            = new SimpleIntegerProperty(1);
    public SimpleStringProperty  name          = new SimpleStringProperty();
    public SimpleStringProperty  password      = new SimpleStringProperty();
    public SimpleBooleanProperty canBorrow     = new SimpleBooleanProperty(true);
    public SimpleBooleanProperty canManageBook = new SimpleBooleanProperty();
    public SimpleBooleanProperty canManageUser = new SimpleBooleanProperty();

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

    public final SimpleStringProperty nameProperty() {
        return this.name;
    }

    public final String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final String name) {
        this.nameProperty().set(name);
    }

    public final SimpleStringProperty passwordProperty() {
        return this.password;
    }

    public final String getPassword() {
        return this.passwordProperty().get();
    }

    public final void setPassword(final String password) {
        this.passwordProperty().set(password);
    }

    public final SimpleBooleanProperty canBorrowProperty() {
        return this.canBorrow;
    }

    public final boolean isCanBorrow() {
        return this.canBorrowProperty().get();
    }

    public final void setCanBorrow(final boolean canBorrow) {
        this.canBorrowProperty().set(canBorrow);
    }

    public final SimpleBooleanProperty canManageBookProperty() {
        return this.canManageBook;
    }

    public final boolean isCanManageBook() {
        return this.canManageBookProperty().get();
    }

    public final void setCanManageBook(final boolean canManageBook) {
        this.canManageBookProperty().set(canManageBook);
    }

    public final SimpleBooleanProperty canManageUserProperty() {
        return this.canManageUser;
    }

    public final boolean isCanManageUser() {
        return this.canManageUserProperty().get();
    }

    public final void setCanManageUser(final boolean canManageUser) {
        this.canManageUserProperty().set(canManageUser);
    }

    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (password == null) {
            if (other.password != null) return false;
        } else if (!password.equals(other.password)) return false;
        return true;
    }

    @Override public String toString() {
        return "User [id=" + id.get() + ", name=" + name.get() + ", password=" + password.get() + ", canBorrow="
                + canBorrow.get() + ", canManageBook=" + canManageBook.get() + ", canManageUser=" + canManageUser.get()
                + "]";
    }

    public static void main(String[] args) {
        System.out.println(new User("sa", "sa"));
    }
}
