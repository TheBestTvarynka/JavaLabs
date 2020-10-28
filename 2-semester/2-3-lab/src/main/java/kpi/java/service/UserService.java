package kpi.java.service;

import kpi.java.controller.UserAuthData;
import kpi.java.dao.UserDao;
import kpi.java.dto.LoginDto;
import kpi.java.emun.UserType;

import java.util.UUID;

public class UserService {
    private static UserService instance;
    private final UserDao repository;

    private UserService() {
        repository = new UserDao();
    }

    public String login(LoginDto credentials) {
        // log in
        repository.findByUsername(credentials.username);
        UserAuthData.setAuthData(UUID.randomUUID(), "pasha", UserType.USER);
        return "Login success!";
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

}
