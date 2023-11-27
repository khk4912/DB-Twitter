package twitter.db;

import java.sql.*;
import java.util.*;

public class Bookmark {
    private Connection con;

    public Bookmark(Connection con) {
        this.con = con;
    }

    public void addBookmark(String userId, int postId) {
        try {
            String insertSql = "INSERT INTO bookmarks (user_id, post_id) VALUES (?, ?)";
            PreparedStatement insertPstmt = con.prepareStatement(insertSql);
            insertPstmt.setString(1, userId);
            insertPstmt.setInt(2, postId);
            insertPstmt.executeUpdate();

            System.out.println("User " + userId + " has bookmarked post " + postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBookmark(String userId, int postId) {
        try {
            String deleteSql = "DELETE FROM bookmarks WHERE user_id = ? AND post_id = ?";
            PreparedStatement deletePstmt = con.prepareStatement(deleteSql);
            deletePstmt.setString(1, userId);
            deletePstmt.setInt(2, postId);
            deletePstmt.executeUpdate();

            System.out.println("User " + userId + " has removed bookmark from post " + postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getBookmarkedPosts(String userId) {
        List<Integer> bookmarkedPosts = new ArrayList<>();
        try {
            String selectSql = "SELECT post_id FROM bookmarks WHERE user_id = ?";
            PreparedStatement selectPstmt = con.prepareStatement(selectSql);
            selectPstmt.setString(1, userId);
            ResultSet rs = selectPstmt.executeQuery();

            while (rs.next()) {
                int postId = rs.getInt("post_id");
                bookmarkedPosts.add(postId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookmarkedPosts;
    }
}
