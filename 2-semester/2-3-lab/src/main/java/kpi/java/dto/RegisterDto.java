package kpi.java.dto;

import java.util.UUID;

public class RegisterDto {
    private UUID id;
    private String username;
    private String email;
    private String fullName;
    private String password;

    public RegisterDto(String username, String email, String fullName, String password) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }
}
