package kpi.java.service;

import kpi.java.controller.UserAuthData;
import kpi.java.exception.BadCredentialsException;
import kpi.java.exception.UnavailableException;
import kpi.java.utils.SimpleConnectionPool;
import kpi.java.dao.UserDao;
import kpi.java.dto.LoginDto;
import kpi.java.dto.RegisterDto;
import kpi.java.entity.User;
import kpi.java.exception.UserAlreadyExistException;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private static UserService instance;

    private final UserDao repository;

    private UserService() {
        repository = new UserDao();
    }

    public String login(LoginDto credentials) throws BadCredentialsException, UnavailableException {
        Optional<User> user;
        try {
            repository.setConnection(SimpleConnectionPool.getPool().getConnection());
            user = repository.findByUsername(credentials.username);
            SimpleConnectionPool.getPool().releaseConnection(repository.releaseConnection());
        } catch(SQLException e) {
            throw new UnavailableException();
        }
        if (user.isPresent()) {
            User userData = user.get();
            if (userData.getPassword().equals(credentials.password)) {
                UserAuthData.setAuthData(userData.getId(), userData.getUsername(), userData.getUserType());
                return "Login success!";
            }
            throw new BadCredentialsException();
        }
        throw new BadCredentialsException();
    }

    public String register(RegisterDto registerData) throws UserAlreadyExistException, UnavailableException {
        try {
            repository.setConnection(SimpleConnectionPool.getPool().getConnection());
            if (repository.findByUsername(registerData.username).isPresent()) {
                throw new UserAlreadyExistException();
            }
            repository.save(User.fromRegisterData(registerData));
            SimpleConnectionPool.getPool().releaseConnection(repository.releaseConnection());
        } catch(SQLException e) {
            throw new UnavailableException();
        }
        return "Register success!";
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

}
