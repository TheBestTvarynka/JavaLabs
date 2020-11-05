package kpi.java.controller.action;

import kpi.java.entity.Request;
import kpi.java.exception.NotFoundException;
import kpi.java.service.RequestService;
import kpi.java.utils.Formatter;
import kpi.java.view.View;

import java.sql.SQLException;
import java.util.List;

public class ResolveRequestAction implements Action {
    private static Action action;

    private RequestService requestService;

    private ResolveRequestAction(RequestService service) {
        this.requestService = service;
    }

    public void execute(View view) {
        List<Request> requests;
        while (true) {
            try {
                 requests = requestService.getAllRequests();
            } catch (SQLException | IllegalArgumentException ignored) {
                view.print("Service temporary unavailable.");
                return;
            }
            view.print(Formatter.formatRequests(requests));
            int rId;
            while (true) {
                String requestId = view.getAnswer("Enter request id:");
                try {
                    rId = Integer.parseInt(requestId);
                    if (rId >= 0 && rId < requests.size()) {
                        break;
                    } else {
                        view.error("Bad range for id. Please, enter correct id.");
                    }
                } catch (NumberFormatException e) {
                    view.error("id is number. Please, enter correct id.");
                }
            }
            view.print("Now you can browse all rooms. Search for more suitable)");
            BrowseAction.getAction().execute(view);
            String roomNumber = view.getAnswer("Enter room number:");
            try {
                view.print(requestService.resolveRequest(requests.get(rId).getId(), roomNumber), "green");
            } catch (SQLException ignored) {
                view.error("Service temporary unavailable. Please, ");
                return;
            } catch (NotFoundException e) {
                view.error(e.getMessage());
            }
            String ans = view.getAnswer("Continue resolving? [y/n]");
            if (ans.equals("n")) break;
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new ResolveRequestAction(RequestService.getInstance());
        }
        return action;
    }
}
