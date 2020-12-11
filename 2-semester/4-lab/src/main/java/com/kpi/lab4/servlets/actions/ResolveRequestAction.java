package com.kpi.lab4.servlets.actions;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResolveRequestAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().println("Welcome! This is resolve request page.");
    }
}
