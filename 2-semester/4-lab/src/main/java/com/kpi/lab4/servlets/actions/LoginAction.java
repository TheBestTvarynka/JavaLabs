package com.kpi.lab4.servlets.actions;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class LoginAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET")) {
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } else if (method.equals("POST")) {
            System.out.println("In doPost(): login");
            Iterator<String> it = request.getParameterNames().asIterator();
            while (it.hasNext()) {
                String name = it.next();
                System.out.println(name + " -> " + Arrays.toString(request.getParameterValues(name)));
            }
            response.setContentType("text/plain");
            response.getWriter().println("doPost");
        } else {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
