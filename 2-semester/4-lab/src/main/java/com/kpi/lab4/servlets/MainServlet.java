package com.kpi.lab4.servlets;

import com.kpi.lab4.services.UserService;
import com.kpi.lab4.servlets.actions.Action;
import com.kpi.lab4.servlets.actions.HomeAction;
import com.kpi.lab4.servlets.actions.LoginAction;
import com.kpi.lab4.servlets.actions.RegisterAction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MainServlet", urlPatterns = {"/home", "/browse", "/order", "/register", "/login"})
public class MainServlet extends HttpServlet {
    private Map<String, Action> actions;

    @Override
    public void init(ServletConfig config) throws ServletException {
        actions = new HashMap<>();
        actions.put("/home", new HomeAction());
        actions.put("/register", new RegisterAction(new UserService()));
        actions.put("/login", new LoginAction());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("In service!");
        String path = req.getServletPath();
        Action action = actions.get(path);
        if (action != null) {
            action.execute(req, resp, req.getServletContext());
        } else {
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}