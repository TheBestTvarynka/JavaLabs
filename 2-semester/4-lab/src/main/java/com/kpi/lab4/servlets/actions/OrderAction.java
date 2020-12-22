package com.kpi.lab4.servlets.actions;

import com.kpi.lab4.exception.AlreadyBookedException;
import com.kpi.lab4.exception.BookNotFoundException;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.services.OrderService;
import com.kpi.lab4.utils.builders.CreateOrderDtoBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

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
            CreateOrderDtoBuilder builder = new CreateOrderDtoBuilder();
            Iterator<String> it = request.getParameterNames().asIterator();
            try {
                while (it.hasNext()) {
                    String name = it.next();
                    String[] values = request.getParameterValues(name);
                    builder.set(name, values[0]);
                }
                service.bookRoom(builder.build());
                request.setAttribute("message", "All success! You have two days to pay for the order.");
            } catch (AlreadyBookedException | BookNotFoundException | IllegalArgumentException | UnavailableException e) {
                request.setAttribute("error", e.getMessage());
            }
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Method " + method + " not allowed!");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
