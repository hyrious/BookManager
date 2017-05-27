package bkmgr;

import java.sql.*;
import bkmgr.DataBase;

public class User {
    private Integer id = null;
    private String name = null;
    private Integer permission = null;

    User(Integer id, String name, Integer permission) {
        this.id = id;
        this.name = name;
        this.permission = permission;
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

    public boolean borrow(String title) {
        if (!canBorrow()) return false;
        try {
            PreparedStatement st = DataBase.prepare("select id from book where title = ? and available = 1");
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return borrow(rs.getInt("id"));
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

    public static void main(String[] args) {
        DataBase.use("library.db", "sa", "sa");
        User user = new User(DataBase.user_id, DataBase.user_name, DataBase.permission);
        user.add_user("hyrious", "hyrious");
        user.add_book("LOVE");
        DataBase.close();
        DataBase.use("library.db", "hyrious", "hyrious");
        user = new User(DataBase.user_id, DataBase.user_name, DataBase.permission);
        user.borrow("LOVE");
    }
}
