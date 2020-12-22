package com.kpi.lab4.servlets.actions;

import com.kpi.lab4.dao.OrderDao;
import com.kpi.lab4.dao.RequestDao;
import com.kpi.lab4.dao.RoomDao;
import com.kpi.lab4.dao.UserDao;
import com.kpi.lab4.exception.NotFoundException;
import com.kpi.lab4.services.OrderService;
import com.kpi.lab4.services.RequestService;
import com.kpi.lab4.services.UserService;

public class ActionFactory {
    public static Action getAction(String path) {
        switch (path) {
            case "home":
                return new HomeAction();
            case "/register":
                return new RegisterAction(new UserService(new UserDao()));
            case "/login":
                return new LoginAction(new UserService(new UserDao()));
            case "/browse":
                return new BrowseAction(new RequestService(new RequestDao(), new RoomDao(), new OrderDao()));
            case "/request":
                return new RequestAction(new RequestService(new RequestDao(), new RoomDao(), new OrderDao()));
            case "/order":
                return new OrderAction(new OrderService(new OrderDao(), new RoomDao()));
            case "/logout":
                return new LogOutAction();
            case "/resolve":
                return new ResolveRequestAction();
            default:
                throw new NotFoundException("Request you are looking for not found");
        }
    }
}
