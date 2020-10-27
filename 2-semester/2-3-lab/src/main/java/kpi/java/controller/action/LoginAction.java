package kpi.java.controller.action;

import kpi.java.dto.LoginDto;
import kpi.java.service.UserService;
import kpi.java.view.Menu;

public class LoginAction implements Action {
    private static Action action;

    UserService userService;

    private LoginAction(UserService service) {
        this.userService = service;
    }

    public void execute(Menu menu) {
        String username = menu.getAnswer("Type a username:");
        String password = menu.getAnswer("Type a password:");
        menu.print(userService.login(new LoginDto(username, password)));
    }

    public static Action getAction() {
        if (action == null) {
            action = new LoginAction(UserService.getInstance());
        }
        return action;
    }
}
