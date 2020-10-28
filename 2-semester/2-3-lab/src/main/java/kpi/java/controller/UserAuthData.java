package kpi.java.controller;

import kpi.java.emun.UserType;

import java.util.UUID;

public class UserAuthData {
    private static UserAuthData userAuthData = new UserAuthData();
    private UUID id;
    private String username = "guest";
    private UserType role = UserType.NONE;

    private UserAuthData() {
    }

    private UserAuthData(UUID id, String username, UserType role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public UserType getRole() {
        return role;
    }

    public static void setAuthData(UUID id, String username, UserType role) {
        userAuthData = new UserAuthData(id, username, role);
    }

    public static UserAuthData getAuthData() {
        return userAuthData;
    }
}
