package dbPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private ArrayList<Book> books; // 도서 목록

    public Admin() {
        books = new ArrayList<>(); // 도서 목록 초기화
        // 임시 데이터 추가
        books.add(new Book(1, "티니핑 대모험", "홍길동", "대도서관 출판사", "모험", 5));
        books.add(new Book(2, "티니핑 마법책", "이순신", "대도서관 출판사", "판타지", 2));
    }

    public void searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("검색할 도서명을 입력하세요: ");
        String searchQuery = scanner.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(searchQuery)) {
                System.out.println("도서 ID: " + book.getBookID());
                System.out.println("도서명: " + book.getBookName());
                System.out.println("저자: " + book.getAuthor());
                System.out.println("출판사: " + book.getPublisher());
                System.out.println("카테고리: " + book.getCategory());
                System.out.println("수량: " + book.getQuantity());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("검색한 도서를 찾을 수 없습니다.");
        }
    }

    // 관리자 기능 추가 메서드 (예시)
    public void manageUsers() {
        System.out.println("회원 관리 기능을 구현하세요."); // 관리자 회원 관리 기능
    }

    public void manageBooks() {
        System.out.println("도서 목록 관리 기능을 구현하세요."); // 관리자 도서 관리 기능
    }

    public void approveExtension() {
        System.out.println("대여 연장 요청 승인 기능을 구현하세요."); // 대여 연장 요청 승인 기능
    }
}
