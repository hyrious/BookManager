package bkmgr;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

import lombok.Getter;
import lombok.Setter;

public class DataManager {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String ALGORITHM = "SHA1";
    private static Connection conn = null;
    private static Boolean loggedin = false;
    @Getter @Setter private static String file = "library.db";
    @Getter @Setter private static User user = null;

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String sha1(String str) {
        if (str == null)
            return null;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        digest.update(str.getBytes());
        return bytesToHex(digest.digest());
    }

    public static String encrypt(String str) {
        return sha1((new StringBuffer(str.concat("bkmgr"))).reverse().toString());
    }

    public static void connect() {
        if (null == file) {
            System.err.println("Invalid database file name. Connect failed");
            return;
        }
        String url = "jdbc:sqlite:".concat(file);
        SQLiteConfig cfg = new SQLiteConfig();
        cfg.enforceForeignKeys(true);
        try {
            conn = DriverManager.getConnection(url, cfg.toProperties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String sql) {
        try {
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void batch(String... sqls) {
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (String sql : sqls)
            try {
                st.addBatch(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        try {
            st.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String sql) {
        try {
            conn.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(String sql) {
        try {
            return conn.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exists(String sql) {
        try {
            return query(sql).isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User findUser(String name) {
        final String sql = "select id, password, permission from user where name = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst())
                return null;
            return new User(rs.getInt("id"), name, rs.getString("password"), rs.getInt("permission"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void install() {
        batch("create table if not exists book ( id integer primary key, title text not null, owner integer default 1 )",
                "create table if not exists user ( id integer primary key, name text not null, password text not null, permission integer default 1 )",
                "create table if not exists borrow ( user_id integer, book_id integer, due text, primary key(user_id, book_id), foreign key(user_id) references user(id), foreign key(book_id) references book(id) )",
                "create table if not exists return ( user_id integer, book_id integer, ret text, primary key(user_id, book_id), foreign key(user_id) references user(id), foreign key(book_id) references book(id) )",
                "create view if not exists borrow_book as select user_id, book_id, book.title as title from borrow join book on borrow.book_id = book.id");
        if (!exists("select * from user where name = 'sa'"))
            update("insert into user ( name, password, permission ) values ( 'sa', '5C37E86672A3AE4CCFB1E9D3F5907DD62D856DFC', 6 )");
    }

    public static void login() {
        User rhs = findUser(user.getName());
        if (rhs != null)
            loggedin = rhs.getPassword().equals(user.getPassword());
        if (loggedin)
            user = rhs;
    }

    public static void main(String[] args) {
        // connect();
        // install();
        // setUser(new User("sa", encrypt("sa")));
        // login();
        // System.out.println(user);
    }
}
