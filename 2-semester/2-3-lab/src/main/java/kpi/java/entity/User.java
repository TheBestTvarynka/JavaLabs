package kpi.java.entity;

import kpi.java.dto.RegisterDto;
import kpi.java.enums.UserType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private UserType userType;

    public static User fromRegisterData(RegisterDto data) {
        return User.builder()
                .id(data.id)
                .username(data.username)
                .password(data.password)
                .fullName(data.fullName)
                .email(data.email)
                .userType(UserType.USER)
                .build();
    }
}
