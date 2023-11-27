import java.sql.*;

public class ShowFollowers {
    private Connection con;

    public ShowFollowers(Connection con) {
        this.con = con;
    }

    public void execute(String userID) {
        try {
            String query = "SELECT follower_id FROM follower WHERE follow_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();

            System.out.print(userID + " is follow : ");
            while (rs.next()) {
                System.out.println(rs.getString("follower_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
