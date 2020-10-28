package kpi.java.controller.useractions;

import kpi.java.emun.UserType;

import java.util.HashMap;
import java.util.Map;

public class UserActions {
    public static Map<ActionType, String> getActions(UserType userType) {
        Map<ActionType, String> actions = new HashMap<>();
        actions.put(ActionType.EXIT, "close the program");
        switch (userType) {
            case USER:
                actions.put(ActionType.LOG_OUT, "log out from your account");
                break;
//            case MANAGER:
//                break;
//            case ADMIN:
//                break;
            default:
                actions.put(ActionType.LOGIN, "login into system");
                actions.put(ActionType.REGISTER, "create new account");
                actions.put(ActionType.BROWSE, "search for rooms");
                break;
        }
        return actions;
    }
}
