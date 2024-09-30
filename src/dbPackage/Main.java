package dbPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("  /\\_/\\  티니티니");
        System.out.println(" ( o.o )  티니티니");
        System.out.println("  > ^ <  티니티니핑");
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Member currentUser = null;

        while (true) {
            System.out.println("=== 티니핑 대도서관 ===");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try (Connection conn = DBConnection.getConnection()) {
                switch (choice) {
                    case 1:
                        currentUser = loginMenu(conn); // 로그인 메뉴 호출
                        if (currentUser != null) {
                            System.out.println("로그인 성공: " + currentUser.getName() + "님, 환영합니다.");
                            if (currentUser.getMemberGrade().equals("관리자")) {
                                adminMenu(admin, conn); // 관리자 메뉴 호출
                            } else {
                                Menu.userMenu(currentUser, conn); // 일반 사용자 메뉴 호출
                            }
                        } else {
                            System.out.println("로그인 실패: 잘못된 ID 또는 비밀번호입니다.");
                        }
                        break;
                    case 2:
                        Member newMember = new Member(null,null,null,null);
                        newMember.registerMember();
                        break;
                    case 3:
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
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
        System.out.print("ID를 입력하세요: ");
        String inputId = scanner.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String inputPw = scanner.nextLine();
        return Member.login(conn, inputId, inputPw); // 로그인 시 데이터베이스에서 확인
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
