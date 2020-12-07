package com.kpi.lab4.servlets.actions;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }
}
