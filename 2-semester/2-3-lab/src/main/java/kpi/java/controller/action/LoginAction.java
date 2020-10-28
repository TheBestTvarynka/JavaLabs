package kpi.java.controller.action;

import kpi.java.dto.LoginDto;
import kpi.java.service.UserService;
import kpi.java.view.View;

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
        view.print(userService.login(new LoginDto(username, password)));
    }

    public static Action getAction() {
        if (action == null) {
            action = new LoginAction(UserService.getInstance());
        }
        return action;
    }
}
