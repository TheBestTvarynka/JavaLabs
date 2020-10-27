package kpi.java.controller;

import kpi.java.controller.action.AbstractActionFactory;
import kpi.java.controller.action.Action;
import kpi.java.exception.UnsupportedActionException;
import kpi.java.view.Menu;

public class GlobalController {
    Menu menu;

    public void run() {
        Action action;
        while (true) {
            menu.print("(Type 'exit' to close the program)");
            String actionId = menu.getAnswer("Choose an action:");
            if (actionId.equals("exit")) {
                return;
            }
            try {
                action = AbstractActionFactory.getActionByNumber(actionId);
                action.execute(menu);
            } catch (UnsupportedActionException e) {
                menu.print(e.getMessage());
                menu.print("Please, try again");
            }
        }
    }

}
