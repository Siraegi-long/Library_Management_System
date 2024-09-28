package dbPackage;

import java.sql.*;
import java.util.Scanner;

public class Admin {
    private Connection conn;

    // 생성자에서 데이터베이스 연결 초기화
    public Admin() {
        try {
            conn = DBConnection.getConnection(); // DBConnection에서 연결 가져오기
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 오류: " + e.getMessage());
        }
    }

    public void manageUsers() {
        // 회원 관리 로직 (여기서는 간단한 출력만 예시로 작성)
        System.out.println("회원 관리 기능은 아직 구현되지 않았습니다.");
    }

    public void manageBooks() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== 도서 관리 ===");
            System.out.println("1. 도서 추가");
            System.out.println("2. 도서 수정");
            System.out.println("3. 도서 삭제");
            System.out.println("4. 도서 목록 보기");
            System.out.println("5. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    addBook(scanner); // 도서 추가
                    break;
                case 2:
                    updateBook(scanner); // 도서 수정
                    break;
                case 3:
                    deleteBook(scanner); // 도서 삭제
                    break;
                case 4:
                    listBooks(); // 도서 목록 보기
                    break;
                case 5:
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }

    // 도서 추가 메서드
    private void addBook(Scanner scanner) {
        System.out.print("도서 ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("저자: ");
        String author = scanner.nextLine();
        System.out.print("출판사: ");
        String publisher = scanner.nextLine();
        System.out.print("출판일: ");
        String publicationDate = scanner.nextLine();
        System.out.print("카테고리: ");
        String category = scanner.nextLine();
        System.out.print("수량: ");
        int quantity = scanner.nextInt();

        try {
            String query = "INSERT INTO books (bookId, title, author, publisher, publicationDate, category, quantity, isRented) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bookId);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setString(4, publisher);
            pstmt.setString(5, publicationDate);
            pstmt.setString(6, category);
            pstmt.setInt(7, quantity);
            pstmt.setBoolean(8, false); // 초기 대여 여부는 false

            pstmt.executeUpdate();
            System.out.println("도서가 추가되었습니다.");
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("도서 추가 중 오류 발생: " + e.getMessage());
        }
    }

    // 도서 수정 메서드
    private void updateBook(Scanner scanner) {
        System.out.print("수정할 도서 ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        try {
            String query = "SELECT * FROM books WHERE bookId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.print("새 제목 (" + rs.getString("title") + "): ");
                String title = scanner.nextLine();
                System.out.print("새 저자 (" + rs.getString("author") + "): ");
                String author = scanner.nextLine();
                System.out.print("새 출판사 (" + rs.getString("publisher") + "): ");
                String publisher = scanner.nextLine();
                System.out.print("새 출판일 (" + rs.getString("publicationDate") + "): ");
                String publicationDate = scanner.nextLine();
                System.out.print("새 카테고리 (" + rs.getString("category") + "): ");
                String category = scanner.nextLine();
                System.out.print("새 수량 (" + rs.getInt("quantity") + "): ");
                int quantity = scanner.nextInt();

                String updateQuery = "UPDATE books SET title = ?, author = ?, publisher = ?, publicationDate = ?, category = ?, quantity = ? WHERE bookId = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setString(1, title.isEmpty() ? rs.getString("title") : title);
                updateStmt.setString(2, author.isEmpty() ? rs.getString("author") : author);
                updateStmt.setString(3, publisher.isEmpty() ? rs.getString("publisher") : publisher);
                updateStmt.setString(4, publicationDate.isEmpty() ? rs.getString("publicationDate") : publicationDate);
                updateStmt.setString(5, category.isEmpty() ? rs.getString("category") : category);
                updateStmt.setInt(6, quantity);
                updateStmt.setInt(7, bookId);

                updateStmt.executeUpdate();
                System.out.println("도서 정보가 수정되었습니다.");
                updateStmt.close();
            } else {
                System.out.println("해당 ID의 도서를 찾을 수 없습니다.");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("도서 수정 중 오류 발생: " + e.getMessage());
        }
    }

    // 도서 삭제 메서드
    private void deleteBook(Scanner scanner) {
        System.out.print("삭제할 도서 ID: ");
        int bookId = scanner.nextInt();

        try {
            String query = "DELETE FROM books WHERE bookId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bookId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("도서가 삭제되었습니다.");
            } else {
                System.out.println("해당 ID의 도서를 찾을 수 없습니다.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("도서 삭제 중 오류 발생: " + e.getMessage());
        }
    }

    // 도서 목록 보기 메서드
    private void listBooks() {
        try {
            String query = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("=== 도서 목록 ===");
            while (rs.next()) {
                System.out.println("도서 ID: " + rs.getInt("bookId") +
                        ", 제목: " + rs.getString("title") +
                        ", 저자: " + rs.getString("author") +
                        ", 출판사: " + rs.getString("publisher") +
                        ", 출판일: " + rs.getString("publicationDate") +
                        ", 카테고리: " + rs.getString("category") +
                        ", 수량: " + rs.getInt("quantity") +
                        ", 대여 여부: " + (rs.getBoolean("isRented") ? "대여 중" : "가능"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("도서 목록 조회 중 오류 발생: " + e.getMessage());
        }
    }
}
