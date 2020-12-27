package com.kpi.lab4.servlets;

import com.kpi.lab4.exception.NotFoundException;
import com.kpi.lab4.servlets.actions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static Logger logger = LogManager.getLogger(MainServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(req.getServletPath());
            String method = req.getMethod();
            switch (method) {
                case "GET":
                    action.get(req, resp);
                    break;
                case "POST":
                    action.post(req, resp);
                    break;
                default:
                    action.execute(req, resp);
                    break;
            }
        } catch(NotFoundException e) {
            logger.info("Action not found for path: " + req.getServletPath());
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
