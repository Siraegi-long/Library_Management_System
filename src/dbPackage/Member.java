package dbPackage;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Member {
    private String name;
    private String memberID;
    private String memberGrade = "일반"; // 기본 회원 등급
    private String phone;
    private String loginId;
    private String password;
    private int overdueCount = 0; // 연체 횟수
    private int currentRentalCount = 0; // 대여 중인 도서 수

    private Scanner scanner = new Scanner(System.in);

    // Getter 및 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(int overdueCount) {
        this.overdueCount = overdueCount;
    }

    public int getCurrentRentalCount() {
        return currentRentalCount;
    }

    public void setCurrentRentalCount(int currentRentalCount) {
        this.currentRentalCount = currentRentalCount;
    }

    // 회원가입 기능 (앞서 구현된 기능 참고)
    public void registerMember() {
        // 회원가입 로직
    }

    // 회원 정보 조회
    public void viewMemberInfo() {
        System.out.println("회원 정보:");
        System.out.println("이름: " + name);
        System.out.println("회원 ID: " + memberID);
        System.out.println("전화번호: " + phone);
        System.out.println("회원 등급: " + memberGrade);
        System.out.println("연체 횟수: " + overdueCount);
        System.out.println("현재 대여 중인 도서 수: " + currentRentalCount);
    }

    // 도서 대여 및 반납
    public void rentOrReturnBook() {
        System.out.println("도서 대여 및 반납");
        // 도서 대여 및 반납 로직 구현 필요
    }

    // 대여 연장 요청
    public void extendRentalPeriod() {
        System.out.println("대여 연장 요청 중...");
        // 대여 연장 로직 구현 필요
    }

    // 회원 등급 조회
    public String getMemberGrade() {
        return memberGrade;
    }

    // ID 유효성 검사, 비밀번호 유효성 검사, 전화번호 유효성 검사 등
    // 앞서 구현된 메서드 유지
}
