import java.sql.*;

public class HashTag {
    private Connection con;

    public HashTag(Connection con) {
        this.con = con;
    }

    public void execute(String postId, String tag) {
        try {
            String checkSql = "SELECT * FROM hashtag WHERE post_id = ? AND tag = ?";
            PreparedStatement checkPstmt = con.prepareStatement(checkSql);
            checkPstmt.setString(1, postId);
            checkPstmt.setString(2, tag);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                String insertSql = "INSERT INTO hashtag (post_id, tag) VALUES (?, ?)";
                PreparedStatement insertPstmt = con.prepareStatement(insertSql);
                insertPstmt.setString(1, postId);
                insertPstmt.setString(2, tag);
                insertPstmt.executeUpdate();

                System.out.println("Post " + postId + " is now tagged with " + tag);
            } else {
                removeTag(postId, tag);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeTag(String postId, String tag) {
        try {
            String deleteSql = "DELETE FROM hashtag WHERE post_id = ? AND tag = ?";
            PreparedStatement deletePstmt = con.prepareStatement(deleteSql);
            deletePstmt.setString(1, postId);
            deletePstmt.setString(2, tag);
            deletePstmt.executeUpdate();

            System.out.println("Hashtag " + tag + " has been removed from post " + postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
