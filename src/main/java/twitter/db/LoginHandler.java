package twitter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginHandler {

    private Connection connection;

    public LoginHandler(Connection connection) {
        this.connection = connection;
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
