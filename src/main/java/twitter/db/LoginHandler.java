package twitter.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import twitter.exceptions.WrongIDorPassword;
import twitter.utils.TwitterAlert;
import twitter.utils.LoginContext;

public class LoginHandler {

    private Connection connection;

    public LoginHandler(Connection connection) {
        this.connection = connection;
    }

    public LoginContext loginUser(String userID, String password) throws WrongIDorPassword {
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT user_id FROM User WHERE user_id = \"" + userID + "\" AND Pwd=\"" + password + "\"";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Logged in!!");

            } else {
                throw new WrongIDorPassword("잘못된 ID 혹은 비밀번호를 입력하셨어요. 다시 시도해주세요.");
            }

            LoginContext context = new LoginContext(userID);
            return context;

        } catch (SQLException e) {
            e.printStackTrace();
            TwitterAlert.error("로그인 오류!", e.getMessage());
            return null;
        }

    }

    public void loginUser() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter user ID: ");
        String user_id = sc.next();

        System.out.println("Enter password: ");
        String pwd = sc.next();

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT user_id FROM User WHERE user_id = \"" + user_id + "\" AND Pwd=\"" + pwd + "\"";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Logged in!!");

            } else {
                System.out.println("Wrong ID/password. Please log in again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
