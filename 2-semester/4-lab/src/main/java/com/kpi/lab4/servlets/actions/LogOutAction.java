package com.kpi.lab4.servlets.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutAction implements Action {
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
