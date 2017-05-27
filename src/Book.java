package bkmgr;

public class Book {
    private Integer id;
    private String title;
    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public static void main(String[] args) {
        // System.out.println(new Book(1, "LOVE"));
    }

    public Integer getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "#<Book id=" + id + " title='" + title + "'>";
    }
}
