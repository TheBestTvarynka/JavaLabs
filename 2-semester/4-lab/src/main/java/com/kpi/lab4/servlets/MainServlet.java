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

@WebServlet(name = "MainServlet", urlPatterns = {
        "/home", "/browse", "/order", "/register", "/login", "/request", "/logout", "/resolve"
})
public class MainServlet extends HttpServlet {
    private Map<String, Action> actions;

    @Override
    public void init(ServletConfig config) throws ServletException {
        UserService userService = new UserService();
        RequestService requestService = new RequestService();
        OrderService orderService = new OrderService();
        actions = new HashMap<>();
        actions.put("/home", new HomeAction());
        actions.put("/register", new RegisterAction(userService));
        actions.put("/login", new LoginAction(userService));
        actions.put("/browse", new BrowseAction(requestService));
        actions.put("/request", new RequestAction(requestService));
        actions.put("/order", new OrderAction(orderService));
        actions.put("/logout", new LogOutAction());
        actions.put("/resolve", new ResolveRequestAction());
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
