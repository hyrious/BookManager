package bkmgr;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DataBase {
  public static Connection db = null;
  public static void use(String file, String name, String password) throws SQLException {
    boolean init_flag = !new File(file).isFile();
    db = DriverManager.getConnection("jdbc:sqlite:".concat(file));
    if (init_flag) System.out.println("TODO: init ".concat(file));
  }
  public static void update(String[] sqls) throws SQLException {
    Statement stmt = db.createStatement();
    for (String query : sqls)
      stmt.addBatch(query);
    stmt.executeBatch();
    stmt.close();
  }
  public static void update(String sqls) throws SQLException { update(sqls.split(";")); }
  public static ResultSet query(String sql) throws SQLException {
    return db.createStatement().executeQuery(sql);
  }
  public static void close() throws SQLException { if(db != null) db.close(); }
  public static void main(String[] args) {
    // use("library.db", "sa", "as");
  }
}
