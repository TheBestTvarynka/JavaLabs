package kpi.java.entity;

import kpi.java.dto.RegisterDto;
import kpi.java.enums.UserType;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private UserType userType;

    public User(UUID id, String username, String password, String fullName, String email, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.userType = userType;
    }

    public static User fromRegisterData(RegisterDto data) {
        return new User(
                data.getId(),
                data.getUsername(),
                data.getPassword(),
                data.getFullName(),
                data.getEmail(),
                UserType.USER
        );
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }
}
