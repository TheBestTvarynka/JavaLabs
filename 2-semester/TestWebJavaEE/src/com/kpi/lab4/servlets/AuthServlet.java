package com.kpi.lab4.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@WebServlet(name = "AuthServlet", urlPatterns = {"/login", "/register"})
public class AuthServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("In doPost()");
        Iterator<String> it = req.getParameterNames().asIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        res.setContentType("text/plain");
        res.getWriter().println("doPost");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path =req.getServletPath();
        if (path.equals("/login")) {
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
        } else if (path.equals("/register")) {
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, res);
        }
    }
}
