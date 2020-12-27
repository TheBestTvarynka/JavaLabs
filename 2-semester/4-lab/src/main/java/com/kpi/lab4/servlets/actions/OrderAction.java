package com.kpi.lab4.servlets.actions;

import com.kpi.lab4.dto.CreateOrderDto;
import com.kpi.lab4.exception.AlreadyBookedException;
import com.kpi.lab4.exception.BookNotFoundException;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.services.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderAction implements Action {
    private OrderService service;

    public OrderAction(OrderService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET")) {
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        } else if (method.equals("POST")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
            try {
                service.bookRoom(new CreateOrderDto(
                        dateFormat.parse(request.getParameter("dateFrom")),
                        dateFormat.parse(request.getParameter("dateTo")),
                        request.getParameter("roomNumber"),
                        request.getParameter("phone")
                ));
                request.setAttribute("message", "All success! You have two days to pay for the order.");
            } catch (AlreadyBookedException | BookNotFoundException | IllegalArgumentException | UnavailableException e) {
                request.setAttribute("error", e.getMessage());
            } catch (ParseException e) {
                request.setAttribute("error", "Wrong parameters for order!");
            }
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Method " + method + " not allowed!");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
