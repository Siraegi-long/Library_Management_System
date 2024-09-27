package dbPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cookdb?serverTimeZone=UTC";
    private String id = "root";
    private String pw = "0000";

    private Connection conn = null;

    public void initDBConnect() {
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url, this.id, this.pw);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.conn;
    }
}
