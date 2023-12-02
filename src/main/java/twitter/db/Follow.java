package twitter.db;

import java.sql.*;

public class Follow {
    private Connection con;

    public Follow(Connection con) {
        this.con = con;
    }

    public boolean checkFollowing(String targetID, String searchID) {
        try {
            String sql = "SELECT * FROM following WHERE follow_id = ? AND following_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, targetID);
            pstmt.setString(2, searchID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void execute(String followID, String followerID) {
        try {

            if (checkFollowing(followID, followerID)) {
                unfollow(followID, followerID);
            } else {
                String insertSql = "INSERT INTO follower VALUES (?, ?)";
                PreparedStatement insertPstmt = con.prepareStatement(insertSql);
                insertPstmt.setString(1, followID); // �ȷο� �ϴ� ���
                insertPstmt.setString(2, followerID); // �ȷο� �޴� ���
                insertPstmt.executeUpdate();

                String insertSql2 = "INSERT INTO following VALUES (?, ?)";
                PreparedStatement insertPstmt2 = con.prepareStatement(insertSql2);
                insertPstmt2.setString(1, followerID); // �ȷο� �޴� ���
                insertPstmt2.setString(2, followID); // �ȷο� �ϴ� ���
                insertPstmt2.executeUpdate();

                System.out.println(followID + " is now follow " + followerID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void unfollow(String followID, String followerID) {
        try {
            String deleteSql = "DELETE FROM follower WHERE follow_id = ? AND follower_id = ?";
            PreparedStatement deletePstmt = con.prepareStatement(deleteSql);
            deletePstmt.setString(1, followID);
            deletePstmt.setString(2, followerID);
            deletePstmt.executeUpdate();

            String deleteSql_2 = "DELETE FROM following WHERE follow_id = ? AND following_id = ?";
            deletePstmt = con.prepareStatement(deleteSql_2);
            deletePstmt.setString(1, followerID);
            deletePstmt.setString(2, followID);
            deletePstmt.executeUpdate();
            System.out.println(followID + " has unfollowed " + followerID);
            System.out.println(followerID + " has unfollowing " + followID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}