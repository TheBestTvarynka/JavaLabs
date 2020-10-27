package kpi.java.service;

import kpi.java.dto.LoginDto;

public class UserService {
    private static UserService instance;

    private UserService() {
    }

    public String login(LoginDto credentials) {
        // log in
        return "Login success!";
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

}
