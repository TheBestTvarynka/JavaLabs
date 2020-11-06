package kpi.java.controller.action;

import kpi.java.dto.CreateRequestDto;
import kpi.java.enums.RoomType;
import kpi.java.exception.UnavailableException;
import kpi.java.service.RequestService;
import kpi.java.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RequestAction implements Action {
    private static Action action;

    private RequestService requestService;

    private RequestAction(RequestService service) {
        requestService = service;
    }

    @Override
    public void execute(View view) {
        while (true) {
            String phone = view.getAnswer("Enter your phone number:");
            String roomType = view.getAnswer("Enter room type: [room, vip, lux, president]");
            String seatNumber = view.getAnswer("Enter seat number:");
            String dateFrom = view.getAnswer("Enter date from: [dd/MM/yyyy]");
            String dateTo = view.getAnswer("Enter date to: [dd/MM/yyyy]");
            try {
                String res = requestService.createRequest(new CreateRequestDto(
                        Integer.parseInt(seatNumber),
                        RoomType.valueOf(roomType.toUpperCase()),
                        phone,
                        new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom),
                        new SimpleDateFormat("dd/MM/yyyy").parse(dateTo)
                ));
                view.print(res, "green");
                break;
            } catch(UnavailableException e) {
                view.error(e.getMessage());
            } catch(IllegalArgumentException e) {
                view.error("Wrong room type! Please, try again.");
            } catch(ParseException e) {
                view.error("Wrong date format! Please, try again.");
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
