package dbPackage;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Admin admin = new Admin();
    private Member member = new Member();

    // 초기 메뉴: 로그인 또는 회원가입
    public void initMenu() {
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                loginMenu();
                break;
            case 2:
                member.registerMember();
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                initMenu();
        }
    }

    // 로그인 후 메뉴 제공
    public void loginMenu() {
        System.out.println("로그인 정보를 입력하세요.");
        String loginId = scanner.next();
        String password = scanner.next();

        if (member.login(loginId, password)) {
            if (member.getMemberGrade().equals("관리자")) {
                adminMenu();
            } else {
                userMenu();
            }
        } else {
            System.out.println("로그인에 실패했습니다.");
            initMenu();
        }
    }

    // 관리자 메뉴
    public void adminMenu() {
        System.out.println("관리자 메뉴입니다.");
        System.out.println("1. 회원 관리");
        System.out.println("2. 도서 관리");
        System.out.println("3. 대여 연장 승인 관리");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                admin.manageUsers();
                break;
            case 2:
                admin.manageBooks();
                break;
            case 3:
                admin.approveExtension();
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                adminMenu();
        }
    }

    // 일반 사용자 메뉴
    public void userMenu() {
        System.out.println("1. 도서 검색");
        System.out.println("2. 내 정보 조회");
        System.out.println("3. 도서 대여 및 반납");
        System.out.println("4. 대여 연장 요청");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                member.searchBook();
                break;
            case 2:
                member.viewMemberInfo();
                break;
            case 3:
                member.rentOrReturnBook();
                break;
            case 4:
                member.extendRentalPeriod();
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                userMenu();
        }
    }
}
