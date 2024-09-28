package dbPackage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();  // 관리자 객체 생성
        Member currentUser = null;  // 현재 로그인한 사용자

        while (true) {
            System.out.println("=== 티니핑 대도서관 ===");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentUser = loginMenu();  // 로그인 시도
                    if (currentUser != null) {
                        System.out.println("로그인 성공: " + currentUser.getName() + "님, 환영합니다.");
                        if (currentUser.getMemberGrade().equals("관리자")) {
                            adminMenu(admin);  // 관리자 메뉴로 이동
                        } else {
                            userMenu(currentUser);  // 일반 사용자 메뉴로 이동
                        }
                    } else {
                        System.out.println("로그인 실패: 잘못된 ID 또는 비밀번호입니다.");
                    }
                    break;

                case 2:
                    Member newMember = new Member();
                    newMember.registerMember();  // 회원가입 기능 실행
                    System.out.println("회원가입이 완료되었습니다.");
                    break;

                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);

                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }

    // 로그인 처리 메서드
    public static Member loginMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID를 입력하세요: ");
        String inputId = scanner.nextLine();

        System.out.print("비밀번호를 입력하세요: ");
        String inputPw = scanner.nextLine();

        // 데이터베이스와 연동해 로그인 검증 필요 (여기서는 예시로 간단하게 처리)
        // 실제로는 DB에서 사용자를 확인하고 객체를 반환해야 함
        Member mockUser = new Member();
        mockUser.setName("홍길동");
        mockUser.setMemberID("1234");
        mockUser.setMemberGrade("일반");
        if (inputId.equals("admin") && inputPw.equals("admin")) {
            mockUser.setMemberGrade("관리자");
        }
        return mockUser;  // 예시로 리턴, 실제로는 DB 조회 결과 리턴 필요
    }

    // 관리자 메뉴
    public static void adminMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== 관리자 메뉴 ===");
            System.out.println("1. 전체 회원 관리");
            System.out.println("2. 도서 목록 관리");
            System.out.println("3. 대여 연장 요청 승인");
            System.out.println("4. 로그아웃");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    admin.manageUsers();  // 회원 관리
                    break;
                case 2:
                    admin.manageBooks();  // 도서 목록 관리
                    break;
                case 3:
                    admin.approveExtension();  // 대여 연장 승인
                    break;
                case 4:
                    System.out.println("관리자 로그아웃 완료.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }

    // 사용자 메뉴 (일반 회원)
    public static void userMenu(Member member) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== 사용자 메뉴 ===");
            System.out.println("1. 도서 검색");
            System.out.println("2. 내 정보 조회");
            System.out.println("3. 도서 대여");
            System.out.println("4. 도서 반납");
            System.out.println("5. 대여 연장 요청");
            System.out.println("6. 로그아웃");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("도서 검색 기능 실행...");
                    // Book 클래스의 searchBook() 호출하여 도서 검색 처리
                    break;
                case 2:
                    member.viewMemberInfo();  // 내 정보 조회
                    break;
                case 3:
                    System.out.println("도서 대여 기능 실행...");
                    // Book 클래스의 rentBook() 호출하여 도서 대여 처리
                    break;
                case 4:
                    System.out.println("도서 반납 기능 실행...");
                    // Book 클래스의 returnBook() 호출하여 도서 반납 처리
                    break;
                case 5:
                    member.extendRentalPeriod();  // 대여 연장 요청
                    break;
                case 6:
                    System.out.println("로그아웃 완료.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }
}
