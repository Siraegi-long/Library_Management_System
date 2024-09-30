package dbPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DBConnection 클래스는 데이터베이스 연결을 관리하는 역할을 한다.
public class DBConnection {
    // 데이터베이스 연결에 필요한 상수 정의
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb"; // 데이터베이스 URL 설정 (데이터베이스의 주소와 포트 번호)
    private static final String USER = "root"; // 데이터베이스 사용자 이름 설정
    private static final String PASSWORD = "71028776"; // 데이터베이스 비밀번호 설정

    // 데이터베이스에 연결을 반환하는 메서드
    public static Connection getConnection() throws SQLException {
        // DriverManager를 사용하여 데이터베이스에 연결을 생성하고 반환
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
