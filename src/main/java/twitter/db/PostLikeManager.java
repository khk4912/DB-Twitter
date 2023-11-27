package twitter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostLikeManager {
    private Connection con;
    private PreparedStatement pstm;
    private Statement stmt;
    private ResultSet rs;
    private String user_id;

    public PostLikeManager(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void managePostLike() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter pl_id, post id");
            String pl_id = sc.next();
            int postid = sc.nextInt();
            String s3 = null;
            String s4 = null;

            System.out.println(pl_id + " " + postid);

            s3 = "select user_id_liker from post_like where user_id_liker=\"" + user_id + "\" and post_id=\"" + postid
                    + "\"";
            rs = stmt.executeQuery(s3);

            if (rs.next()) {
                s3 = "delete from post_like where user_id_liker=\"" + user_id + "\"";
                s4 = "update posts set num_of_likes=num_of_likes-1 where post_id='" + postid + "'";
                pstm = con.prepareStatement(s3);
                pstm.executeUpdate();
                int count = stmt.executeUpdate(s4);
                System.out.println("Like removed successfully!");
            } else {
                s3 = "insert into post_like values('" + pl_id + "','" + postid + "','" + user_id + "')";
                s4 = "update posts set num_of_likes=num_of_likes+1 where post_id='" + postid + "'";
                pstm = con.prepareStatement(s3);
                pstm.executeUpdate();
                int count = stmt.executeUpdate(s4);
                System.out.println("Like added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
