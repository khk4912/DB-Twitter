package team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostCommentViewer {

    private Connection con;
    private int post_id; // 선택한 포스트의 ID

    public PostCommentViewer(Connection con, int post_id) {
        this.con = con;
        this.post_id = post_id;
    }

    public void viewComments() throws SQLException {
        // 포스트에 대한 댓글을 가져오는 쿼리
        String commentQuery = "SELECT * FROM comment WHERE post_id = ? ORDER BY update_date DESC";

        try (PreparedStatement pstmt = con.prepareStatement(commentQuery)) {
            pstmt.setInt(1, post_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Comment ID User_ID Content update Date");
                while (rs.next()) {
                    int commentId = rs.getInt("comment_id");
                    int userId = rs.getInt("user_id_writer");
                    String content = rs.getString("content");
                    String updateDate = rs.getString("update_date");

                    System.out.printf("%d %d %s %s\n", commentId, userId, content, updateDate);

                    // 해당 댓글의 대댓글을 가져오는 쿼리
                    viewReplies(commentId);
                }
            }
        }
    }

    private void viewReplies(int commentId) throws SQLException {
        // 대댓글을 가져오는 쿼리
        String replyQuery = "SELECT * FROM child_comment WHERE comment_id = ? ORDER BY update_date DESC";

        try (PreparedStatement pstmt = con.prepareStatement(replyQuery)) {
            pstmt.setInt(1, commentId);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Child Comment ID User_ID Content update Date");
                while (rs.next()) {
                    int childCommentId = rs.getInt("child_comment_id");
                    int userId = rs.getInt("user_id_writer");
                    String content = rs.getString("content");
                    String updateDate = rs.getString("update_date");
                    int ccm_like=rs.getInt("ccm_like");
                    System.out.printf("%d %d %s %s\n", childCommentId, userId, content, updateDate);
                }
            }
        }
    }

    // Add other methods if necessary
}

