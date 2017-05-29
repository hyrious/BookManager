package bkmgr;

import lombok.Data;

public @Data class Book {
    private Integer id;
    private String title;
    private Boolean owner;

    public Book(Integer id, String title, Boolean owner) {
        super();
        this.id = id;
        this.title = title;
        this.owner = owner;
    }

    public Book(String title) {
        super();
        this.title = title;
    }

}
