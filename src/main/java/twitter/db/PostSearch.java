package twitter.db;

import java.sql.*;
import java.util.Scanner;

public class PostSearch {
    private Connection con;

    public PostSearch(Connection con) {
        this.con = con;
    }

    public void searchByContent(String content) {
        try {
            System.out.println("Enter the content to search:");
            String sql = "SELECT * FROM posts WHERE content LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + content + "%"); // Use '%' for partial match
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Post ID: " + rs.getInt("post_id") + ", Writer ID: " + rs.getString("user_id_writer")
                        +
                        ", Image: " + rs.getString("post_image") + ", Content: " + rs.getString("content") +
                        ", Update Date: " + rs.getTimestamp("update_date") + ", Registration Date: "
                        + rs.getTimestamp("registration_date") +
                        ", Number of Likes: " + rs.getInt("num_of_likes") + ", Report: " + rs.getString("report"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchById(String ID) {
        try {
            String sql = "SELECT * FROM posts WHERE writer_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Post ID: " + rs.getInt("post_id") + ", Writer ID: " + rs.getString("user_id_writer")
                        +
                        ", Image: " + rs.getString("post_image") + ", Content: " + rs.getString("content") +
                        ", Update Date: " + rs.getTimestamp("update_date") + ", Registration Date: "
                        + rs.getTimestamp("registration_date") +
                        ", Number of Likes: " + rs.getInt("num_of_likes") + ", Report: " + rs.getString("report"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByHashTag(String tag) {
        try {
            String searchSql = "SELECT * FROM hashtag JOIN posts ON hashtag.post_id = posts.post_id WHERE tag = ?";
            PreparedStatement searchPstmt = con.prepareStatement(searchSql);
            searchPstmt.setString(1, tag); // �˻��ϰ��� �ϴ� �±�
            ResultSet rs = searchPstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Post ID: " + rs.getInt("post_id") + ", Writer ID: " + rs.getString("user_id_writer")
                        +
                        ", Image: " + rs.getString("post_image") + ", Content: " + rs.getString("content") +
                        ", Update Date: " + rs.getTimestamp("update_date") + ", Registration Date: "
                        + rs.getTimestamp("registration_date") +
                        ", Number of Likes: " + rs.getInt("num_of_likes") + ", Report: " + rs.getString("report") +
                        ", Hashtag: " + rs.getString("tag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByLocationTag(String location) {
        try {
            String searchSql = "SELECT * FROM location JOIN posts ON location.post_id = posts.post_id WHERE location = ?";
            PreparedStatement searchPstmt = con.prepareStatement(searchSql);
            searchPstmt.setString(1, location); // �˻��ϰ��� �ϴ� ��� �±�
            ResultSet rs = searchPstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Post ID: " + rs.getInt("post_id") + ", Writer ID: " + rs.getString("user_id_writer")
                        +
                        ", Image: " + rs.getString("post_image") + ", Content: " + rs.getString("content") +
                        ", Update Date: " + rs.getTimestamp("update_date") + ", Registration Date: "
                        + rs.getTimestamp("registration_date") +
                        ", Number of Likes: " + rs.getInt("num_of_likes") + ", Report: " + rs.getString("report") +
                        ", Location Tag: " + rs.getString("location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchUserById(String ID) {
        try {
            String sql = "SELECT * FROM user WHERE user_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("User ID: " + rs.getString("user_id") + ", Nickname: " + rs.getString("nickname") +
                        ", Access Date: " + rs.getTimestamp("access_date") + ", Update Date: "
                        + rs.getTimestamp("update_date") +
                        ", First Name: " + rs.getString("first_name") + ", Last Name: " + rs.getString("last_name") +
                        ", Gender: " + rs.getString("gender") + ", Email: " + rs.getString("email") +
                        ", Birthday: " + rs.getDate("birthday") + ", Age: " + rs.getInt("age") +
                        ", Profile Image: " + rs.getString("profile_image") + ", Introduce: "
                        + rs.getString("introduce") +
                        ", Report: " + rs.getString("report"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
