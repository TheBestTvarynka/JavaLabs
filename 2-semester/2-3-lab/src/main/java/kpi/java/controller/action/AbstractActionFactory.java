package kpi.java.controller.action;

import kpi.java.controller.useractions.ActionType;
import kpi.java.exception.UnsupportedActionException;

public class AbstractActionFactory {
    public static Action getActionByNumber(String action) throws UnsupportedActionException {
        ActionType actionType;
        try {
            actionType = ActionType.valueOf(action);
        } catch (IllegalArgumentException e) {
            throw new UnsupportedActionException();
        }
        switch (actionType) {
            case LOGIN:
                return LoginAction.getAction();
            case REGISTER:
                return RegisterAction.getAction();
            case REQUEST:
                return RequestAction.getAction();
            case ORDER:
                return OrderAction.getAction();
            case BROWSE:
                return BrowseAction.getAction();
            default:
                throw new UnsupportedActionException();
        }
    }
}
