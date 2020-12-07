package com.kpi.lab4.servlets.actions;

import com.kpi.lab4.exception.UserAlreadyExistException;
import com.kpi.lab4.services.UserService;
import com.kpi.lab4.utils.RegisterDtoBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;

public class RegisterAction implements Action {
    private UserService userService;

    public RegisterAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET")) {
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        } else if (method.equals("POST")) {
            RegisterDtoBuilder builder = new RegisterDtoBuilder();

            Iterator<String> it = request.getParameterNames().asIterator();
            while (it.hasNext()) {
                String name = it.next();
                String[] values = request.getParameterValues(name);
                if (values.length == 0) {
                    return;
                }
                builder.set(name, values[0]);
            }
            try {
                userService.register(builder.build());
                context.setAttribute("message", "Register success. You can login now.");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            } catch (SQLException ignored) {
                System.out.println("SQL EXCEPTION");
                context.setAttribute("error", "Sorry, now we are temporary unavailable.");
                request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            } catch (UserAlreadyExistException e) {
                context.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
