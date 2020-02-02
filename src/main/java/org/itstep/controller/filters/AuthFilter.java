package org.itstep.controller.filters;

import org.itstep.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        ServletContext context = request.getServletContext();
        System.out.println(session);
        System.out.println("Role: " + session.getAttribute("role"));
        System.out.println("Logged user: " + context.getAttribute("loggedUsers"));
        User.ROLE role = (User.ROLE) session.getAttribute("role");
        String path = req.getRequestURI();
        if (role == null || role.equals(User.ROLE.UNKNOWN)) {
            filterChain.doFilter(request, response);
            return;
        }

        //User logged in
        if (session != null && session.getAttribute("role") != null) {
            if (path.endsWith("coffee/") || path.contains("login")
                    || isPathNotCorrectForUser(role, path)) {
                res.sendRedirect("logout");
                return;
            }
        }

        filterChain.doFilter(request,response);
    }

    private boolean isPathNotCorrectForUser(User.ROLE role, String path) {
        return ((role.equals(User.ROLE.USER) && path.contains("admin"))
                || role.equals(User.ROLE.ADMIN) && path.contains("user"));
    }

    @Override
    public void destroy() {

    }
}
