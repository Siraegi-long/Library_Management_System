package dbPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void registerMember(Connection conn, String name, String id, String pw, String phone) {
        // 유효성 검사
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름을 입력하세요.");
        }
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID를 입력하세요.");
        }
        if (pw == null || pw.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 최소 8자리 이상이어야 합니다.");
        }
        if (phone != null && phone.length() != 11) {
            throw new IllegalArgumentException("전화번호는 11자리 숫자여야 합니다.");
        }

        try {
            // 중복 ID 확인
            String checkQuery = "SELECT ID FROM usertbl WHERE ID = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, id);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    throw new IllegalArgumentException("이미 사용 중인 ID입니다.");
                }
            }

            // 회원 등록
            String insertQuery = "INSERT INTO usertbl (name, memberGrade, ID, PW, phone) VALUES (?, '일반', ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, name);
                insertStmt.setString(2, id);
                insertStmt.setString(3, pw);
                insertStmt.setString(4, phone);
                insertStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Member login(Connection conn, String inputId, String inputPw) {
        // 로그인 로직 (DB에서 사용자 정보 확인)
        Member member = null;
        String query = "SELECT memberId, name, memberGrade FROM usertbl WHERE ID = ? AND PW = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, inputId);
            stmt.setString(2, inputPw);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String memberId = rs.getString("memberId");
                String name = rs.getString("name");
                String memberGrade = rs.getString("memberGrade");
                member = new Member(memberId, name, inputPw, memberGrade); // 로그인 성공 시 Member 객체 생성
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public void searchBook(Connection conn) {
        // 검색 도서 로직 (가정)
    }

    public void viewMemberInfo() {
        System.out.println("회원 ID: " + this.memberId);
        System.out.println("회원 이름: " + this.name);
        System.out.println("회원 등급: " + this.memberGrade);
    }

    public void extendRentalPeriod(Connection conn) {
        System.out.println("대여 연장 요청이 완료되었습니다."); // 실제 로직은 추가 필요
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
