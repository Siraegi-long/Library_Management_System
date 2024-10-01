package dbPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Menu JoinTitle = new Menu();
        Menu MainMenu = new Menu();

        // 접속 타이틀 화면
        JoinTitle.JoinTitle();

        // 메인 메뉴 호출
        MainMenu.MainMenu();

    }
}