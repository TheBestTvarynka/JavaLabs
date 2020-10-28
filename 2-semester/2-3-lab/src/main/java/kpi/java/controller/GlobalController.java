package kpi.java.controller;

import kpi.java.controller.action.AbstractActionFactory;
import kpi.java.controller.action.Action;
import kpi.java.controller.useractions.ActionType;
import kpi.java.controller.useractions.UserActions;
import kpi.java.exception.UnsupportedActionException;
import kpi.java.view.Menu;
import kpi.java.view.MenuContext;

import java.util.Map;

public class GlobalController {
    Menu menu;

    public GlobalController() {
        this.menu = new MenuContext(System.in, System.out);
    }

    public void run() {
        Action action;
        UserAuthData user;
        while (true) {
            user = UserAuthData.getAuthData();
            Map<ActionType, String> actions = UserActions.getActions(user.getRole());
            actions.forEach((key, value) -> {
                menu.print(key.toString().toLowerCase() + " - " + value);
            });
            String actionId = menu.getAnswer(user.getUsername() + "> ");
            if (actionId.equals("exit")) {
                return;
            }
            try {
                action = AbstractActionFactory.getActionByNumber(actionId.toUpperCase());
                action.execute(menu);
            } catch (UnsupportedActionException e) {
                menu.print(e.getMessage());
                menu.print("Please, try again");
            }
        }
    }

}
