package com.kpi.lab4.services;

import com.kpi.lab4.dao.UserDao;
import com.kpi.lab4.dto.LoginDto;
import com.kpi.lab4.dto.RegisterDto;
import com.kpi.lab4.entities.User;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.exception.UserAlreadyExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private static Logger logger = LogManager.getLogger(UserService.class);
    private final UserDao repository;

    public UserService(UserDao userDao) {
        repository = userDao;
    }

    public User login(LoginDto credentials) throws UnavailableException {
        Optional<User> user;
        try {
            user = repository.findByUsername(credentials.getUsername());
        } catch(SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            throw new UnavailableException();
        }

        if (user.isPresent() && user.get().getPassword().equals(credentials.getPassword())) {
            return user.get();
        }
        return null;
    }

    public void register(RegisterDto registerData) throws UnavailableException {
        try {
            if (repository.findByUsername(registerData.getUsername()).isPresent()) {
                throw new UserAlreadyExistException();
            }
            repository.save(User.fromRegisterData(registerData));
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            throw new UnavailableException();
        }
    }
}
