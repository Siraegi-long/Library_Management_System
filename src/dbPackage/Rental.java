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
    public static void addRental(Rental rental) {
        String query = "INSERT INTO rentals (bookId, memberId, rentalDate, returnDate) VALUES (?, ?, ?, ?)"; // 대여 정보를 삽입하기 위한 SQL 쿼리
        try (Connection conn = DBConnection.getConnection(); // try-with-resources로 연결 관리
             PreparedStatement pstmt = conn.prepareStatement(query)) { // PreparedStatement 객체 생성
             
            pstmt.setInt(1, rental.bookId); // 도서 ID 설정
            pstmt.setInt(2, rental.memberId); // 회원 ID 설정
            pstmt.setString(3, rental.rentalDate); // 대여 날짜 설정
            pstmt.setString(4, rental.returnDate); // 반납 날짜 설정
            pstmt.executeUpdate(); // SQL 쿼리 실행하여 대여 정보 추가
            System.out.println("대여 정보가 추가되었습니다."); // 추가 성공 메시지 출력
        } catch (SQLException e) {
            System.out.println("대여 정보 추가 중 오류 발생: " + e.getMessage()); // 오류 메시지 출력
        }
    }

    // 대여 기록 조회 메서드
    public static void viewRentals() {
        String query = "SELECT * FROM rentals"; // 모든 대여 기록을 조회하기 위한 SQL 쿼리
        try (Connection conn = DBConnection.getConnection(); // try-with-resources로 연결 관리
             Statement stmt = conn.createStatement(); // Statement 객체 생성
             ResultSet rs = stmt.executeQuery(query)) { // SQL 쿼리 실행하여 결과 집합 가져오기

            // 결과 집합을 순회하며 대여 정보를 출력
            while (rs.next()) {
                int rentalId = rs.getInt("rentalId"); // 대여 ID 가져오기
                int bookId = rs.getInt("bookId"); // 도서 ID 가져오기
                int memberId = rs.getInt("memberId"); // 회원 ID 가져오기
                String rentalDate = rs.getString("rentalDate"); // 대여 날짜 가져오기
                String returnDate = rs.getString("returnDate"); // 반납 날짜 가져오기

                // 대여 정보를 출력
                System.out.println("대여 ID: " + rentalId + ", 도서 ID: " + bookId + ", 회원 ID: " + memberId + ", 대여 날짜: " + rentalDate + ", 반납 날짜: " + returnDate);
            }
        } catch (SQLException e) {
            System.out.println("대여 기록 조회 중 오류 발생: " + e.getMessage()); // 오류 메시지 출력
        }
    }

    // 대여 기록 반납 처리 메서드
    public static void returnRental(int rentalId) {
        String query = "UPDATE rentals SET returnDate = ? WHERE rentalId = ?"; // 반납 날짜를 업데이트하는 SQL 쿼리
        try (Connection conn = DBConnection.getConnection(); // try-with-resources로 연결 관리
             PreparedStatement pstmt = conn.prepareStatement(query)) { // PreparedStatement 객체 생성

            pstmt.setString(1, new java.sql.Date(System.currentTimeMillis()).toString()); // 현재 날짜를 반납 날짜로 설정
            pstmt.setInt(2, rentalId); // 대여 ID 설정
            
            int affectedRows = pstmt.executeUpdate(); // SQL 쿼리 실행하여 업데이트
            if (affectedRows > 0) {
                System.out.println("반납이 완료되었습니다."); // 반납 완료 메시지 출력
            } else {
                System.out.println("해당 대여 ID가 존재하지 않습니다."); // 대여 ID가 없을 경우 메시지 출력
            }
        } catch (SQLException e) {
            System.out.println("반납 처리 중 오류 발생: " + e.getMessage()); // 오류 메시지 출력
        }
    }

    // 대여 기록 삭제 메서드
    public static void deleteRental(int rentalId) {
        String query = "DELETE FROM rentals WHERE rentalId = ?"; // 대여 기록 삭제 SQL 쿼리
        try (Connection conn = DBConnection.getConnection(); // try-with-resources로 연결 관리
             PreparedStatement pstmt = conn.prepareStatement(query)) { // PreparedStatement 객체 생성

            pstmt.setInt(1, rentalId); // 삭제할 대여 ID 설정
            
            int affectedRows = pstmt.executeUpdate(); // SQL 쿼리 실행하여 삭제
            if (affectedRows > 0) {
                System.out.println("대여 기록이 삭제되었습니다."); // 삭제 완료 메시지 출력
            } else {
                System.out.println("해당 대여 ID가 존재하지 않습니다."); // 대여 ID가 없을 경우 메시지 출력
            }
        } catch (SQLException e) {
            System.out.println("대여 기록 삭제 중 오류 발생: " + e.getMessage()); // 오류 메시지 출력
        }
    }
}
