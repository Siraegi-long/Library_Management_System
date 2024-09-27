package dbPackage;

public class Main {
    public static void main(String[] args) {
    	System.out.print("ㅎㅇㅎㅇ");
        DBManager dbManager = new DBManager(); // DBManager 이름이 정확해야 함
        dbManager.initDBConnect();

        // DB 연결 객체 가져오기
        Member member = new Member(dbManager.getConnection());
        Book book = new Book(dbManager.getConnection());
        Admin admin = new Admin(dbManager.getConnection());

        // 예시 실행
        member.registerMember("홍길동", "hong123", "pass1234", "01012345678");
        member.viewMemberInfo("hong123");

        book.searchBook("티니핑");
        admin.manageUsers();
    }
}
