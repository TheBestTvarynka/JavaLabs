package com.kpi.lab4.services;

import com.kpi.lab4.exception.BadCredentialsException;
import com.kpi.lab4.dao.UserDao;
import com.kpi.lab4.dto.LoginDto;
import com.kpi.lab4.dto.RegisterDto;
import com.kpi.lab4.entities.User;
import com.kpi.lab4.exception.UserAlreadyExistException;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private final UserDao repository;

    public UserService(UserDao userDao) {
        repository = userDao;
    }

    public User login(LoginDto credentials) throws BadCredentialsException, SQLException {
        Optional<User> user = repository.findByUsername(credentials.getUsername());

        if (user.isPresent()) {
            User userData = user.get();
            if (userData.getPassword().equals(credentials.getPassword())) {
                return userData;
            }
        }
        return null;
    }

    public void register(RegisterDto registerData) throws SQLException {
        if (repository.findByUsername(registerData.getUsername()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        repository.save(User.fromRegisterData(registerData));
    }
}
