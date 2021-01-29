package me.project.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String loginURL = req.getContextPath() + "/auth/login";
        String signupURL = req.getContextPath() + "/auth/signup";
        String langUrl = req.getContextPath() + "/lang";

        HttpSession session = req.getSession();

        boolean loggedIn = session.getAttribute("user") != null;

        boolean canGuest = req.getRequestURI().contains(loginURL)
                || req.getRequestURI().contains(signupURL)||req.getRequestURI().contains(langUrl);

        if (!loggedIn && !canGuest) {
            res.sendRedirect(loginURL);
            return;
        }

        filterChain.doFilter(req, res);

    }


    @Override
    public void destroy() {

    }
}
