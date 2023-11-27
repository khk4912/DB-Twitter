package twitter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BlockHandler {
    private Connection con;
    private Scanner sc;
    private String user_id;

    public BlockHandler(Connection con, Scanner sc, String user_id) {
        this.con = con;
        this.sc = sc;
        this.user_id = user_id;
    }

    public void addBlockWithType() {
        try {
            String rub = sc.nextLine();
            String user_id_blocked = null;
            System.out.println("Enter block only user 0, only post 1, both 2");
            int op3 = sc.nextInt();
            sc.nextLine();

            String s6 = null;

            if (op3 == 0) {
                System.out.println("Enter block_id, user_id_blocked");
                String block_id = sc.next();
                user_id_blocked = sc.next();
                sc.nextLine();
                s6 = "insert into block(block_id,user_id,user_id_blocked) values('" + block_id + "','" + user_id + "','"
                        + user_id_blocked + "')";
            } else if (op3 == 1) {
                System.out.println("Enter block_id, post_id_blocked");
                String block_id = sc.next();
                String post_id_blocked = sc.next();
                sc.nextLine();
                s6 = "insert into block(block_id,user_id,post_id_blocked) values('" + block_id + "','" + user_id + "','"
                        + post_id_blocked + "')";
            } else if (op3 == 2) {
                System.out.println("Enter block_id, user_id_blocked, post_id_blocked");
                String block_id = sc.next();
                user_id_blocked = sc.next();
                String post_id_blocked = sc.next();
                sc.nextLine();
                s6 = "insert into block values('" + block_id + "','" + user_id + "','" + post_id_blocked + "','"
                        + user_id_blocked + "')";
            }

            PreparedStatement pstm = con.prepareStatement(s6);
            pstm.executeUpdate();

            if (op3 == 0 || op3 == 2) {
                Statement stmt = con.createStatement();
                String s2 = "select user_id_following from following where user_id_following=\"" + user_id_blocked
                        + "\" and user_id=\"" + user_id + "\"";
                ResultSet rs = stmt.executeQuery(s2);

                // 내가 블락하려는 상대를 팔로잉 하는 경우
                if (rs.next()) {
                    String s7 = "delete from following where user_id_following=\"" + user_id_blocked
                            + "\" and user_id=\"" + user_id + "\"";
                    String s8 = "delete from follower where user_id_follower=\"" + user_id + "\" and user_id=\""
                            + user_id_blocked + "\"";

                    // 위의 두 경우를 팔로잉, 팔로워 테이블에서 삭제
                    PreparedStatement pstm7 = con.prepareStatement(s7);
                    pstm7.executeUpdate();

                    PreparedStatement pstm8 = con.prepareStatement(s8);
                    pstm8.executeUpdate();
                }

                String s3 = "select user_id_following from following where user_id_following=\"" + user_id
                        + "\" and user_id=\"" + user_id_blocked + "\"";
                rs = stmt.executeQuery(s3);

                // 블락하려는 상대가 나를 팔로잉 하는 경우
                if (rs.next()) {
                    String s9 = "delete from following where user_id_following=\"" + user_id + "\" and user_id=\""
                            + user_id_blocked + "\"";
                    String s10 = "delete from follower where user_id_follower=\"" + user_id_blocked
                            + "\" and user_id=\"" + user_id + "\"";

                    // 위의 두 경우를 팔로잉, 팔로워 테이블에서 삭제
                    PreparedStatement pstm9 = con.prepareStatement(s9);
                    pstm9.executeUpdate();

                    PreparedStatement pstm10 = con.prepareStatement(s10);
                    pstm10.executeUpdate();
                }
            }

            System.out.println("Block added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
