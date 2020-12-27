package com.kpi.lab4.servlets.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResolveRequestAction implements Action {
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().println("Welcome! This is resolve request page.");
    }
}
