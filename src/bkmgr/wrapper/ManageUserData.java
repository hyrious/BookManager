package bkmgr.wrapper;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ManageUserData {
    public SimpleBooleanProperty canBorrow     = new SimpleBooleanProperty(true);
    public SimpleBooleanProperty canManageBook = new SimpleBooleanProperty(false);
    public SimpleBooleanProperty canManageUser = new SimpleBooleanProperty(false);
    public SimpleIntegerProperty id            = new SimpleIntegerProperty(1);
    public SimpleStringProperty  name          = new SimpleStringProperty();
    public SimpleStringProperty  password      = new SimpleStringProperty();
    public ManageUserData(final int id, final String name, final String password, final boolean canBorrow,
            final boolean canManageBook, final boolean canManageUser) {
        super();
        this.id.set(id);
        this.name.set(name);
        this.password.set(password);
        this.canBorrow.set(canBorrow);
        this.canManageBook.set(canManageBook);
        this.canManageUser.set(canManageUser);
    }
    public ManageUserData(final int id, final String name, final String password, final int permission) {
        this(id, name, password, (permission & 1) == 1, (permission & 2) == 2, (permission & 4) == 4);
    }
    public final SimpleBooleanProperty canBorrowProperty() {
        return this.canBorrow;
    }
    public final SimpleBooleanProperty canManageBookProperty() {
        return this.canManageBook;
    }
    public final SimpleBooleanProperty canManageUserProperty() {
        return this.canManageUser;
    }
    public final int getId() {
        return this.idProperty().get();
    }
    public final String getName() {
        return this.nameProperty().get();
    }
    public final String getPassword() {
        return this.passwordProperty().get();
    }
    public final SimpleIntegerProperty idProperty() {
        return this.id;
    }
    public final boolean isCanBorrow() {
        return this.canBorrowProperty().get();
    }
    public final boolean isCanManageBook() {
        return this.canManageBookProperty().get();
    }
    public final boolean isCanManageUser() {
        return this.canManageUserProperty().get();
    }
    public final SimpleStringProperty nameProperty() {
        return this.name;
    }
    public final SimpleStringProperty passwordProperty() {
        return this.password;
    }
    public final void setCanBorrow(final boolean canBorrow) {
        this.canBorrowProperty().set(canBorrow);
    }
    public final void setCanManageBook(final boolean canManageBook) {
        this.canManageBookProperty().set(canManageBook);
    }
    public final void setCanManageUser(final boolean canManageUser) {
        this.canManageUserProperty().set(canManageUser);
    }
    public final void setId(final int id) {
        this.idProperty().set(id);
    }
    public final void setName(final String name) {
        this.nameProperty().set(name);
    }
    public final void setPassword(final String password) {
        this.passwordProperty().set(password);
    }
}
