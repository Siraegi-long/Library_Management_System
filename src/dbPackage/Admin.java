package dbPackage;

import java.sql.*; // JDBC API 사용을 위한 패키지
import java.util.Scanner; // 사용자 입력을 받기 위한 패키지

public class Admin {
    private Connection conn; // 데이터베이스 연결을 위한 Connection 객체

    // 생성자에서 데이터베이스 연결 초기화
    public Admin() {
        try {
            // DBConnection 클래스에서 데이터베이스 연결을 가져옴
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            // 연결 실패 시 오류 메시지 출력
            System.out.println("데이터베이스 연결 오류: " +    e.getMessage());
        }
    }

 // 회원 관리 기능을 실행하는 메서드
    public void manageUsers(Connection conn) {
        // Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);
        
        // 무한 루프로 회원 관리 메뉴 제공
        while (true) {
            // 회원 관리 메뉴 출력
            System.out.println("=== 회원 관리 ===");
            System.out.println("1. 전체 회원 목록 보기");
            System.out.println("2. 회원 등급 수정");
            System.out.println("3. 뒤로 가기");
            System.out.print("선택: ");

            // 사용자 선택 입력 받기
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            // 메뉴에 따라 기능 실행
            switch (choice) {
                case 1:
                    // 전체 회원 목록 보기
                    listAllUsers();
                    break;
                case 2:
                    // 회원 등급 수정
                    updateMemberLevel(scanner);
                    break;
                case 3:
                    // 뒤로 가기
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }

    // 전체 회원 목록 출력 메서드
    private void listAllUsers() {
        try {
            // 회원 정보를 가져오는 SQL 쿼리
            String query = "SELECT * FROM usertbl";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // 회원 목록 출력
            System.out.println("=== 전체 회원 목록 ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("memberID") + ", 이름: " + rs.getString("name") 
                + ", 등급: " + rs.getString("memberGrade"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("회원 목록 조회 중 오류 발생: " + e.getMessage());
        }
    }

    // 회원 등급 수정 메서드
    private void updateMemberLevel(Scanner scanner) {
        // 회원 ID와 수정할 등급 입력 받기
        System.out.print("수정할 회원 ID: ");
        String memberID = scanner.nextLine();
        System.out.print("새 등급 (일반, 관리자, 퇴출): ");
        String newGrade = scanner.nextLine();

        try {
            // 등급을 수정하는 SQL 쿼리
            String query = "UPDATE usertbl SET memberGrade = ? WHERE memberID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newGrade);
            pstmt.setString(2, memberID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("회원 등급이 수정되었습니다.");
            } else {
                System.out.println("해당 ID의 회원을 찾을 수 없습니다.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("회원 등급 수정 중 오류 발생: " + e.getMessage());
        }
    }

    // 도서 관리 기능을 실행하는 메서드
    public void manageBooks(Connection conn) {
        // Scanner 객체를 통해 사용자 입력을 받음
        Scanner scanner = new Scanner(System.in);

        // 도서 관리 기능 선택을 위한 무한 루프
        while (true) {
            // 도서 관리 메뉴 출력
            System.out.println("=== 도서 관리 ===");
            System.out.println("1. 도서 추가");
            System.out.println("2. 도서 수정");
            System.out.println("3. 도서 삭제");
            System.out.println("4. 도서 목록 보기");
            System.out.println("5. 뒤로 가기");
            System.out.print("선택: ");

            // 사용자 선택 입력 받기
            int choice = scanner.nextInt();
            scanner.nextLine(); // 입력 후 남은 버퍼 비우기

            // 사용자가 선택한 메뉴에 따라 기능 실행
            switch (choice) {
                case 1:
                    // 도서 추가 메서드 호출
                    addBook(scanner);
                    break;
                case 2:
                    // 도서 수정 메서드 호출
                    updateBook(scanner);
                    break;
                case 3:
                    // 도서 삭제 메서드 호출
                    deleteBook(scanner);
                    break;
                case 4:
                    // 도서 목록 보기 메서드 호출
                    listBooks();
                    break;
                case 5:
                    // 메뉴 종료, 이전 메뉴로 돌아감
                    return;
                default:
                    // 잘못된 선택 시 오류 메시지 출력
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }

    // 대여 연장 요청 승인 메서드
    public void approveExtension(Connection conn) {
        // 대여 연장 승인 로직은 아직 구현되지 않았음을 알리는 메시지 출력
        System.out.println("대여 연장 승인 기능은 아직 구현되지 않았습니다.");   
    }

    // 도서 추가 메서드
    private void addBook(Scanner scanner) {
        System.out.print("도서 ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("제목: ");
        String bookName = scanner.nextLine();
        System.out.print("저자: ");
        String author = scanner.nextLine();
        System.out.print("출판사: ");
        String publisher = scanner.nextLine();
        System.out.print("카테고리: ");
        String category = scanner.nextLine();
        System.out.print("수량: ");
        int quantity = scanner.nextInt();

        try {
            String query = "INSERT INTO booktbl (bookId, bookName, author, publisher, category, quantity) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, bookId);
            pstmt.setString(2, bookName);
            pstmt.setString(3, author);
            pstmt.setString(4, publisher);
            pstmt.setString(5, category);
            pstmt.setInt(6, quantity);

            pstmt.executeUpdate();
            System.out.println("도서가 추가되었습니다.");

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("도서 추가 중 오류 발생: " + e.getMessage());
        }
    }


    // 도서 수정 메서드
    private void updateBook(Scanner scanner) {
        // 수정할 도서 ID 입력 받음
        System.out.print("수정할 도서 ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        try {
            // 도서 ID로 기존 도서 정보를 조회하는 SQL 쿼리
            String query = "SELECT * FROM booktbl WHERE bookId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 객체 생성
            pstmt.setInt(1, bookId); // 도서 ID 설정

            // 쿼리 실행하여 결과 집합을 가져옴
            ResultSet rs = pstmt.executeQuery();

            // 결과 집합에 데이터가 있는 경우
            if (rs.next()) {
                // 기존 도서 정보를 기반으로 새로운 정보 입력받기
                System.out.print("새 제목 (" + rs.getString("bookName") + "): ");
                String bookName = scanner.nextLine();
                System.out.print("새 저자 (" + rs.getString("author") + "): ");
                String author = scanner.nextLine();
                System.out.print("새 출판사 (" + rs.getString("publisher") + "): ");
                String publisher = scanner.nextLine();
                System.out.print("새 카테고리 (" + rs.getString("category") + "): ");
                String category = scanner.nextLine();
                System.out.print("새 수량 (" + rs.getInt("quantity") + "): ");
                int quantity = scanner.nextInt();

                // 도서 정보를 업데이트하는 SQL 쿼리
                String updateQuery = "UPDATE booktbl SET bookName = ?, author = ?, publisher = ?, category = ?, quantity = ? WHERE bookId = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery); // PreparedStatement 객체 생성

                // 쿼리에 값 설정, 빈 문자열인 경우 기존 값 유지
                updateStmt.setString(1, bookName.isEmpty() ? rs.getString("bookName") : bookName);
                updateStmt.setString(2, author.isEmpty() ? rs.getString("author") : author);
                updateStmt.setString(3, publisher.isEmpty() ? rs.getString("publisher") : publisher);
                updateStmt.setString(4, category.isEmpty() ? rs.getString("category") : category);
                updateStmt.setInt(5, quantity);
                updateStmt.setInt(6, bookId); // 수정할 도서 ID 설정

                // 쿼리 실행
                updateStmt.executeUpdate();
                System.out.println("도서 정보가 수정되었습니다."); // 수정 완료 메시지 출력

                updateStmt.close(); // PreparedStatement 닫기
            } else {
                // 해당 ID의 도서를 찾을 수 없을 때 메시지 출력
                System.out.println("해당 ID의 도서를 찾을 수 없습니다.");
            }

            rs.close(); // ResultSet 닫기
            pstmt.close(); // PreparedStatement 닫기
        } catch (SQLException e) {
            // 도서 수정 중 오류 발생 시 메시지 출력
            System.out.println("도서 수정 중 오류 발생: " + e.getMessage());
        }
    }

    // 도서 삭제 메서드
    private void deleteBook(Scanner scanner) {
        // 삭제할 도서 ID 입력 받음
        System.out.print("삭제할 도서 ID: ");
        int bookId = scanner.nextInt();

        try {
            // 도서를 삭제하는 SQL 쿼리
            String query = "DELETE FROM booktbl WHERE bookId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 객체 생성
            pstmt.setInt(1, bookId); // 도서 ID 설정

            // 쿼리 실행하여 영향을 받은 행 수 확인
            int rowsAffected = pstmt.executeUpdate();

            // 삭제 성공 여부 확인
            if (rowsAffected > 0) {
                System.out.println("도서가 삭제되었습니다."); // 삭제 완료 메시지 출력
            } else {
                System.out.println("해당 ID의 도서를 찾을 수 없습니다."); // 도서 미발견 메시지 출력
            }

            pstmt.close(); // PreparedStatement 닫기
        } catch (SQLException e) {
            // 도서 삭제 중 오류 발생 시 메시지 출력
            System.out.println("도서 삭제 중 오류 발생: " + e.getMessage());
        }
    }

    // 도서 목록 출력 메서드
    private void listBooks() {
        try {
            // 도서 목록을 조회하는 SQL 쿼리
            String query = "SELECT * FROM booktbl";
            Statement stmt = conn.createStatement(); // Statement 객체 생성

            // 쿼리 실행하여 결과 집합 가져옴
            ResultSet rs = stmt.executeQuery(query);

            // 도서 목록 출력
            System.out.println("=== 도서 목록 ===");
            while (rs.next()) {
                // 각 도서의 정보를 출력
                System.out.println("ID: " + rs.getInt("bookId") + ", 제목: " + rs.getString("bookName") + ", 저자: " + rs.getString("author")
                        + ", 출판사: " + rs.getString("publisher") + ", 카테고리: "
                        + rs.getString("category") + ", 수량: " + rs.getInt("quantity") + ", 대여 중: ");
            }

            rs.close(); // ResultSet 닫기
            stmt.close(); // Statement 닫기
        } catch (SQLException e) {
            // 도서 목록 조회 중 오류 발생 시 메시지 출력
            System.out.println("도서 목록 조회 중 오류 발생: " + e.getMessage());
        }
    }
}
