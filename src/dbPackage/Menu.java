package dbPackage;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Admin admin = new Admin();
    private Member member = new Member();

    // 초기 메뉴: 로그인 또는 회원가입
    public void initMenu() {
        try {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loginMenu(); // 로그인 메뉴 호출
                    break;
                case 2:
                    member.registerMember(); // 회원가입 메서드 호출
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
                    initMenu(); // 초기 메뉴 재호출
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류 발생: " + e.getMessage());
        }
    }

    // 로그인 후 메뉴 제공
    public void loginMenu() {
        try {
            System.out.println("로그인 정보를 입력하세요.");
            String loginId = scanner.next();
            String password = scanner.next();

            if (member.login(loginId, password)) {
                // 회원 등급에 따라 다른 메뉴 제공
                if (member.getMemberGrade().equals("관리자")) {
                    adminMenu(); // 관리자 메뉴 호출
                } else {
                    userMenu(); // 일반 사용자 메뉴 호출
                }
            } else {
                System.out.println("로그인에 실패했습니다.");
                initMenu(); // 초기 메뉴 재호출
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류 발생: " + e.getMessage());
        }
    }

    // 관리자 메뉴
    public void adminMenu() {
        try {
            System.out.println("관리자 메뉴입니다.");
            System.out.println("1. 회원 관리");
            System.out.println("2. 도서 관리");
            System.out.println("3. 대여 연장 승인 관리");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    admin.manageUsers(); // 회원 관리
                    break;
                case 2:
                    admin.manageBooks(); // 도서 관리
                    break;
                case 3:
                    admin.approveExtension(); // 대여 연장 승인 관리
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
                    adminMenu(); // 관리자 메뉴 재호출
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류 발생: " + e.getMessage());
        }
    }

    // 일반 사용자 메뉴
    public void userMenu() {
        try {
            System.out.println("1. 도서 검색");
            System.out.println("2. 내 정보 조회");
            System.out.println("3. 도서 대여 및 반납");
            System.out.println("4. 대여 연장 요청");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    member.searchBook(); // 도서 검색
                    break;
                case 2:
                    member.viewMemberInfo(); // 내 정보 조회
                    break;
                case 3:
                    member.rentOrReturnBook(); // 도서 대여 및 반납
                    break;
                case 4:
                    member.extendRentalPeriod(); // 대여 연장 요청
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
                    userMenu(); // 잘못된 선택 시 다시 호출
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류 발생: " + e.getMessage());
        }
    }
}
