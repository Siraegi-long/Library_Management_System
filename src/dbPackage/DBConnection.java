package dbPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/yourDatabase"; // 데이터베이스 URL
    private static final String USER = "yourUsername"; // 사용자 이름
    private static final String PASSWORD = "yourPassword"; // 비밀번호

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
