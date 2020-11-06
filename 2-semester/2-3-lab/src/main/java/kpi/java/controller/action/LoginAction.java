package kpi.java.controller.action;

import kpi.java.dto.LoginDto;
import kpi.java.exception.BadCredentialsException;
import kpi.java.service.UserService;
import kpi.java.view.View;

import java.sql.SQLException;

public class LoginAction implements Action {
    private static Action action;

    UserService userService;

    private LoginAction(UserService service) {
        this.userService = service;
    }

    @Override
    public void execute(View view) {
        String username = view.getAnswer("Type a username:");
        String password = view.getAnswer("Type a password:");
        try {
            view.print(userService.login(new LoginDto(username, password)), "green");
        } catch (BadCredentialsException e) {
            view.error(e.getMessage());
        } catch (SQLException ignored) {
            view.error("Sorry, we are temporary unavailable. Please, try later.");
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new LoginAction(UserService.getInstance());
        }
        return action;
    }
}
