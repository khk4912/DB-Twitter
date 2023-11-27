package twitter.db;

import java.sql.*;

public class ShowFollowing {
    private Connection con;

    public ShowFollowing(Connection con) {
        this.con = con;
    }

    public void execute(String userID) {
        try {
            String query = "SELECT following_id FROM following WHERE follow_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();

            System.out.print("People who are following " + userID + ":");
            while (rs.next()) {
                System.out.print(rs.getString("following_id "));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
