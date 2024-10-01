package dbPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Member member = null;
    private Member currentUser = null;
    private Admin admin = new Admin();

    public void JoinTitle() {
        System.out.println("- 티니핑 라이브러리 -");
        System.out.println("");
        System.out.println("	   ／＞　 フ		 .---. .-..-. .-..-..----. .-..-. .-. .---. ");
        System.out.println("	  | 　_　_|		{_   _}| ||  `| || || {}  }| ||  `| |/   __} ");
        System.out.println("	／` ミ＿x ノ		  | |  | || |\\| || || |-'' | || |`_ |   \\");
        System.out.println("	/　　　　 |		  `-'  `-'`-' `-'`-'`-'    `-'`-' `-' `---'  ");
        System.out.println("	│　　  |　|			");
        System.out.println("	／￣|　|　|　|		.-.   .-..----. .----.   .--.   .----. .-.  .-.");
        System.out.println("	＼　|　|　|　|		| |   | || {}  }| {}  } / {} \\ | {}  }  \\ \\/ / ");
        System.out.println("	  `￣|　|　|		| `--.| || {}  }| .-.\\/   /\\ \\ | .-. \\	 }  {  ");
        System.out.println("	      `ー'`ー'		`----'`-'`----' `-' `-' `-'  `-' `-'-'	 `--'    ");
        System.out.println("");

    }

    // 메인메뉴
    public void MainMenu() {
        Scanner scanner = new Scanner(System.in);

        // 입력 유효성 검사 및 예외처리
        int main_Menu_Choice = 0;

        while (main_Menu_Choice < 1 || main_Menu_Choice > 3) {

            System.out.println("=======================================");
            System.out.println("");
            System.out.println("         ★ 티니핑 대도서관 ★         ");
            System.out.println("");
            System.out.println("=======================================");
            System.out.println("");
            System.out.println("	- 접속 경로를 선택해주세요. -");
            System.out.println("");
            System.out.println("	  1. 로그인(Login)");
            System.out.println("	  2. 회원가입(Register)");
            System.out.println("	  3. 종료(Exit)");
            System.out.println("");
            System.out.println("=======================================");
            System.out.println("");
            System.out.print("숫자를 입력해주세요: ");

            if (scanner.hasNextInt()) {
                main_Menu_Choice = scanner.nextInt();
                scanner.nextLine(); // 줄바꿈 제거

            } else {
                System.out.println("선택지(숫자)를 입력해주세요.");
                scanner.next(); // 잘못된 입력 처리 (숫자가 아닌 입력)
                //System.out.print("숫자를 입력해주세요: ");

            }

            try (Connection conn = DBConnection.getConnection()) {
                switch (main_Menu_Choice) {
                    case 1:
                        currentUser = loginMenu(conn); // 로그인 메뉴 호출
                        if (currentUser != null) {
                            System.out.println("로그인 성공: " + currentUser.getName() + "님, 환영합니다.");
                            if (currentUser.getMemberGrade().equals("관리자")) {
                                adminMenu(admin, conn); // 관리자 메뉴 호출
                            } else {
                                Menu.userMenu(currentUser, conn);// 일반 사용자 메뉴 호출
                                main_Menu_Choice = 0;
                            }
                        } else {
                            System.out.println("로그인 실패: 잘못된 ID 또는 비밀번호입니다!");
                            System.out.println("접속 경로를 다시 선택해주세요.");
                            System.out.println("");
                            main_Menu_Choice = 0; // 재시도를 위한 변수 초기화
                        }
                        break;
                    case 2:
                        Member newMember = new Member(null,null,null,null);
                        newMember.registerMember();
                        break;
                    case 3:
                        System.out.println("");
                        System.out.print("정말로 종료하시겠습니까? (Y/N): ");
                        System.out.print("");
                        String exitChoice = scanner.nextLine().trim().toUpperCase();
                        if (exitChoice.equals("Y")) {
                            System.out.println("");
                            System.out.println("프로그램을 종료합니다.");
                            System.exit(0);
                        } else if (exitChoice.equals("N")) {
                            System.out.println("");
                            System.out.println("접속 경로를 다시 선택해주세요.");
                            main_Menu_Choice = 0; // 메뉴를 다시 출력하도록 초기화
                        } else {
                            System.out.println("잘못된 선택입니다. 메뉴로 돌아갑니다.");
                            main_Menu_Choice = 0;
                        }
                        break;
                    default:
                        System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
                }
            } catch (SQLException e) {
                System.out.println("데이터베이스 오류: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }

    // 로그인 메뉴
    public static Member loginMenu(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("ID를 입력하세요: ");
        String inputId = scanner.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String inputPw = scanner.nextLine();
        System.out.println("");
        return Member.login(conn, inputId, inputPw); // 로그인 시 데이터베이스에서 확인
    }

    // 사용자 메뉴
    public static void userMenu(Member member, Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=======================================");
            System.out.println("");
            System.out.println("      - 사용자 메뉴를 선택해주세요. -");
            System.out.println("");
            System.out.println("=======================================");
            System.out.println("");
            System.out.println("	    1. 도서 검색");
            System.out.println("	    2. 도서 대여");
            System.out.println("	    3. 도서 반납");
            System.out.println("	    4. 회원 정보 보기");
            System.out.println("	    5. 로그아웃");
            System.out.println("");
            System.out.print("숫자를 입력해주세요: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Book.searchBook(); // 도서 검색
                    break;
                case 2:
                    Book.rentBook(member);

                    break;
                case 3:
                    Book.returnBook(conn, choice); // 반납
                    break;
                case 4:
                    member.viewMemberInfo(); // 회원 정보 보기
                    break;
                case 5:
                    System.out.println("로그아웃 완료.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }

        }
    }

    // 관리자 메뉴
    public static void adminMenu(Admin admin, Connection conn) throws SQLException {
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
                    admin.manageUsers(conn); // 회원 관리
                    break;
                case 2:
                    admin.manageBooks(conn); // 도서 관리
                    break;
                case 3:
                    admin.approveExtension(conn); // 대여 연장 승인
                    break;
                case 4:
                    System.out.println("관리자 로그아웃 완료.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }
}
