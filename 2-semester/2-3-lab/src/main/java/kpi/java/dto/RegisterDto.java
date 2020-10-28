package kpi.java.dto;

import java.util.UUID;

public class RegisterDto {
    public UUID id;
    public String username;
    public String email;
    public String fullName;
    public String password;

    public RegisterDto(String username, String email, String fullName, String password) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
}
