package dbPackage; // dbPackage라는 패키지에 속하는 클래스

// Overdue 클래스는 회원의 연체 관리 기능을 담당한다.
public class Overdue {
    // 연체 알림 발송 메서드
    public void sendOverdueAlert(Member member) {
        // 회원의 연체 횟수를 확인하여 알림 메시지 출력
        if (member.getOverdueCount() > 0) {
            System.out.println("연체 회원입니다. 대출 불가 회원입니다."); // 연체가 있는 경우
        } else {
            System.out.println("연체 기록이 없습니다."); // 연체가 없는 경우
        }
    }

    // 연체 횟수에 따른 회원 등급 변환 메서드
    public void convertMemberGrade(Member member) {
        // 연체 횟수가 2회 이상일 경우 회원 등급을 "퇴출"로 변경
        if (member.getOverdueCount() >= 2) {
            member.setMemberGrade("퇴출"); // 회원 등급 변경
            System.out.println("회원 " + member.getName() + "이(가) 퇴출되었습니다."); // 퇴출 알림
        // 연체 횟수가 1회 이상일 경우 회원 등급을 "연체"로 변경
        } else if (member.getOverdueCount() > 0) {
            member.setMemberGrade("연체"); // 회원 등급 변경
            System.out.println("회원 " + member.getName() + "의 등급이 연체로 변경되었습니다."); // 연체 알림
        }
    }

    // 연체 도서 연장 불가 처리 메서드
    public boolean isEligibleForExtension(Member member) {
        // 연체 횟수가 0일 경우 연장 가능
        return member.getOverdueCount() == 0; // 연장 가능 여부 반환
    }
}
