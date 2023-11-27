package team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserProfileUpdater {

    private Connection con;

    public UserProfileUpdater(Connection con) {
        this.con = con;
    }

    public void updateProfile(String user_id) throws SQLException {
        Scanner sc = new Scanner(System.in);
        PreparedStatement pstm;
        String s12;

        System.out.println("0 to birthday, 1 to first&last name, 2 to gender, "
                + "3 to email, 4 to age, 5 to introduce");
        int op5 = sc.nextInt();
        sc.nextLine();

        if (op5 == 0) {
            System.out.println("enter birthday ex. 2023-01-01");
            String birth = sc.nextLine();
            s12 = "update user set birthday=\'" + birth + "\' where user_id=\'" + user_id + "\'";
            pstm = con.prepareStatement(s12);
            pstm.executeUpdate();
        } else if (op5 == 1) {
            System.out.println("enter first name");
            String first = sc.nextLine();
            System.out.println("enter last name");
            String last = sc.nextLine();
            s12 = "update user set first_name=\'" + first + "\',second_name=\'" + last + "\' where user_id=\'" + user_id + "\'";
            pstm = con.prepareStatement(s12);
            pstm.executeUpdate();
        } else if (op5 == 2) {
            System.out.println("enter gender");
            String gender = sc.nextLine();
            s12 = "update user set gender=\'" + gender + "\' where user_id=\'" + user_id + "\'";
            pstm = con.prepareStatement(s12);
            pstm.executeUpdate();
        } else if (op5 == 3) {
            System.out.println("enter email");
            String email = sc.nextLine();
            s12 = "update user set email=\'" + email + "\' where user_id=\'" + user_id + "\'";
            pstm = con.prepareStatement(s12);
            pstm.executeUpdate();
        } else if (op5 == 4) {
            System.out.println("enter age");
            int age = sc.nextInt();
            sc.nextLine();
            s12 = "update user set age=\'" + age + "\' where user_id=\'" + user_id + "\'";
            pstm = con.prepareStatement(s12);
            pstm.executeUpdate();
        } else if (op5 == 5) {
            System.out.println("enter introduce");
            String introduce = sc.nextLine();
            s12 = "update user set introduce=\'" + introduce + "\' where user_id=\'" + user_id + "\'";
            pstm = con.prepareStatement(s12);
            pstm.executeUpdate();
        }
    }
}

