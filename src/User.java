package bkmgr;

import java.util.HashSet;
import java.sql.*;

public class User {
    private Integer id = null;
    private String name = null;
    private Integer permission = null;
    public User(Integer id, String name, Integer permission) {
        this.id = id;
        this.name = name;
        this.permission = permission;
    }

    public static User fromDataBase() {
        return new User(DataBase.user_id, DataBase.user_name, DataBase.permission);
    }

    public static void main(String[] args) {
        DataBase.use("library.db", "sa", "sa");
        User user = User.fromDataBase();
        user.add_user("hyrious", "hyrious");
        user.add_book("LOVE");
        user.add_book("HATE");
        System.out.println(user);
        DataBase.close();
        DataBase.use("library.db", "hyrious", "hyrious");
        user = User.fromDataBase();
        System.out.println("borrow book(2): " + user.borrow("HATE"));
        // System.out.println("borrow book(1): " + user.borrow("LOVE"));
        System.out.println("return book(1): " + user.ret(1));
        for (Book i : user.books()) {
            System.out.println(i);
        }
        System.out.println(user);
    }

    @Override
    public String toString() {
        return "#<User id=" + id + " name='" + name + "' permission=" + permission + '>';
    }

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPermission() {
        return permission;
    }

    public boolean can(Integer i) {
        return (permission & i) == i;
    }

    public boolean canBorrow() {
        return can(1);
    }

    public boolean canManageBook() {
        return can(2);
    }

    public boolean canManageUser() {
        return can(4);
    }

    public boolean add_user(String name, String password) {
        if (!canManageUser()) return false;
        try {
            PreparedStatement st = DataBase.prepare("insert into user (name, password) values ( ?, ? )");
            st.setString(1, name);
            st.setString(2, DataBase.encrypt(password));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean add_user(String name, String password, Integer permission) {
        if (!canManageUser()) return false;
        try {
            PreparedStatement st = DataBase.prepare("insert into user (name, password, permission) values ( ?, ?, ? )");
            st.setString(1, name);
            st.setString(2, DataBase.encrypt(password));
            st.setInt(3, permission);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete_user(Integer user_id) {
        if (!canManageUser()) return false;
        try {
            PreparedStatement st = DataBase.prepare("delete from user where id = ?");
            st.setInt(1, user_id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean add_book(String title) {
        if (!canManageBook()) return false;
        try {
            PreparedStatement st = DataBase.prepare("insert into book (title) values ( ? )");
            st.setString(1, title);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete_book(Integer book_id) {
        if (!canManageBook()) return false;
        try {
            PreparedStatement st = DataBase.prepare("delete from book where id = ?");
            st.setInt(1, book_id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean borrow(String title) {
        if (!canBorrow()) return false;
        try {
            PreparedStatement st = DataBase.prepare("select id from book where title = ? and available = 1");
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            return rs.next() ? borrow(rs.getInt("id")) : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean borrow(Integer book_id) {
        if (!canBorrow()) return false;
        try {
            PreparedStatement st = DataBase.prepare("insert into borrow (user_id, book_id, due) values ( ?, ?, date('now', '+14 day') )");
            st.setInt(1, this.id);
            st.setInt(2, book_id);
            st.executeUpdate();
            PreparedStatement mk = DataBase.prepare("update book set available = 0 where id = ?");
            mk.setInt(1, book_id);
            mk.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean ret(Integer book_id) {
        if (!canBorrow()) return false;
        if (!bookIDs().contains(book_id)) return false;
        try {
            PreparedStatement rt = DataBase.prepare("insert into return (user_id, book_id, ret) values ( ?, ?, date('now') )");
            rt.setInt(1, this.id);
            rt.setInt(2, book_id);
            rt.executeUpdate();
            PreparedStatement st = DataBase.prepare("delete from borrow where user_id = ? and book_id = ?");
            st.setInt(1, this.id);
            st.setInt(2, book_id);
            st.executeUpdate();
            PreparedStatement mk = DataBase.prepare("update book set available = 1 where id = ?");
            mk.setInt(1, book_id);
            mk.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public HashSet<Book> books() {
        if (!canBorrow()) return null;
        try {
            HashSet<Book> ret = new HashSet<Book>();
            PreparedStatement st = DataBase.prepare("select book_id, title from borrow_book where user_id = ?");
            st.setInt(1, this.id);
            ResultSet rs = st.executeQuery();
            while (rs.next())
                ret.add(new Book(rs.getInt("book_id"), rs.getString("title")));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashSet<Integer> bookIDs() {
        if (!canBorrow()) return null;
        try {
            HashSet<Integer> ret = new HashSet<Integer>();
            PreparedStatement st = DataBase.prepare("select book_id from borrow_book where user_id = ?");
            st.setInt(1, this.id);
            ResultSet rs = st.executeQuery();
            while (rs.next())
                ret.add(rs.getInt("book_id"));
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
