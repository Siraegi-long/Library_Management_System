package dbPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Member {
    private Connection conn;

    public Member(Connection conn) {
        this.conn = conn;
    }

    public void registerMember(String name, String id, String pw, String phone) {
        String sql = "INSERT INTO UserTbl (name, ID, PW, phone) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.setString(3, pw);
            pstmt.setString(4, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMemberInfo(String id, String newPhone) {
        String sql = "UPDATE UserTbl SET phone = ? WHERE ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPhone);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(String id) {
        String sql = "DELETE FROM UserTbl WHERE ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewMemberInfo(String id) {
        String sql = "SELECT * FROM UserTbl WHERE ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getString("ID"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
