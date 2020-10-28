package kpi.java.dao;

import java.sql.*;

public class UserDao {
    String findByUsername = "select * from users where username=?";

    public void save() {

    }

    public void findByUsername(String username) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/java_lab", "postgres",
                    "postgres");
            PreparedStatement pstmt = connection.prepareStatement(findByUsername);
            pstmt.setString(1, username);
            ResultSet res = pstmt.executeQuery();
            System.out.println("result:");
            while (res.next()) {
                System.out.println(res.getString("id"));
                System.out.println(res.getString("username"));
                System.out.println(res.getString("password"));
                System.out.println(res.getString("email"));
                System.out.println(res.getString("full_name"));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

}
