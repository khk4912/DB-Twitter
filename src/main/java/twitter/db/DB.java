package twitter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private Connection con;

    /**
     * Connection을 만들어 static 변수 con에 대입.
     * 
     * @param userName DB 사용자 이름
     * @param password 비밀번호
     * @param DBName   DB 이름
     */
    public Connection makeConnection(String userName, String password, String DBName) {
        // try {
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // } catch (ClassNotFoundException e) {
        // System.out.println("makeConnection(): " + e.getMessage());
        // return false;
        // }

        String url = "jdbc:mysql://localhost:3306/" + DBName;
        try {
            con = DriverManager.getConnection(url, userName, password);
            return con;
        } catch (SQLException e) {
            System.out.println("makeConnection(): " + e.getMessage());
            return null;
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("closeConnection(): " + e.getMessage());
        }
    }

    public Connection getConnection() {
        if (con == null)
            System.out.println("Connection is not set yet!");

        return con;
    }

}
