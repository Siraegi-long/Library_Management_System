package dbPackage; // dbPackage라는 패키지에 속하는 클래스

import java.util.Scanner; // 사용자 입력을 받기 위한 Scanner 클래스

// Menu 클래스는 애플리케이션의 메뉴를 관리하고 사용자와의 상호작용을 처리한다.
public class Menu {
    private Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성
    private Admin admin = new Admin(); // 관리자를 위한 Admin 클래스 인스턴스 생성
    private Member member = new Member(); // 사용자를 위한 Member 클래스 인스턴스 생성

    // 초기 메뉴: 로그인 또는 회원가입
    public void initMenu() {
        System.out.println("1. 로그인"); // 로그인 옵션 출력
        System.out.println("2. 회원가입"); // 회원가입 옵션 출력
        int choice = scanner.nextInt(); // 사용자 입력 선택

        switch (choice) {
            case 1:
                loginMenu(); // 로그인 메뉴 호출
                break;
            case 2:
                member.registerMember(); // 회원가입 메서드 호출
                break;
            default:
                System.out.println("잘못된 선택입니다."); // 잘못된 입력 처리
                initMenu(); // 초기 메뉴 재호출
        }
    }

    // 로그인 후 메뉴 제공
    public void loginMenu() {
        System.out.println("로그인 정보를 입력하세요."); // 로그인 정보 요청
        String loginId = scanner.next(); // 로그인 ID 입력 받기
        String password = scanner.next(); // 비밀번호 입력 받기

        // 로그인 검증
        if (member.login(loginId, password)) {
            // 회원 등급에 따라 다른 메뉴 제공
            if (member.getMemberGrade().equals("관리자")) {
                adminMenu(); // 관리자 메뉴 호출
            } else {
                userMenu(); // 일반 사용자 메뉴 호출
            }
        } else {
            System.out.println("로그인에 실패했습니다."); // 로그인 실패 메시지
            initMenu(); // 초기 메뉴 재호출
        }
    }

    // 관리자 메뉴
    public void adminMenu() {
        System.out.println("관리자 메뉴입니다."); // 관리자 메뉴 제목 출력
        System.out.println("1. 회원 관리"); // 회원 관리 옵션 출력
        System.out.println("2. 도서 관리"); // 도서 관리 옵션 출력
        System.out.println("3. 대여 연장 승인 관리"); // 대여 연장 승인 관리 옵션 출력
        int choice = scanner.nextInt(); // 사용자 입력 선택

        switch (choice) {
            case 1:
                admin.manageUsers(); // 회원 관리 메서드 호출
                break;
            case 2:
                admin.manageBooks(); // 도서 관리 메서드 호출
                break;
            case 3:
                admin.approveExtension(); // 대여 연장 승인 메서드 호출
                break;
            default:
                System.out.println("잘못된 선택입니다."); // 잘못된 입력 처리
                adminMenu(); // 관리자 메뉴 재호출
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
            System.out.println("데이터베이스 오류: " + e.getMessage()); // SQLException 발생 시 오류 메시지 출력
        } catch (Exception e) {
            System.out.println("알 수 없는 오류 발생: " + e.getMessage()); // 기타 예외 처리
        }
    }

}
