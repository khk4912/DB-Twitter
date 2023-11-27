package twitter.db;

import java.sql.*;

public class Retweet {
    private Connection con;

    public Retweet(Connection con) {
        this.con = con;
    }

    public void execute(String retweetId, String userId, int postId, String content, String additionalComment) {
        try {
            String checkSql = "SELECT * FROM retweet WHERE retweet_id = ?";
            PreparedStatement checkPstmt = con.prepareStatement(checkSql);
            checkPstmt.setString(1, retweetId);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                String insertSql = "INSERT INTO retweet(retweet_id, user_id, post_id, content, additional_comment) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertPstmt = con.prepareStatement(insertSql);
                insertPstmt.setString(1, retweetId);
                insertPstmt.setString(2, userId);
                insertPstmt.setInt(3, postId);
                insertPstmt.setString(4, content);
                insertPstmt.setString(5, additionalComment);
                insertPstmt.executeUpdate();

                // Get the next post_id for the retweet
                Statement stmt = con.createStatement();
                ResultSet rs2 = stmt.executeQuery("SELECT MAX(post_id) AS max_id FROM post");
                int nextPostId = 1;
                if (rs2.next()) {
                    nextPostId = rs2.getInt("max_id") + 1;
                }

                // Insert the retweet into the post table
                String insertPostSql = "INSERT INTO posts(post_id, user_id, content) VALUES (?, ?, ?)";
                PreparedStatement insertPostPstmt = con.prepareStatement(insertPostSql);
                insertPostPstmt.setInt(1, nextPostId);
                insertPostPstmt.setString(2, userId);
                insertPostPstmt.setString(3, content + " (Retweet by " + userId + ": " + additionalComment + ")");
                insertPostPstmt.executeUpdate();

                System.out.println("Post retweeted successfully with your additional comment!");
            } else {
                String updateSql = "UPDATE retweet SET user_id = ?, post_id = ?, content = ?, additional_comment = ? WHERE retweet_id = ?";
                PreparedStatement updatePstmt = con.prepareStatement(updateSql);
                updatePstmt.setString(1, userId);
                updatePstmt.setInt(2, postId);
                updatePstmt.setString(3, content);
                updatePstmt.setString(4, additionalComment);
                updatePstmt.setString(5, retweetId);
                updatePstmt.executeUpdate();

                System.out.println("Retweet updated successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
