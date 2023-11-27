package twitter.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserWritePostViewer { // 유저가 쓴 포스트만

    private Connection con;

    public UserWritePostViewer(Connection con) {
        this.con = con;
    }

    public void ViewerUserPosts(String user_id) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs;
        String sql = "select * from posts where user_id_writer=\'" + user_id + "\' ORDER BY update_date DESC";
        rs = stmt.executeQuery(sql);
        System.out.println("postid image content update registration like");
        while (rs.next()) {
            String postid = rs.getString(1);
            if (rs.wasNull())
                postid = "null";
            String image = rs.getString(3);
            if (rs.wasNull())
                image = "null";
            String content = rs.getString(4);
            if (rs.wasNull())
                content = "null";
            String update = rs.getString(5);
            if (rs.wasNull())
                update = "null";
            String registration = rs.getString(6);
            if (rs.wasNull())
                registration = "null";
            String like = rs.getString(7);
            if (rs.wasNull())
                like = "null";
            System.out.printf("%s %15s %15s %15s %15s %15s\n", postid, image, content, update, registration, like);
        }
    }
}
