package com.kpi.lab4.servlets.actions;

import com.kpi.lab4.entities.Room;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.services.RequestService;
import com.kpi.lab4.utils.SelectRoomOptions;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BrowseAction implements Action {
    private RequestService service;

    public BrowseAction(RequestService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException, IOException {
        SelectRoomOptions options = new SelectRoomOptions();
        Iterator<String> it = request.getParameterNames().asIterator();
        while (it.hasNext()) {
            String name = it.next();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                options.set(name, value);
            }
        }
        try {
            List<Room> rooms = service.selectRooms(options);
            context.setAttribute("rooms", rooms);
        } catch (IllegalArgumentException ignored) {
            context.setAttribute("error", "Some parameters are incorrect!");
        } catch (UnavailableException ignored) {
            context.setAttribute("error", "Sorry, now we are temporary unavailable.");
        }
        request.getRequestDispatcher("/jsp/browse.jsp").forward(request, response);
    }
}
