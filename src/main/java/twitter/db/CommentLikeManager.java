package team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CommentLikeManager {
    private Connection con;
    private PreparedStatement pstm;
    private Statement stmt;
    private ResultSet rs;
    private String user_id;

    public CommentLikeManager(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
    }

    public void toggleCommentLike() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter cl_id, comment_id");
            String cl_id = sc.next();
            String commentid = sc.next();

            String s3 = "select user_id_liker from comment_like where user_id_liker='" + user_id + "' and comment_id='" + commentid + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(s3);

            if (rs.next()) {
                s3 = "delete from comment_like where user_id_liker='" + user_id + "' and comment_id='" + commentid + "'";
                String s4 = "update comment set num_of_likes=num_of_likes-1 where comment_id='" + commentid + "'";
                pstm = con.prepareStatement(s3);
                pstm.executeUpdate();
                int count = stmt.executeUpdate(s4);

                System.out.println("Comment like removed successfully!");
            } else {
                s3 = "insert into comment_like values('" + cl_id + "','" + user_id + "','" + commentid + "')";
                String s4 = "update comment set num_of_likes=num_of_likes+1 where comment_id='" + commentid + "'";
                pstm = con.prepareStatement(s3);
                pstm.executeUpdate();
                int count = stmt.executeUpdate(s4);

                System.out.println("Comment like added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
