package kpi.java.controller.action;

import kpi.java.entity.Room;
import kpi.java.exception.UnavailableException;
import kpi.java.service.RequestService;
import kpi.java.utils.Formatter;
import kpi.java.utils.SelectRoomOptions;
import kpi.java.view.View;

import java.sql.SQLException;
import java.util.List;

public class BrowseAction implements Action {
    private static Action action;

    private RequestService requestService;

    private BrowseAction(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void execute(View view) {
        String option;
        String param;
        SelectRoomOptions options = new SelectRoomOptions();
        while (true) {
            option = view.getAnswer("Select an option: [price, seats, status, type] done - for rooms selection");
            if (option.equals("price") || option.equals("seats")) {
                try {
                    param = view.getAnswer("Set order for " + option + ": [asc, desc, none]");
                    options.setOrder(option, param);
                } catch (IllegalArgumentException e) {
                    view.error(e.getMessage());
                }
            } else if (option.equals("status")) {
                param = view.getAnswer("Set status filter: [available, booked, repair]");
                try {
                    options.setStatus(param);
                } catch (IllegalArgumentException e) {
                    view.error(e.getMessage());
                }
            } else if (option.equals("type")) {
                param = view.getAnswer("Set type filter: [room, vip, lux, president]");
                try {
                    options.setType(param);
                } catch (IllegalArgumentException e) {
                    view.error(e.getMessage());
                }
            } else if (option.equals("done")) {
                try {
                    List<Room> rooms = requestService.selectRooms(options);
                    view.print(Formatter.formatRooms(rooms));
                } catch (UnavailableException | IllegalArgumentException e) {
                    view.error("Sorry, we are temporary unavailable. Please, try later.");
                }
                String ans = view.getAnswer("Continue browsing? [con/exit]");
                if (ans.equals("exit")) {
                    break;
                }
            } else {
                view.error("Wrong option! Please, try again.");
            }
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new BrowseAction(RequestService.getInstance());
        }
        return action;
    }
}
