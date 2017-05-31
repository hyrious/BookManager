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
                if (param instanceof java.lang.Integer) {
                    statement.setInt(i, (Integer) param);
                } else if (param instanceof java.lang.Boolean) {
                    statement.setInt(i, (Integer) param);
                } else if (param instanceof java.lang.String) {
                    statement.setString(i, (String) param);
                }
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

        public interface Listener {
            default void callback(ResultSet resultSet) {}
        }

        /** Usage:
         * 
         * <pre>
         * SQLHelper.select("", new SQLHelper.Listener() {
         *     public void callback(ResultSet resultSet) {
         *         try {
         *             while (resultSet.next()) {
         *                 System.out.println(resultSet.getInt(1));
         *                 System.out.println(resultSet.getString(5));
         *             }
         *         } catch (SQLException e) {
         *             e.printStackTrace();
         *         }
         *     }
         * });
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
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
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
            select(sql, new SQLHelper.Listener() {
                public void callback(ResultSet resultSet) {
                    try {
                        existsFlag = resultSet.isBeforeFirst();
                    } catch (SQLException e) {}
                }
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

    public static String               file      = null;
    public static User                 user      = null;
    public static ObservableList<User> all_users = FXCollections.observableArrayList();
    public static ObservableList<Book> all_books = FXCollections.observableArrayList();
}
