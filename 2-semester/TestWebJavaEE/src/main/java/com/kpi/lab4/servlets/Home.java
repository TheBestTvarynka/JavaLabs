package com.kpi.lab4.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Home", urlPatterns = "/home")
public class Home extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/plain");
        try {
            res.getWriter().println("some servlet text");
        } catch(IOException ignored) {
        }
    }
}
