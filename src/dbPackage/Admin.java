package dbPackage;

import java.util.ArrayList;

public class Admin {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();

    // 전체 회원 관리
    public void manageUsers() {
        System.out.println("전체 회원 목록:");
        for (Member member : members) {
            member.viewMemberInfo();
        }
    }

    // 도서 목록 관리
    public void manageBooks() {
        System.out.println("전체 도서 목록:");
        for (Book book : books) {
            System.out.println("도서명: " + book.getBookName());
        }
    }

    // 대여 연장 승인
    public void approveExtension() {
        System.out.println("대여 연장 요청 승인/거부 처리 중...");
        // 연장 승인 로직 구현 필요
    }

    // 회원 등급 수정 (일반 -> 관리자, 퇴출 등)
    public void updateMemberLevel(Member member, String newGrade) {
        member.setMemberGrade(newGrade);
        System.out.println(member.getName() + " 회원의 등급이 " + newGrade + "(으)로 변경되었습니다.");
    }

    // 도서 추가
    public void addBook(Book book) {
        books.add(book);
        System.out.println("도서가 추가되었습니다: " + book.getBookName());
    }

    // 도서 삭제
    public void removeBook(int bookId) {
        books.removeIf(book -> book.getBookID() == bookId);
        System.out.println("도서가 삭제되었습니다: ID " + bookId);
    }
}
