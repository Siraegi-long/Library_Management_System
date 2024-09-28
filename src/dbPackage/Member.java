package dbPackage;

import java.sql.*;
import java.util.Scanner;

public class Member {
    private String memberId;
    private String name;
    private String password;
    private String memberGrade;

    public Member(String memberId, String name, String password, String memberGrade) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.memberGrade = memberGrade;
    }

    public void registerMember() {
        // 회원가입 로직 (DB에 회원 정보 추가)
    }

    public void searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("검색할 도서 제목을 입력하세요: ");
        String title = scanner.nextLine();

        try {
            Book.searchBook(title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewMemberInfo() {
        System.out.println("회원 ID: " + this.memberId);
        System.out.println("회원 이름: " + this.name);
        System.out.println("회원 등급: " + this.memberGrade);
        // 추가 정보 표시
    }

    public void extendRentalPeriod() {
        System.out.println("대여 연장 요청이 완료되었습니다."); // 실제 로직은 추가 필요
    }

    public static Member login(String inputId, String inputPw) {
        // 로그인 로직 (DB에서 사용자 정보 확인)
        return new Member("1234", "홍길동", "password", "일반"); // 임시 리턴
    }

    // Getter 메서드
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getMemberGrade() {
        return memberGrade;
    }
}
