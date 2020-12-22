package com.kpi.lab4.servlets;

import com.kpi.lab4.exception.NotFoundException;
import com.kpi.lab4.servlets.actions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = {
        "/home", "/browse", "/order", "/register", "/login", "/request", "/logout", "/resolve"
})
public class MainServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ActionFactory.getAction(req.getServletPath()).execute(req, resp, req.getServletContext());
        } catch(NotFoundException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
