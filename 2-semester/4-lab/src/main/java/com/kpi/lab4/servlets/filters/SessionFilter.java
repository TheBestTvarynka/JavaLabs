package com.kpi.lab4.servlets.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {
    private static Logger logger = LogManager.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            logger.info("User already logged in: " + session.getAttribute("username"));
            chain.doFilter(req, res);
        } else {
            logger.error("Redirect to login...");
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
