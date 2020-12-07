package com.kpi.lab4.services;

//import kpi.java.controller.UserAuthData;
import com.kpi.lab4.dao.SimpleConnectionPool;
import com.kpi.lab4.exception.BadCredentialsException;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.dao.UserDao;
import com.kpi.lab4.dto.LoginDto;
import com.kpi.lab4.dto.RegisterDto;
import com.kpi.lab4.entities.User;
import com.kpi.lab4.exception.UserAlreadyExistException;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private final UserDao repository;

    public UserService() {
        repository = new UserDao();
    }

    public User login(LoginDto credentials) throws BadCredentialsException, SQLException {
        Optional<User> user;
        repository.setConnection(SimpleConnectionPool.getPool().getConnection());
        user = repository.findByUsername(credentials.getUsername());
        SimpleConnectionPool.getPool().releaseConnection(repository.releaseConnection());

        if (user.isPresent()) {
            User userData = user.get();
            if (userData.getPassword().equals(credentials.getPassword())) {
                return userData;
            }
            return null;
//            throw new BadCredentialsException();
        }
//        throw new BadCredentialsException();
        return null;
    }

    public void register(RegisterDto registerData) throws SQLException {
        repository.setConnection(SimpleConnectionPool.getPool().getConnection());
        if (repository.findByUsername(registerData.getUsername()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        repository.save(User.fromRegisterData(registerData));
        SimpleConnectionPool.getPool().releaseConnection(repository.releaseConnection());
    }
}
