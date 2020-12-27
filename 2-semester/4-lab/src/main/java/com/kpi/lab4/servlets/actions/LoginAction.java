package com.kpi.lab4.servlets.actions;

import com.kpi.lab4.dto.LoginDto;
import com.kpi.lab4.entities.User;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction implements Action {
    private UserService service;

    public LoginAction(UserService userService) {
        this.service = userService;
    }

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            User user = service.login(new LoginDto(
                    request.getParameter("username"),
                    request.getParameter("password")
            ));
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getUserType());
                // 7200 = 60 * 60 * 2 = 2 hours
                session.setMaxInactiveInterval(7200);
                response.sendRedirect(request.getContextPath() + "/request");
            } else {
                request.setAttribute("error", "Incorrect username or password.");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        } catch (UnavailableException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } catch (IndexOutOfBoundsException e) {
            request.setAttribute("error", "Incorrect username or password.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
