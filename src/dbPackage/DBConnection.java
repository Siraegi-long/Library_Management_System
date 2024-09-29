package dbPackage; // dbPackage라는 패키지에 속하는 클래스

import java.sql.Connection; // JDBC의 Connection 인터페이스를 사용하기 위한 패키지
import java.sql.DriverManager; // JDBC의 DriverManager 클래스를 사용하기 위한 패키지
import java.sql.SQLException; // SQL 예외 처리를 위한 패키지

// DBConnection 클래스는 데이터베이스 연결을 관리하는 역할을 한다.
public class DBConnection {
    // 데이터베이스 연결에 필요한 상수 정의
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb"; // 데이터베이스 URL 설정 (데이터베이스의 주소와 포트 번호)
    private static final String USER = "root"; // 데이터베이스 사용자 이름 설정
    private static final String PASSWORD = "0000"; // 데이터베이스 비밀번호 설정

    // 데이터베이스에 연결을 반환하는 메서드
    public static Connection getConnection() throws SQLException {
        // DriverManager를 사용하여 데이터베이스에 연결을 생성하고 반환
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
