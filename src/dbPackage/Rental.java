package dbPackage; // dbPackage라는 패키지에 속하는 클래스

import java.sql.*; // SQL 관련 클래스 임포트

// Rental 클래스는 도서 대여 정보를 관리하는 기능을 제공한다.
public class Rental {
    private int rentalId; // 대여 ID
    private int bookId; // 대여된 도서의 ID
    private int memberId; // 대여한 회원의 ID
    private String rentalDate; // 도서 대여 날짜
    private String returnDate; // 도서 반납 날짜

    // 생성자: 대여 정보를 초기화
    public Rental(int rentalId, int bookId, int memberId, String rentalDate, String returnDate) {
        this.rentalId = rentalId; // 대여 ID 설정
        this.bookId = bookId; // 도서 ID 설정
        this.memberId = memberId; // 회원 ID 설정
        this.rentalDate = rentalDate; // 대여 날짜 설정
        this.returnDate = returnDate; // 반납 날짜 설정
    }

    // 대여 기록 추가 메서드
    public static void addRental(Rental rental) throws SQLException {
        // 데이터베이스 연결
        Connection conn = DBConnection.getConnection(); // DBConnection에서 연결 가져오기
        // 대여 정보를 삽입하기 위한 SQL 쿼리
        String query = "INSERT INTO rentals (bookId, memberId, rentalDate, returnDate) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 객체 생성
        pstmt.setInt(1, rental.bookId); // 도서 ID 설정
        pstmt.setInt(2, rental.memberId); // 회원 ID 설정
        pstmt.setString(3, rental.rentalDate); // 대여 날짜 설정
        pstmt.setString(4, rental.returnDate); // 반납 날짜 설정
        pstmt.executeUpdate(); // SQL 쿼리 실행하여 대여 정보 추가
        pstmt.close(); // PreparedStatement 객체 닫기
        conn.close(); // 데이터베이스 연결 닫기
    }
}
