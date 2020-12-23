package com.kpi.lab4.entities;

import com.kpi.lab4.dto.RegisterDto;
import com.kpi.lab4.enums.UserType;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private UserType userType;

    public User() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(email, user.email) &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, fullName, email, userType);
    }
}
