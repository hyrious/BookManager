package bkmgr.wrapper;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ManageBookData {
    public SimpleIntegerProperty id    = new SimpleIntegerProperty(1);
    public SimpleStringProperty  owner = new SimpleStringProperty();
    public SimpleStringProperty  title = new SimpleStringProperty();
    public ManageBookData(final Integer id, final String title, final String owner) {
        super();
        this.id.set(id);
        this.title.set(title);
        this.owner.set(owner);
    }
    public final int getId() {
        return this.idProperty().get();
    }
    public final String getOwner() {
        return this.ownerProperty().get();
    }
    public String getTitle() {
        return this.titleProperty().get();
    }
    public final SimpleIntegerProperty idProperty() {
        return this.id;
    }
    public final SimpleStringProperty ownerProperty() {
        return this.owner;
    }
    public final void setId(final int id) {
        this.idProperty().set(id);
    }
    public final void setOwner(final String owner) {
        this.ownerProperty().set(owner);
    }
    public void setTitle(final String title) {
        this.titleProperty().set(title);
    }
    public SimpleStringProperty titleProperty() {
        return this.title;
    }
}
