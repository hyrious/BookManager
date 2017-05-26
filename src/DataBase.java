package bkmgr;

import org.sqlite.SQLiteConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public final class DataBase {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static final String ALGORITHM = "SHA1";
    public static Connection db = null;
    public static Integer user_id = null;
    public static String user_name = null;
    public static Integer permission = null;

    private static final void connect(String file) throws SQLException {
        String url = "jdbc:sqlite:".concat(file);
        SQLiteConfig cfg = new SQLiteConfig();
        cfg.enforceForeignKeys(true);
        db = DriverManager.getConnection(url, cfg.toProperties());
    }

    private static final void install() throws SQLException {
        batch(new String[]{
                "create table if not exists book ( id integer primary key, title text not null, available integer default 1 )",
                "create table if not exists user ( id integer primary key, name text not null, password text not null, permission integer default 1 )",
                "create table if not exists borrow ( user_id integer, book_id integer, due text, primary key(user_id, book_id), foreign key(user_id) references user(id), foreign key(book_id) references book(id) )",
                "create table if not exists return ( user_id integer, book_id integer, ret text, primary key(user_id, book_id), foreign key(user_id) references user(id), foreign key(book_id) references book(id) )"
        });
        if (!exists("select * from user where name = 'sa'"))
            update("insert into user ( name, password, permission ) values ( 'sa', '5C37E86672A3AE4CCFB1E9D3F5907DD62D856DFC', 6 )");
    }

    private static final boolean login(String name, String password) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("select id, password, permission from user where name = ? and password = ?");
        stmt.setString(1, name);
        stmt.setString(2, encrypt(password));
        ResultSet set = stmt.executeQuery();
        if (!set.isBeforeFirst()) // not found user or faulty password
            return false;
        user_id = set.getInt("id");
        user_name = name;
        permission = set.getInt("permission");
        // Debug
        System.out.println("Login by" + " (" + user_id + ") " + name + " with permission " + permission + ".");
        return true;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String sha1(String str) {
        if (str == null) return null;
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

    public static boolean use(String f, String n, String p) {
        try {
            connect(f);
            install();
            return login(n, p);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void batch(String[] sqls) throws SQLException {
        Statement stmt = db.createStatement();
        for (String query : sqls)
            stmt.addBatch(query);
        stmt.executeBatch();
        stmt.close();
    }

    public static boolean execute(String sql) throws SQLException {
        return db.createStatement().execute(sql);
    }

    public static int update(String sql) throws SQLException {
        return db.createStatement().executeUpdate(sql);
    }

    public static PreparedStatement prepare(String sql) throws SQLException {
        return db.prepareStatement(sql);
    }

    public static ResultSet query(String sql) throws SQLException {
        return db.createStatement().executeQuery(sql);
    }

    public static boolean exists(String sql) throws SQLException {
        return query(sql).isBeforeFirst();
    }

    public static void close() throws SQLException {
        if (db != null) db.close();
    }

    public static void main(String[] args) {
        // DataBase.use("library.db", "sa", "sa"); // false when login failed
        // PreparedStatement st = DataBase.prepare("insert into user (name, password, permission) values ( ?, ?, ? )")
        // DataBase.close();
    }
}
