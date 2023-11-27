package team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ChildCommentLikeManager {
    private Connection con;
    private Statement stmt;
    private PreparedStatement pstm;
    private ResultSet rs;
    private String user_id;

    public ChildCommentLikeManager(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
    }

    public void toggleChildCommentLike() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter child_cmt_like_id, child_comment_id");
            String ccl_id = sc.next();
            String ccmt_id = sc.next();

            String s3 = "select user_id_liker from child_comment_like " +
                    "where user_id_liker=\"" + user_id + "\" and child_cmt_id=\"" + ccmt_id + "\"";

            rs = stmt.executeQuery(s3);

            if (rs.next()) {
                String deleteQuery = "delete from child_comment_like where user_id_liker=\"" + user_id + "\"";
                String updateQuery = "update child_comment set ccm_like=ccm_like-1 where child_cmt_id=\'" + ccmt_id + "\'";

                pstm = con.prepareStatement(deleteQuery);
                pstm.executeUpdate();

                int count = stmt.executeUpdate(updateQuery);

                System.out.println("You unliked the child comment!");
            } else {
                String insertQuery = "insert into child_comment_like values('" + ccl_id + "','" + user_id + "','" + ccmt_id + "')";
                String updateQuery = "update child_comment set ccm_like=ccm_like+1 where child_cmt_id=\'" + ccmt_id + "\'";

                pstm = con.prepareStatement(insertQuery);
                pstm.executeUpdate();

                int count = stmt.executeUpdate(updateQuery);

                System.out.println("You liked the child comment!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

