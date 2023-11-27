package team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CommentManager {
    private Connection con;
    private PreparedStatement pstm;
    private String user_id;

    public CommentManager(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
    }

    public void addComment() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter comment_id, post_id");
            String comment_id = sc.next();
            int postid = sc.nextInt();
            sc.nextLine(); // Consume the newline character left by nextInt()

            System.out.println("Enter text");
            String content = sc.nextLine();

            String s5 = "insert into comment(comment_id, user_id_writer, post_id, content) values('" + comment_id + "', '" + user_id + "', '" + postid + "', '" + content + "')";
            pstm = con.prepareStatement(s5);
            pstm.executeUpdate();

            System.out.println("Comment added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

