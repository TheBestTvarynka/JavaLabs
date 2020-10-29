package kpi.java.controller.action;

import kpi.java.dto.CreateOrderDto;
import kpi.java.entity.Room;
import kpi.java.service.RequestService;
import kpi.java.utils.SelectRoomOptions;
import kpi.java.view.View;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RequestAction implements Action {
    private static RequestAction action;

    private RequestService requestService;

    private RequestAction(RequestService service) {
        requestService = service;
    }

    @Override
    public void execute(View view) {
        view.print("First, you should to select the room(s).");
        view.print("You can set filters for room type and status, specify sorting.");
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
                    view.print(e.getMessage());
                }
            } else if (option.equals("status")) {
                param = view.getAnswer("Set status filter: [available, booked, repair]");
                try {
                    options.setStatus(param);
                } catch (IllegalArgumentException e) {
                    view.print(e.getMessage());
                }
            } else if (option.equals("type")) {
                param = view.getAnswer("Set type filter: [room, vip, lux, president]");
                try {
                    options.setType(param);
                } catch (IllegalArgumentException e) {
                    view.print(e.getMessage());
                }
            } else if (option.equals("done")) {
                try {
                    // need to print as table or other pretty view
                    List<Room> rooms = requestService.selectRooms(options);
                    for (Room room : rooms) {
                        System.out.println(room);
                    }
                    view.print("Request - you specify parameters for room and we choose most suitable variant for you.");
                    view.print("Order - you tell us what rooms (concrete numbers) you want to book");
                    String ans = view.getAnswer("What you would like to do? [request/order]");
                    if (ans.equals("order")) {
                        makeOrder(view);
                    } else if (ans.equals("request")) {
                        makeRequest(view);
                    }
                } catch (SQLException e) {
                    view.print(e.getMessage());
                }
                break;
            } else {
                view.print("Wrong option! Please, try again.");
            }
        }
    }

    private void makeRequest(View view) {

    }

    private void makeOrder(View view) {
        while (true) {
            String roomNumber = view.getAnswer("Enter room number:");
            String dateFrom = view.getAnswer("Enter date from: [dd/MM/yyyy]");
            String dateTo = view.getAnswer("Enter date to: [dd/MM/yyyy]");
            try {
                String res = requestService.bookRoom(new CreateOrderDto(
                        new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom),
                        new SimpleDateFormat("dd/MM/yyyy").parse(dateTo),
                        roomNumber
                ));
                view.print(res);
                break;
            } catch (SQLException | IllegalArgumentException | ParseException e) {
                view.print(e.getMessage());
            }
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new RequestAction(RequestService.getInstance());
        }
        return action;
    }
}
