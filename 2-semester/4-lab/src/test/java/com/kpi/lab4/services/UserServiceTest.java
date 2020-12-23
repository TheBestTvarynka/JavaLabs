package com.kpi.lab4.services;

import com.kpi.lab4.dao.UserDao;
import com.kpi.lab4.dto.LoginDto;
import com.kpi.lab4.dto.RegisterDto;
import com.kpi.lab4.entities.User;
import com.kpi.lab4.enums.UserType;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.exception.UserAlreadyExistException;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserDao userDao;

    @Before
    public void before() {
        userDao = mock(UserDao.class);
    }

    @Test
    public void whenLoginWithBadUsername_thenReturnNull() throws SQLException {
        LoginDto credentials = new LoginDto("test", "test123");
        when(userDao.findByUsername(credentials.getUsername())).thenReturn(Optional.empty());

        UserService service = new UserService(userDao);
        assertNull(service.login(credentials));
        verify(userDao).findByUsername(credentials.getUsername());
    }

    @Test
    public void whenLoginWithBadPassword_thenReturnNull() throws SQLException {
        LoginDto credentials = new LoginDto("test", "test123");
        when(userDao.findByUsername(credentials.getUsername())).thenReturn(Optional.of(new User(
                UUID.randomUUID(), "test", "test1234", "Test User", "test@gmail.com", UserType.USER
        )));

        UserService service = new UserService(userDao);
        assertNull(service.login(credentials));
        verify(userDao).findByUsername(credentials.getUsername());
    }

    @Test
    public void whenLogin_thenReturnUser() throws SQLException {
        LoginDto credentials = new LoginDto("test", "test123");
        User testUser = new User(
                UUID.randomUUID(), "test", "test123", "Test User", "test@gmail.com", UserType.USER
        );
        when(userDao.findByUsername(credentials.getUsername())).thenReturn(Optional.of(testUser));

        User user = new UserService(userDao).login(credentials);
        assertNotNull(user);
        assertEquals(user, testUser);
        verify(userDao).findByUsername(credentials.getUsername());
    }

    @Test(expected = UnavailableException.class)
    public void whenLogin_thenThrowUnavailableException() throws SQLException {
        LoginDto credentials = new LoginDto("test", "test123");
        doThrow(new UnavailableException()).when(userDao).findByUsername("test");

        new UserService(userDao).login(credentials);
    }

    @Test(expected = UserAlreadyExistException.class)
    public void whenRegister_thenThrowUserAlreadyExistException() throws SQLException {
        RegisterDto registerDto = new RegisterDto("test", "test@gmail.com", "Test User", "test123");
        when(userDao.findByUsername(registerDto.getUsername())).thenReturn(Optional.of(new User()));

        new UserService(userDao).register(registerDto);
    }

    @Test(expected = UnavailableException.class)
    public void whenRegister_thenThrowUnavailableException() throws SQLException {
        RegisterDto registerDto = new RegisterDto("test", "test@gmail.com", "Test User", "test123");
        User user = User.fromRegisterData(registerDto);
        doThrow(new SQLException()).when(userDao).save(user);

        new UserService(userDao).register(registerDto);
    }

    @Test
    public void whenRegister_thenSaveUser() throws SQLException {
        RegisterDto registerDto = new RegisterDto("test", "test@gmail.com", "Test User", "test123");
        User user = User.fromRegisterData(registerDto);
        when(userDao.findByUsername(registerDto.getUsername())).thenReturn(Optional.empty());

        new UserService(userDao).register(registerDto);
        verify(userDao).save(user);
    }

}
