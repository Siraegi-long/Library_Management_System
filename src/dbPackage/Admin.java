package dbPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    private Connection conn;

    public Admin(Connection conn) {
        this.conn = conn;
    }

    public void manageUsers() {
        String sql = "SELECT * FROM UserTbl";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("ID"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void manageBooks() {
        String sql = "SELECT * FROM BookTbl";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Book Name: " + rs.getString("bookName"));
                System.out.println("Author: " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void approveExtension(int bookID) {
        // 대여 연장 요청 승인 코드
    }
}
