package dbPackage;

import java.sql.*;
import java.util.Scanner;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private String category;
    private int quantity;

    public Book(int bookId, String title, String author, String publisher, String publicationDate, String category, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.category = category;
        this.quantity = quantity;
    }

    // Getter 메서드 추가
    public int getBookID() {
        return bookId;
    }

    public String getBookName() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public static void searchBook(String title) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM books WHERE title LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, "%" + title + "%");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int bookId = rs.getInt("bookId");
            String author = rs.getString("author");
            String publisher = rs.getString("publisher");
            String publicationDate = rs.getString("publicationDate");
            String category = rs.getString("category");
            int quantity = rs.getInt("quantity"); // 수량 추가
            boolean isRented = rs.getBoolean("isRented");

            System.out.println("도서 ID: " + bookId + ", 제목: " + title + ", 저자: " + author + ", 출판사: " + publisher + ", 카테고리: " + category + ", 수량: " + quantity + ", 대여 여부: " + (isRented ? "대여 중" : "가능"));
        }

        rs.close();
        pstmt.close();
        conn.close();
    }

    public static void rentBook(int bookId, int memberId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String query = "UPDATE books SET isRented = true WHERE bookId = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, bookId);
        pstmt.executeUpdate();

        Rental newRental = new Rental(0, bookId, memberId, new java.util.Date().toString(), null);
        Rental.addRental(newRental); // 대여 기록 추가
        pstmt.close();
        conn.close();
        System.out.println("도서 대여가 완료되었습니다.");
    }

    public static void returnBook(int bookId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String query = "UPDATE books SET isRented = false WHERE bookId = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, bookId);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        System.out.println("도서 반납이 완료되었습니다.");
    }
}
