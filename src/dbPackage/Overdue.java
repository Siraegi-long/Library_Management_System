package dbPackage;

public class Overdue {
    // 연체 알림 발송
    public void sendOverdueAlert(Member member) {
        if (member.getOverdueCount() > 0) {
            System.out.println("연체 회원입니다. 대출 불가 회원입니다.");
        } else {
            System.out.println("연체 기록이 없습니다.");
        }
    }

    // 연체 횟수에 따른 회원 등급 변환
    public void convertMemberGrade(Member member) {
        if (member.getOverdueCount() >= 2) {
            member.setMemberGrade("퇴출");
            System.out.println("회원 " + member.getName() + "이(가) 퇴출되었습니다.");
        } else if (member.getOverdueCount() > 0) {
            member.setMemberGrade("연체");
            System.out.println("회원 " + member.getName() + "의 등급이 연체로 변경되었습니다.");
        }
    }

    // 연체 도서 연장 불가 처리
    public boolean isEligibleForExtension(Member member) {
        return member.getOverdueCount() == 0;
    }
}
