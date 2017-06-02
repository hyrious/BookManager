package bkmgr;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.sqlite.SQLiteConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class DataManager {
    static class Encrypt {
        private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        private static final String ALGORITHM = "SHA1";

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
    }

    public static String encrypt(final String str) {
        return Encrypt.encrypt(str);
    }

    static class SQLHelper {
        private static Connection getConnection() {
            if (null == file) {
                System.err.println("Invalid database file name. Connect failed");
                return null;
            }
            String url = "jdbc:sqlite:".concat(file);
            SQLiteConfig cfg = new SQLiteConfig();
            cfg.enforceForeignKeys(true);
            try {
                return DriverManager.getConnection(url, cfg.toProperties());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        private static PreparedStatement paramsSet(PreparedStatement statement, Object... params) throws SQLException {
            int paramSize = params.length;
            for (int i = 1; i <= paramSize; i++) {
                Object param = params[i - 1];
                if (param instanceof Integer) statement.setInt(i, (Integer) param);
                else if (param instanceof String) statement.setString(i, (String) param);
                else if (param instanceof Date) statement.setString(i, ((Date) param).toString());
            }
            return statement;
        }

        public static int[] batch(String... sqls) {
            Connection conn = null;
            Statement statement = null;
            try {
                conn = getConnection();
                statement = conn.createStatement();
                for (String sql : sqls)
                    statement.addBatch(sql);
                return statement.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) statement.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public static int update(String sql, Object... params) {
            if (sql.length() - sql.replaceAll("\\?", "").length() != params.length) return -1;
            Connection conn = null;
            PreparedStatement statement = null;
            try {
                conn = getConnection();
                statement = conn.prepareStatement(sql);
                if (params.length > 0) statement = paramsSet(statement, params);
                return statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) statement.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }

        public static int insertInto(String sql, Object... params) {
            if (sql.length() - sql.replaceAll("\\?", "").length() != params.length) return -1;
            sql = "insert into ".concat(sql);
            Connection conn = null;
            PreparedStatement statement = null;
            try {
                conn = getConnection();
                statement = conn.prepareStatement(sql);
                if (params.length > 0) statement = paramsSet(statement, params);
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) statement.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        }

        public static int deleteFrom(String sql, Object... params) {
            return update("delete from ".concat(sql), params);
        }

        @FunctionalInterface public interface Listener {
            void callback(ResultSet resultSet);
        }

        /** Usage:
         * 
         * <pre>
         * SQLHelper.select("sql ?", r -> {}, "params");
         * </pre>
        */
        public static void select(String sql, Listener listener, Object... params) {
            if (sql.length() - sql.replaceAll("\\?", "").length() != params.length) return;
            if (listener == null) return;
            Connection conn = null;
            PreparedStatement statement = null;
            try {
                conn = getConnection();
                statement = conn.prepareStatement(sql);
                if (params.length > 0) statement = paramsSet(statement, params);
                ResultSet resultSet = statement.executeQuery();
                listener.callback(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) statement.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        private static boolean existsFlag = false;

        public static boolean exists(String sql, Object... params) {
            existsFlag = false;
            select(sql, r -> {
                try {
                    existsFlag = r.isBeforeFirst();
                } catch (SQLException e) {}
            }, params);
            return existsFlag;
        }
    }

    public static int[] batch(String... sqls) {
        return SQLHelper.batch(sqls);
    }

    public static int update(String sql, Object... params) {
        return SQLHelper.update(sql, params);
    }

    public static int insertInto(String sql, Object... params) {
        return SQLHelper.insertInto(sql, params);
    }

    public static int deleteFrom(String sql, Object... params) {
        return SQLHelper.deleteFrom(sql, params);
    }

    public static void select(String sql, SQLHelper.Listener listener, Object... params) {
        SQLHelper.select(sql, listener, params);
    }

    public static boolean exists(String sql, Object... params) {
        return SQLHelper.exists(sql, params);
    }

    public static String               file  = null;
    public static User                 user  = null;
    public static ObservableList<Book> books = FXCollections.observableArrayList();
    public static ObservableList<Book> users = FXCollections.observableArrayList();

    public static void init() {
        batch("create table if not exists book ( id integer primary key, title text not null, owner    integer default 1 )",
              "create table if not exists user ( id integer primary key, name  text not null, password text not null, permission integer default 1 )",
              "create table if not exists borrow ( user_id integer, book_id integer, due text, primary key(user_id, book_id), foreign key(user_id) references user(id), foreign key(book_id) references book(id) )",
              "create table if not exists return ( user_id integer, book_id integer, ret text, primary key(user_id, book_id), foreign key(user_id) references user(id), foreign key(book_id) references book(id) )",
              "create view if not exists view_borrow as select user_id, book_id, title, name, due from borrow join book on borrow.book_id = book.id join user on borrow.user_id = user.id",
              "create view if not exists view_return as select user_id, book_id, title, name, ret from return join book on return.book_id = book.id join user on return.user_id = user.id",
              "create view if not exists view_book as select book.id as book_id, user.id as user_id, title, name from book join user on book.owner = user.id");
        if (!exists("select * from user where name = 'sa'"))
            insertInto("user ( name, password, permission ) values ( ?, ?, ? )", "sa", encrypt("sa"), 6);
    }

    public static void init(String database) {
        file = database;
        init();
    }

    private static boolean loginFlag = false;

    public static boolean login(String name, String plain_password) {
        loginFlag = false;
        String password = encrypt(plain_password);
        select("select id, permission from user where name = ? and password = ?", r -> {
            try {
                if (r.next()) {
                    DataManager.user = new User(r.getInt("id"), name, password, r.getInt("permission"));
                    loginFlag = true;
                }
            } catch (SQLException e) {}
        }, name, password);
        return loginFlag;
    }

    public static boolean regist(String name, String plain_password) {
        if (exists("select * from user where name = ?", name)) return false;
        insertInto("user (name, password) values ( ?, ? )", name, encrypt(plain_password));
        return true;
    }

    public static void main(String[] args) {
        file = "library.db";
        init();
        // insertInto("book (title) values ( ? )", "LOVE");
        // insertInto("borrow (book_id, user_id, due) values ( ?, ?, ? )", 1, 1,
        // "2017-06-01");
        // select("select due from borrow", r -> {
        // try {
        // while (r.next())
        // System.out.println(r.getString("due"));
        // } catch (SQLException sqle) {}
        // });
        System.out.println(new Date().toString());
        /** Test
         * 
         * <pre>
         * update("create table if not exists test (id integer primary key, value text not null)");
         * select("select value from test", r -> {
         *     try {
         *         while (r.next()) {
         *             System.out.println(r.getString("value"));
         *         }
         *     } catch (SQLException sqle) {}
         * });
         * </pre>
        */
    }
}
