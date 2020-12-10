package com.kpi.lab4.servlets;

import com.kpi.lab4.services.OrderService;
import com.kpi.lab4.services.RequestService;
import com.kpi.lab4.services.UserService;
import com.kpi.lab4.servlets.actions.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MainServlet", urlPatterns = {"/home", "/browse", "/order", "/register", "/login", "/request","/logout"})
public class MainServlet extends HttpServlet {
    private Map<String, Action> actions;

    @Override
    public void init(ServletConfig config) throws ServletException {
        actions = new HashMap<>();
        actions.put("/home", new HomeAction());
        actions.put("/register", new RegisterAction(new UserService()));
        actions.put("/login", new LoginAction(new UserService()));
        actions.put("/browse", new BrowseAction(new RequestService()));
        actions.put("/request", new RequestAction(new RequestService()));
        actions.put("/order", new OrderAction(new OrderService()));
        actions.put("/logout", new LogOutAction());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        Action action = actions.get(path);
        if (action != null) {
            action.execute(req, resp, req.getServletContext());
        } else {
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
