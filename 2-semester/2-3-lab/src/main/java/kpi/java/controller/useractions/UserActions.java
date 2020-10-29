package kpi.java.controller.useractions;

import kpi.java.enums.UserType;

import java.util.HashMap;
import java.util.Map;

public class UserActions {
    public static Map<ActionType, String> getActions(UserType userType) {
        Map<ActionType, String> actions = new HashMap<>();
        actions.put(ActionType.EXIT, "close the program");
        switch (userType) {
            case USER:
                actions.put(ActionType.LOG_OUT, "log out from your account");
                actions.put(ActionType.REQUEST, "make a request for rooms");
                break;
//            case MANAGER:
//                break;
//            case ADMIN:
//                break;
            default:
                actions.put(ActionType.LOGIN, "login into system");
                actions.put(ActionType.REGISTER, "create new account");
                break;
        }
        return actions;
    }
}
