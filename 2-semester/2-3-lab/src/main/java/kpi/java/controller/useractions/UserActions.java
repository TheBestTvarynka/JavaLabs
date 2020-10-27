package kpi.java.controller.useractions;

import java.util.HashMap;
import java.util.Map;

public class UserActions {
    private Map<String, String> actions;

    public UserActions() {
        actions = new HashMap<>();
        actions.put("login", "login into system");
    }
}
