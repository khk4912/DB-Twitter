package twitter.db;

import java.util.Date;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import twitter.exceptions.UserIDAlreadyExists;

public class SignUp {
    private Connection con;
    // private Scanner sc;

    public SignUp(Connection con) {
        this.con = con;
    }

    public void signUp(String userID, String password, String nickname, String gender, Date birthday)
            throws UserIDAlreadyExists {
        try {
            Statement stmt = con.createStatement();
            PreparedStatement pstm;
            ResultSet rs;

            String s1 = "select user_id from user where user_id=\"" + userID + "\"";
            rs = stmt.executeQuery(s1);

            if (rs.next())
                throw new UserIDAlreadyExists("유저 ID " + userID + "는 이미 존재합니다.\n다른 ID로 시도해주세요.");

            String insertQuery = "insert into User(user_id, pwd, nickname, gender, birthday) values (?, ?, ?, ?, ?)";
            pstm = con.prepareStatement(insertQuery);
            pstm.setString(1, userID);
            pstm.setString(2, password);
            pstm.setString(3, nickname);
            pstm.setString(4, gender);
            pstm.setDate(5, new java.sql.Date(birthday.getTime()));

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
