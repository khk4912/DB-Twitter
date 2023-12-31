package twitter.db;

import java.util.Date;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import twitter.db.YourProfile;

import twitter.utils.PostContext;
import twitter.utils.TwitterAlert;

public class Home { // User&FollowingPostViewer=timeline

    private Connection con;
    private String user_id;

    public Home(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
    }

    public ArrayList<PostContext> getFollowingPosts() {

        ArrayList<PostContext> results = new ArrayList<>();

        String sql = "select * from posts where user_id_writer = ? or user_id_writer in  (select following_id from following where follow_id = ?) order by update_date desc";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, user_id);
            pstmt.setString(2, user_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nickname = new YourProfile(con, rs.getString("user_id_writer")).getUserInfo().nickname;

                    PostContext post = new PostContext(rs.getInt("post_id"), nickname, rs.getString("user_id_writer"),
                            rs.getString("post_image"), rs.getString("content"), rs.getTimestamp("update_date"),
                            rs.getTimestamp("registration_date"), rs.getInt("num_of_likes"));
                    results.add(post);
                }
            }

        } catch (Exception e) {
            TwitterAlert.error("타임라인 오류!", e.getMessage());
        }

        return results;

    }

    public void viewFollowingPosts() throws SQLException {
        String sql = "SELECT posts.*, user.nickname " +
                "FROM posts " +
                "JOIN following ON posts.user_id_writer = following.user_id_following " +
                "JOIN user ON posts.user_id_writer = user.user_id " +
                "WHERE following.user_id = ? OR posts.user_id_writer = ? " +
                "ORDER BY posts.update_date DESC";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, user_id);
            pstmt.setString(2, user_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("postid writer_id nickname image content update registration like");
                while (rs.next()) {
                    String postid = rs.getString(1);
                    if (rs.wasNull())
                        postid = "null";
                    String writer = rs.getString(2);
                    if (rs.wasNull())
                        writer = "null";
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
                    String Nickname = rs.getString(9);
                    if (rs.wasNull())
                        Nickname = "null";
                    System.out.printf("%s %15s %15s %15s %15s %15s %15s %15s\n", postid, writer, Nickname, image,
                            content, update, registration, like);
                }
            }
        }
    }

    // Add other methods if necessary
}
