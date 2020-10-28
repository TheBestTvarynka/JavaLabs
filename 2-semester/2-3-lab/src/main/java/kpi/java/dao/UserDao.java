package kpi.java.dao;

import kpi.java.entity.User;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class UserDao {
    final String findByUsername = "select * from users where username=?";
    final String addNewUser = "insert into users (id, username, password, full_name, email) values (?, ?, ?, ?, ?)";
    final String updateUser = "update users set id=?, username=?, password=?, full_name=?, email=? where id=?";

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection releaseConnection() {
        Connection connection = this.connection;
        this.connection = null;
        return connection;
    }

    public Optional<User> findByUsername(String username) {
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(findByUsername);
            pstmt.setString(1, username);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                user = User.builder()
                        .id(UUID.fromString(res.getString("id")))
                        .email(res.getString("email"))
                        .username(res.getString("username"))
                        .password(res.getString("password"))
                        .fullName(res.getString("full_name"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user == null ? Optional.empty() : Optional.of(user);
    }

    public void save(User user) {
        PreparedStatement pstmt = null;
        if (user.getId() == null) {
            // create
            user.setId(UUID.randomUUID());
            try {
                pstmt = connection.prepareStatement(addNewUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // update
            try {
                pstmt = connection.prepareStatement(updateUser);
                pstmt.setString(6, user.getId().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            pstmt.setObject(1, user.getId(), java.sql.Types.OTHER);
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getFullName());
            pstmt.setString(5, user.getEmail());
            pstmt.execute();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
