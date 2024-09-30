package dbPackage; // dbPackage라는 패키지에 속하는 클래스

import java.sql.*; // JDBC API를 사용하기 위한 패키지

// Book 클래스는 도서의 정보를 저장하고 관리하는 역할을 한다.
public class Book {
    // 도서의 속성 정의
    private int bookId; // 도서 ID
    private String title; // 도서 제목
    private String author; // 도서 저자
    private String publisher; // 도서 출판사
    private String publicationDate; // 도서 출판일
    private String category; // 도서 카테고리
    private int quantity; // 도서 수량
    private boolean isRented; // 대여 여부

    // 생성자: 도서 정보를 초기화하는 역할
    public Book(int bookId, String title, String author, String publisher, String publicationDate, String category, int quantity, boolean isRented) {
        this.bookId = bookId; // 도서 ID 초기화
        this.title = title; // 도서 제목 초기화
        this.author = author; // 도서 저자 초기화
        this.publisher = publisher; // 도서 출판사 초기화
        this.publicationDate = publicationDate; // 도서 출판일 초기화
        this.category = category; // 도서 카테고리 초기화
        this.quantity = quantity; // 도서 수량 초기화
        this.isRented = isRented; // 대여 여부 초기화
    }

    // Getter 메서드: 각 속성 값을 반환하는 메서드들
    public int getBookID() {
        return bookId; // 도서 ID 반환
    }

    public String getBookName() {
        return title; // 도서 제목 반환
    }

    public String getAuthor() {
        return author; // 도서 저자 반환
    }

    public String getPublisher() {
        return publisher; // 도서 출판사 반환
    }

    public String getPublicationDate() {
        return publicationDate; // 도서 출판일 반환
    }

    public String getCategory() {
        return category; // 도서 카테고리 반환
    }

    public int getQuantity() {
        return quantity; // 도서 수량 반환
    }

    public boolean isRented() {
        return isRented; // 대여 여부 반환
    }

    // 도서 제목을 기반으로 도서를 검색하는 메서드
    public static void searchBook(String title) throws SQLException {
        Connection conn = DBConnection.getConnection(); // 데이터베이스 연결
        String query = "SELECT * FROM books WHERE title LIKE ?"; // 도서 제목을 기반으로 검색하는 SQL 쿼리
        PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 객체 생성
        pstmt.setString(1, "%" + title + "%"); // 제목에 대한 LIKE 조건 설정
        
        ResultSet rs = pstmt.executeQuery(); // 쿼리 실행하여 결과 집합 가져오기

        // 결과 집합을 순회하며 도서 정보를 출력
        while (rs.next()) {
            int bookId = rs.getInt("bookId"); // 도서 ID 가져오기
            String author = rs.getString("author"); // 저자 가져오기
            String publisher = rs.getString("publisher"); // 출판사 가져오기
            String publicationDate = rs.getString("publicationDate"); // 출판일 가져오기
            String category = rs.getString("category"); // 카테고리 가져오기
            int quantity = rs.getInt("quantity"); // 수량 가져오기
            boolean isRented = rs.getBoolean("isRented"); // 대여 여부 가져오기

            // 도서 정보를 출력
            System.out.println("도서 ID: " + bookId + ", 제목: " + title + ", 저자: " + author + ", 출판사: " + publisher + ", 카테고리: " + category + ", 수량: " + quantity + ", 대여 여부: " + (isRented ? "대여 중" : "가능"));
        }

        // 자원 정리: ResultSet, PreparedStatement, Connection 닫기
        rs.close();
        pstmt.close();
        conn.close();
    }

    // 도서를 대여하는 메서드
    public static void rentBook(int bookId, int memberId) throws SQLException {
        Connection conn = DBConnection.getConnection(); // 데이터베이스 연결
        String query = "UPDATE books SET isRented = true WHERE bookId = ?"; // 도서를 대여하는 SQL 쿼리
        PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 객체 생성
        pstmt.setInt(1, bookId); // 대여할 도서 ID 설정
        
        // 쿼리 실행하여 도서 대여 상태 업데이트
        pstmt.executeUpdate();

        // Rental 객체 생성: 대여 기록을 위한 객체
        Rental newRental = new Rental(0, bookId, memberId, new java.util.Date().toString(), null);
        Rental.addRental(newRental); // 대여 기록 추가
        
        // 자원 정리: PreparedStatement, Connection 닫기
        pstmt.close();
        conn.close();
        System.out.println("도서 대여가 완료되었습니다."); // 대여 완료 메시지 출력
    }

    // 도서를 반납하는 메서드
    public static void returnBook(int bookId) throws SQLException {
        Connection conn = DBConnection.getConnection(); // 데이터베이스 연결
        String query = "UPDATE books SET isRented = false WHERE bookId = ?"; // 도서를 반납하는 SQL 쿼리
        PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 객체 생성
        pstmt.setInt(1, bookId); // 반납할 도서 ID 설정
        
        // 쿼리 실행하여 도서 대여 상태 업데이트
        pstmt.executeUpdate();
        
        // 자원 정리: PreparedStatement, Connection 닫기
        pstmt.close();
        conn.close();
        System.out.println("도서 반납이 완료되었습니다."); // 반납 완료 메시지 출력
    }

    // 도서를 등록하는 메서드
    public static void addBook(Book book) throws SQLException {
        Connection conn = DBConnection.getConnection(); // 데이터베이스 연결
        String query = "INSERT INTO books (title, author, publisher, publicationDate, category, quantity, isRented) VALUES (?, ?, ?, ?, ?, ?, ?)"; // 도서 등록 SQL 쿼리
        PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 객체 생성
        pstmt.setString(1, book.getBookName()); // 도서 제목 설정
        pstmt.setString(2, book.getAuthor()); // 저자 설정
        pstmt.setString(3, book.getPublisher()); // 출판사 설정
        pstmt.setString(4, book.getPublicationDate()); // 출판일 설정
        pstmt.setString(5, book.getCategory()); // 카테고리 설정
        pstmt.setInt(6, book.getQuantity()); // 수량 설정
        pstmt.setBoolean(7, book.isRented()); // 대여 여부 설정

        pstmt.executeUpdate(); // 쿼리 실행하여 도서 추가
        
        // 자원 정리: PreparedStatement, Connection 닫기
        pstmt.close();
        conn.close();
        System.out.println("도서 등록이 완료되었습니다."); // 등록 완료 메시지 출력
    }

    // 도서를 삭제하는 메서드
    public static void deleteBook(int bookId) throws SQLException {
        Connection conn = DBConnection.getConnection(); // 데이터베이스 연결
        String query = "DELETE FROM books WHERE bookId = ?"; // 도서 삭제 SQL 쿼리
        PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 객체 생성
        pstmt.setInt(1, bookId); // 삭제할 도서 ID 설정

        int affectedRows = pstmt.executeUpdate(); // 쿼리 실행하여 삭제
        if (affectedRows > 0) {
            System.out.println("도서 삭제가 완료되었습니다."); // 삭제 완료 메시지 출력
        } else {
            System.out.println("삭제할 도서가 존재하지 않습니다."); // 도서가 없을 경우 메시지 출력
        }

        // 자원 정리: PreparedStatement, Connection 닫기
        pstmt.close();
        conn.close();
    }
}
