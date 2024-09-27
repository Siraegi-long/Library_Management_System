package dbPackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
    private Connection conn;

    public Book(Connection conn) {
        this.conn = conn;
    }

    public void searchBook(String keyword) {
        String sql = "SELECT * FROM BookTbl WHERE bookName LIKE ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Book Name: " + rs.getString("bookName"));
                System.out.println("Author: " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rentBook(int bookID, String userID) {
        String sql = "UPDATE BookTbl SET rentalDate = ? WHERE bookID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, new Date(System.currentTimeMillis()));
            pstmt.setInt(2, bookID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int bookID) {
        String sql = "UPDATE BookTbl SET rentalDate = NULL WHERE bookID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void extendRentalPeriod(int bookID) {
        // 대여 연장 요청 관련 코드
        // 관리자의 승인이 필요함
    }
}
