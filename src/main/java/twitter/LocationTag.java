import java.sql.*;

public class LocationTag {
    private Connection con;

    public LocationTag(Connection con) {
        this.con = con;
    }

    public void tagLocation(String postId, String content) {
        try {
            String location = extractLocationFromContent(content);
            if (location == null) {
                System.out.println("No location tag found in the post content.");
                return;
            }

            String checkSql = "SELECT * FROM location WHERE post_id = ? AND location = ?";
            PreparedStatement checkPstmt = con.prepareStatement(checkSql);
            checkPstmt.setString(1, postId);
            checkPstmt.setString(2, location);
            ResultSet rs = checkPstmt.executeQuery();

            if (!rs.next()) {
                String insertSql = "INSERT INTO location (post_id, location) VALUES (?, ?)";
                PreparedStatement insertPstmt = con.prepareStatement(insertSql);
                insertPstmt.setString(1, postId);
                insertPstmt.setString(2, location);
                insertPstmt.executeUpdate();

                System.out.println("Post " + postId + " is now tagged with " + location);
            } else {
                removeTag(postId, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String extractLocationFromContent(String content) {
        int tagIndex = content.indexOf("<&>");
        if (tagIndex != -1 && tagIndex + 3 < content.length()) {
            return content.substring(tagIndex + 3);
        }
        return null;
    }

    private void removeTag(String postId, String location) {
        try {
            String deleteSql = "DELETE FROM location WHERE post_id = ? AND location = ?";
            PreparedStatement deletePstmt = con.prepareStatement(deleteSql);
            deletePstmt.setString(1, postId);
            deletePstmt.setString(2, location);
            deletePstmt.executeUpdate();

            System.out.println("Location " + location + " has been removed from post " + postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
