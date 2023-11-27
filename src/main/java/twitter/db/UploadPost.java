package team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UploadPost {
    private Connection con;
    private PreparedStatement pstm;
    private String user_id;

    public UploadPost(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
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
