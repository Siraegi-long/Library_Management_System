package dbPackage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("  /\\_/\\  티니티니");
        System.out.println(" ( o.o )  티니티니");
        System.out.println("  > ^ <  티니티니핑");
        // 스캐너 객체 생성 - 사용자 입력을 받기 위해 사용
        Scanner scanner = new Scanner(System.in);
        
        // Admin 객체 생성 - 관리자 관련 기능을 수행하는 객체
        Admin admin = new Admin();
        
        // 현재 로그인한 사용자 정보를 저장하는 변수, 처음엔 null로 초기화
        Member currentUser = null;

        // 프로그램이 종료될 때까지 계속 실행되는 무한 루프
        while (true) {
        	
            // 메인 메뉴 출력
            System.out.println("=== 티니핑 대도서관 ===");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.print("선택: ");
            
            // 사용자로부터 메뉴 선택을 입력받음
            int choice = scanner.nextInt();
            scanner.nextLine(); // 숫자 입력 후 남은 버퍼를 비워줌

            // 사용자가 선택한 메뉴에 따라 처리
            switch (choice) {
                case 1:
                    // 로그인 기능 호출, 로그인 성공 시 현재 사용자 정보 저장
                    currentUser = loginMenu(); 
                    
                    // 로그인 성공 여부 확인
                    if (currentUser != null) {
                        // 로그인 성공 시 사용자 이름을 출력하여 환영 메시지
                        System.out.println("로그인 성공: " + currentUser.getName() + "님, 환영합니다.");
                        
                        // 로그인한 사용자가 관리자인 경우 관리자 메뉴로 이동
                        if (currentUser.getMemberGrade().equals("관리자")) {
                            adminMenu(admin);
                        } 
                        // 일반 사용자일 경우 사용자 메뉴로 이동
                        else {
                            userMenu(currentUser);
                        }
                    } 
                    // 로그인 실패 시 메시지 출력
                    else {
                        System.out.println("로그인 실패: 잘못된 ID 또는 비밀번호입니다.");
                    }
                    break;

                case 2:
                    // 새로운 회원 객체 생성 (회원가입을 위해)
                    Member newMember = new Member(null, null, null, null);
                    
                    // 회원가입 실행, 사용자 입력에 따라 회원 정보가 설정됨
                    newMember.registerMember("name", "uniqueNo", "id", "pw");
                    
                    // 회원가입 성공 메시지 출력
                    System.out.println("회원가입이 완료되었습니다.");
                    break;

                case 3:
                    // 프로그램 종료 메시지 출력 후 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);

                default:
                    // 잘못된 메뉴 선택 시 메시지 출력
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }

    // 로그인 메뉴 - 사용자로부터 ID와 비밀번호를 입력받아 로그인 처리
    public static Member loginMenu() {
        Scanner scanner = new Scanner(System.in);
        
        // 사용자에게 ID 입력을 요청하고 입력값을 받음
        System.out.print("ID를 입력하세요: ");
        String inputId = scanner.nextLine();

        // 사용자에게 비밀번호 입력을 요청하고 입력값을 받음
        System.out.print("비밀번호를 입력하세요: ");
        String inputPw = scanner.nextLine();

        // 입력받은 ID와 비밀번호로 로그인 시도, 성공 시 해당 사용자 정보 반환
        return Member.login(inputId, inputPw);
    }

    // 관리자 메뉴 - 관리자가 사용할 수 있는 기능을 선택하는 메뉴
    public static void adminMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in);

        // 로그아웃할 때까지 반복 실행
        while (true) {
            // 관리자 메뉴 출력
            System.out.println("=== 관리자 메뉴 ===");
            System.out.println("1. 전체 회원 관리");
            System.out.println("2. 도서 목록 관리");
            System.out.println("3. 대여 연장 요청 승인");
            System.out.println("4. 로그아웃");
            System.out.print("선택: ");
            
            // 관리자에게 메뉴 선택을 받음
            int choice = scanner.nextInt();

            // 선택한 메뉴에 따른 기능 실행
            switch (choice) {
                case 1:
                    // 전체 회원 관리 기능 호출
                    admin.manageUsers();
                    break;
                case 2:
                    // 도서 목록 관리 기능 호출
                    admin.manageBooks();
                    break;
                case 3:
                    // 대여 연장 요청 승인 기능 호출
                    admin.approveExtension();
                    break;
                case 4:
                    // 로그아웃 메시지 출력 후 관리자 메뉴 종료
                    System.out.println("관리자 로그아웃 완료.");
                    return;
                default:
                    // 잘못된 메뉴 선택 시 메시지 출력
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }

    // 사용자 메뉴 - 사용자가 이용할 수 있는 기능을 선택하는 메뉴
    public static void userMenu(Member member) {
        Scanner scanner = new Scanner(System.in);

        // 로그아웃할 때까지 반복 실행
        while (true) {
            // 사용자 메뉴 출력
            System.out.println("=== 사용자 메뉴 ===");
            System.out.println("1. 도서 검색");
            System.out.println("2. 내 정보 조회");
            System.out.println("3. 도서 대여");
            System.out.println("4. 도서 반납");
            System.out.println("5. 대여 연장 요청");
            System.out.println("6. 로그아웃");
            System.out.print("선택: ");
            
            // 사용자로부터 메뉴 선택을 받음
            int choice = scanner.nextInt();

            // 선택한 메뉴에 따른 기능 실행
            switch (choice) {
                case 1:
                    // 도서 검색 기능 호출
                    member.searchBook();
                    break;
                case 2:
                    // 사용자 정보 조회 기능 호출
                    member.viewMemberInfo();
                    break;
                case 3:
                    // 도서 대여 시 도서 ID 입력을 받아 대여 처리
                    System.out.print("대여할 도서 ID를 입력하세요: ");
                    int bookId = scanner.nextInt();
                    Book.rentBook(bookId, Integer.parseInt(member.getMemberId()));
                    break;
                case 4:
                    // 도서 반납 시 도서 ID 입력을 받아 반납 처리
                    System.out.print("반납할 도서 ID를 입력하세요: ");
                    int returnBookId = scanner.nextInt();
                    Book.returnBook(returnBookId);
                    break;
                case 5:
                    // 대여 연장 요청 기능 호출
                    member.extendRentalPeriod();
                    break;
                case 6:
                    // 로그아웃 메시지 출력 후 사용자 메뉴 종료
                    System.out.println("로그아웃 완료.");
                    return;
                default:
                    // 잘못된 메뉴 선택 시 메시지 출력
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }
}
