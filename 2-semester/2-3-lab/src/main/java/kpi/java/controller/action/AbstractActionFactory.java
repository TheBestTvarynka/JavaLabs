package kpi.java.controller.action;

import kpi.java.exception.UnsupportedActionException;

public class AbstractActionFactory {
    public static Action getActionByNumber(String number) throws UnsupportedActionException {
        int action;
        try {
            action = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new UnsupportedActionException();
        }
        switch (action) {
            case 0:
                return LoginAction.getAction();
            case 1: //
                break;
            default:
                throw new UnsupportedActionException();
        }
        return LoginAction.getAction();
    }
}
