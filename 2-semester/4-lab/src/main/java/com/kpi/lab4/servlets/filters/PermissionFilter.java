package com.kpi.lab4.servlets.filters;

import com.kpi.lab4.enums.UserType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class PermissionFilter implements Filter {
    private Map<UserType, List<String>> permissions;
    private List<String> allowList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.allowList = new ArrayList<>();
        this.allowList.add("/login");
        this.allowList.add("/register");
        this.allowList.add("/logout");
        this.allowList.add("/css");

        List<String> userPermissions = new ArrayList<>();
        userPermissions.add("/order");
        userPermissions.add("/request");
        userPermissions.add("/browse");
        userPermissions.add("/home");

        List<String> managerPermissions = new ArrayList<>();
        managerPermissions.add("/resolve");
        userPermissions.add("/browse");
        userPermissions.add("/home");

        this.permissions = new HashMap<>();
        this.permissions.put(UserType.USER, userPermissions);
        this.permissions.put(UserType.MANAGER, managerPermissions);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String path = req.getServletPath();
        if (this.allowList.contains(path)) {
            chain.doFilter(request, response);
        } else if (path.startsWith("/css")) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession(false);
            UserType type = (UserType) session.getAttribute("role");
            List<String> permissions = this.permissions.get(type);
            if (permissions.contains(path)) {
                chain.doFilter(request, response);
            } else {
                request.setAttribute("error", "Permission denied");
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
