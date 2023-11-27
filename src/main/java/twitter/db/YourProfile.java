package team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.time.LocalDate;

public class YourProfile {
    private Connection con;
    private String user_id;

    // 생성자
    public YourProfile(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
    }

    // 생일 체크 메서드
    public void checkBirthday() throws SQLException {
        // 생일 체크 로직...
    	 System.out.println("Your profile!");
         LocalDate currentDate = LocalDate.now();
         String dateString = currentDate.toString();

         String sb = "SELECT birthday FROM User WHERE user_id=\"" + user_id + "\"";
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sb);

         if (rs.next()) {
             String birthday = rs.getString(1); // 테이블에 입력된 생일 가져오기
             if (dateString.equals(birthday)) // 현재와 날짜 비교
                 System.out.println("Happy birthday!");
         }
    }

    // 유저 정보 출력 메서드
    public void printUserInfo() throws SQLException {
        String query = "SELECT * FROM User WHERE user_id=\"" + user_id + "\"";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
       
			
		       if (rs.next()) {
            System.out.println("User Information:");

            String userId = rs.getString("user_id");
            String password = rs.getString("pwd");
            String nickname = rs.getString("nickname");
            Date accessDate = rs.getTimestamp("access_date");
            Date updateDate = rs.getTimestamp("update_date");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String gender = rs.getString("gender");
            String email = rs.getString("email");
            Date birthday = rs.getDate("birthday");
            Integer age = rs.getInt("age");
            String profileImage = rs.getString("profile_image");
            String introduce = rs.getString("introduce");
            String report = rs.getString("report");
            
            String s12="select count(*) from following where user_id=\""+user_id+"\"";
			stmt = con.createStatement();
			
			rs=stmt.executeQuery(s12);
			rs.next();
			String following_num=rs.getString(1);
			s12="select count(*) from follower where user_id=\""+user_id+"\"";
			stmt = con.createStatement();
			
			rs=stmt.executeQuery(s12);
			rs.next();
			String follower_num=rs.getString(1);
			System.out.printf("following num:%s, follower num:%s\n",following_num,follower_num);
			
            // 출력 형식은 필요에 따라 변경 가능
            System.out.println("User ID: " + userId);
            System.out.println("Password: " + password);
            System.out.println("Nickname: " + nickname);
            System.out.println("Access Date: " + accessDate);
            System.out.println("Update Date: " + updateDate);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Gender: " + gender);
            System.out.println("Email: " + email);
            System.out.println("Birthday: " + birthday);
            System.out.println("Age: " + age);
            System.out.println("Profile Image: " + profileImage);
            System.out.println("Introduce: " + introduce);
            System.out.println("Report: " + report);
        }
    }

    // 다른 메서드들...
}

