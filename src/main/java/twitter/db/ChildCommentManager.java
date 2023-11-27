package twitter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ChildCommentManager {
    private Connection con;
    private PreparedStatement pstm;
    private String user_id;

    public ChildCommentManager(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
    }

    public void addChildComment() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter child_cmt_id, post_id, comment_id");
            String ccm_id = sc.next();
            int postid = sc.nextInt();
            String comment_id = sc.next();
            sc.nextLine(); // consume the newline character

            System.out.println("Enter text");
            String content = sc.nextLine();

            String s5 = "insert into child_comment(child_cmt_id, user_id_writter, post_id, comment_id, content) " +
                    "values('" + ccm_id + "','" + user_id + "','" + postid + "','" + comment_id + "','" + content
                    + "')";

            pstm = con.prepareStatement(s5);
            pstm.executeUpdate();

            System.out.println("Child comment added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
