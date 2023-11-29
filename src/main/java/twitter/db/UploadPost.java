package twitter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import twitter.utils.TwitterAlert;

public class UploadPost {
    private Connection con;
    private PreparedStatement pstm;
    private String user_id;

    public UploadPost(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
    }

    // TODO: Image?
    public void uploadPost(String userID, String content) {
        String insertQuery = "insert into posts( user_id_writer, content) values (?, ?)";
        try {
            pstm = con.prepareStatement(insertQuery);
            pstm.setString(1, user_id);
            pstm.setString(2, content);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            TwitterAlert.error("게시글 업로드 오류!", e.getMessage());
        }

    }

    public void uploadPost() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter pid, upload post_image, and content");

            int pid = sc.nextInt();
            String post_image = sc.next();
            String content = sc.next();

            String insertQuery = "insert into posts(post_id, user_id_writer, post_image, content) values (?, ?, ?, ?)";
            pstm = con.prepareStatement(insertQuery);
            pstm.setInt(1, pid);
            pstm.setString(2, user_id);
            pstm.setString(3, post_image);
            pstm.setString(4, content);

            pstm.executeUpdate();
            System.out.println("Post uploaded successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
