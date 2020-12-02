package kpi.java.controller;

import kpi.java.controller.action.ActionFactory;
import kpi.java.controller.action.Action;
import kpi.java.controller.useractions.ActionType;
import kpi.java.controller.useractions.UserActions;
import kpi.java.exception.UnsupportedActionException;
import kpi.java.view.View;
import kpi.java.view.ViewContext;

import java.util.Map;

public class GlobalController {
    View view;

    public GlobalController() {
        this.view = new ViewContext(System.in, System.out);
    }

    public void run() {
        Action action;
        UserAuthData user;
        while (true) {
            user = UserAuthData.getAuthData();
            Map<ActionType, String> actions = UserActions.getActions(user.getRole());
            actions.forEach((key, value) -> {
                view.print(key.toString().toLowerCase() + " - " + value);
            });
            String actionId = view.getAnswer(user.getUsername() + "> ");
            if (actionId.equals("exit")) {
                return;
            }
            try {
                ActionFactory.getActionByName(actionId.toUpperCase()).execute(view);
            } catch (UnsupportedActionException e) {
                view.error(e.getMessage());
                view.print("Please, try again");
            }
        }
    }

}
