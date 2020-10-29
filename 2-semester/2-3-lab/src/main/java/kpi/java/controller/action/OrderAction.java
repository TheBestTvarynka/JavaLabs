package kpi.java.controller.action;

import kpi.java.dto.CreateOrderDto;
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
            String roomNumber = view.getAnswer("Enter room number:");
            String dateFrom = view.getAnswer("Enter date from: [dd/MM/yyyy]");
            String dateTo = view.getAnswer("Enter date to: [dd/MM/yyyy]");
            try {
                String res = orderService.bookRoom(new CreateOrderDto(
                        new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom),
                        new SimpleDateFormat("dd/MM/yyyy").parse(dateTo),
                        roomNumber
                ));
                view.print(res);
                break;
            } catch (SQLException | IllegalArgumentException e) {
                view.print(e.getMessage());
            } catch(ParseException e) {
                view.print("Wrong date format! Please, try again.");
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
