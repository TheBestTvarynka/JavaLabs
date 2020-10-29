package kpi.java.controller.action;

import kpi.java.service.RequestService;
import kpi.java.view.View;

public class RequestAction implements Action {
    private static Action action;

    private RequestService requestService;

    private RequestAction(RequestService service) {
        requestService = service;
    }

    @Override
    public void execute(View view) {

    }

    public static Action getAction() {
        if (action == null) {
            action = new RequestAction(RequestService.getInstance());
        }
        return action;
    }
}
