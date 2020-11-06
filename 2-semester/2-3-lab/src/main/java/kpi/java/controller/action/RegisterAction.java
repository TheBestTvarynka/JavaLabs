package kpi.java.controller.action;

import kpi.java.dto.RegisterDto;
import kpi.java.exception.BadEmailException;
import kpi.java.exception.BadUsernameException;
import kpi.java.exception.UnavailableException;
import kpi.java.exception.UserAlreadyExistException;
import kpi.java.service.UserService;
import kpi.java.utils.Validator;
import kpi.java.view.View;

import java.sql.SQLException;

public class RegisterAction implements Action {
    private static Action action;

    UserService userService;

    private RegisterAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(View view) {
        String username;
        String fullName;
        String email;
        String password;
        String repeatPassword;
        String result;
        view.print("Type '[cancel]' if you want to cancel registration.");
        while (true) {
            while (true) {
                try {
                    username = view.getAnswer("Type a username:");
                    if (username.equals("cancel")) {
                        return;
                    }
                    Validator.validateUsername(username);
                    break;
                } catch (BadUsernameException e) {
                    view.error(e.getMessage());
                }
            }
            fullName = view.getAnswer("Type your full name:");
            while (true) {
                try {
                    email = view.getAnswer("Type an email address:");
                    if (email.equals("cancel")) {
                        return;
                    }
                    Validator.validateEmail(email);
                    break;
                } catch (BadEmailException e) {
                    view.error(e.getMessage());
                }
            }
            while (true) {
                password = view.getAnswer("Type a password:");
                repeatPassword = view.getAnswer("Repeat a password:");
                if (password.equals(repeatPassword)) {
                    break;
                }
                view.error("Passwords are not equal");
            }
            try {
                result = userService.register(new RegisterDto(username, email, fullName, password));
                view.print(result, "green");
                break;
            } catch (UserAlreadyExistException e) {
                view.error(e.getMessage());
            } catch (SQLException ignored) {
                view.error("Sorry, we are temporary unavailable. Please, try later.");
            }
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new RegisterAction(UserService.getInstance());
        }
        return action;
    }
}
