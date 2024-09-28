package dbPackage;

import java.sql.*;

public class Rental {
    private int rentalId;
    private int bookId;
    private int memberId;
    private String rentalDate;
    private String returnDate;

    public Rental(int rentalId, int bookId, int memberId, String rentalDate, String returnDate) {
        this.rentalId = rentalId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public static void addRental(Rental rental) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String query = "INSERT INTO rentals (bookId, memberId, rentalDate, returnDate) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, rental.bookId);
        pstmt.setInt(2, rental.memberId);
        pstmt.setString(3, rental.rentalDate);
        pstmt.setString(4, rental.returnDate);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
