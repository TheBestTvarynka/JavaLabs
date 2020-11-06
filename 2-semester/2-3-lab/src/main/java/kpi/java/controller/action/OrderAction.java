package kpi.java.controller.action;

import kpi.java.dto.CreateOrderDto;
import kpi.java.exception.BookNotFoundException;
import kpi.java.exception.UnavailableException;
import kpi.java.service.OrderService;
import kpi.java.view.View;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderAction implements Action {
    private static Action action;

    private OrderService orderService;

    private OrderAction(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(View view) {
        while (true) {
            String phone = view.getAnswer("Enter your phone number:");
            String roomNumber = view.getAnswer("Enter room number:");
            String dateFrom = view.getAnswer("Enter date from: [dd/MM/yyyy]");
            String dateTo = view.getAnswer("Enter date to: [dd/MM/yyyy]");
            try {
                String res = orderService.bookRoom(new CreateOrderDto(
                        new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom),
                        new SimpleDateFormat("dd/MM/yyyy").parse(dateTo),
                        roomNumber,
                        phone
                ));
                view.print(res, "green");
                break;
            } catch (BookNotFoundException e) {
                view.error(e.getMessage());
            } catch(ParseException e) {
                view.error("Wrong date format! Please, try again.");
            } catch(SQLException | IllegalArgumentException ignored) {
                view.error("Sorry, we are temporary unavailable. Please, try later.");
            }
        }
    }

    public static Action getAction() {
        if (action == null) {
            action = new OrderAction(OrderService.getInstance());
        }
        return action;
    }
}
