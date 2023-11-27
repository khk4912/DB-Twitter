package twitter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SignUp {
    private Connection con;
    private Statement stmt;
    private PreparedStatement pstm;
    private ResultSet rs;
    private Scanner sc;

    public SignUp(Connection con, Statement stmt, PreparedStatement pstm, ResultSet rs) {
        this.con = con;
        this.stmt = stmt;
        this.pstm = pstm;
        this.rs = rs;
        this.sc = new Scanner(System.in);
    }

    public void signUp() {
        try {
            String user_id, pwd, nickname;

            System.out.println("Input UserID / Password / Nickname / gender / birthday(2023-11-25)");
            user_id = sc.next();
            pwd = sc.next();
            nickname = sc.next();
            String gender = sc.next();
            String birthday = sc.next();

            String s1 = "select user_id from user where user_id=\"" + user_id + "\"";
            rs = stmt.executeQuery(s1);

            if (rs.next()) {
                System.out.println("User name already exists. Please try again!");
            } else {
                String insertQuery = "insert into User(user_id, pwd, nickname, gender, birthday) values (?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(insertQuery);
                pstm.setString(1, user_id);
                pstm.setString(2, pwd);
                pstm.setString(3, nickname);
                pstm.setString(4, gender);
                pstm.setString(5, birthday);

                pstm.executeUpdate();
                System.out.println("Sign-up successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
