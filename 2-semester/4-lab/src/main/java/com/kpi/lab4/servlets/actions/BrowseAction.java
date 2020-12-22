package com.kpi.lab4.servlets.actions;

import com.kpi.lab4.dto.Page;
import com.kpi.lab4.entities.Room;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.services.RequestService;
import com.kpi.lab4.utils.SelectRoomOptions;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

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
        try {
            while (it.hasNext()) {
                String name = it.next();
                String[] values = request.getParameterValues(name);
                for (String value : values) {
                    options.set(name, value);
                }
            }
            Page<Room> page = service.selectRooms(options);
            request.setAttribute("page", page);
        } catch (IllegalArgumentException | UnavailableException e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/jsp/browse.jsp").forward(request, response);
    }
}
