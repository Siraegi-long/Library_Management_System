package dbPackage;

public class Member {
    private String memberID; // 회원 ID
    private String name; // 이름
    private String memberGrade; // 회원 등급

    public Member() {
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    // 회원가입 메서드 (구현 필요)
    public void registerMember() {
        // 회원가입 로직 구현
        // 입력을 받고 회원 정보를 설정
    }

    // 내 정보 조회 메서드
    public void viewMemberInfo() {
        System.out.println("회원 ID: " + memberID);
        System.out.println("이름: " + name);
        System.out.println("회원 등급: " + memberGrade);
    }

    // 대여 연장 요청 메서드 (구현 필요)
    public void extendRentalPeriod() {
        // 대여 연장 요청 로직 구현
        System.out.println("대여 연장 요청 기능을 구현하세요.");
    }
}
