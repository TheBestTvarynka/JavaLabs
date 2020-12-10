package com.kpi.lab4.servlets.actions;

import com.kpi.lab4.exception.AlreadyBookedException;
import com.kpi.lab4.exception.BookNotFoundException;
import com.kpi.lab4.services.OrderService;
import com.kpi.lab4.utils.CreateOrderDtoBuilder;
import com.kpi.lab4.utils.CreateRequestDtoBuilder;

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
            while (it.hasNext()) {
                String name = it.next();
                String[] values = request.getParameterValues(name);
                builder.set(name, values[0]);
            }
            System.out.println(builder.build());
            try {
                service.bookRoom(builder.build());
                context.setAttribute("message", "All success! You have two days to pay for the order.");
            } catch(SQLException | IllegalArgumentException e) {
                context.setAttribute("error", "Sorry, now we are temporary unavailable.");
            } catch (AlreadyBookedException | BookNotFoundException e) {
                context.setAttribute("error", e.getMessage());
            }
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
    }
}
