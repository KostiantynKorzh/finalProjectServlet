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

        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);


        HttpSession session = req.getSession();

        boolean loggedIn = session.getAttribute("user") != null;

        boolean availableForGuest = req.getRequestURI().contains(loginURL)
                || req.getRequestURI().contains(signupURL);

        boolean lang = req.getRequestURI().contains(langUrl);

        if (!loggedIn && !availableForGuest && !lang) {
            res.sendRedirect(loginURL);
            return;
        }

        if (loggedIn && availableForGuest) {
            session.setAttribute("user", null);
            res.sendRedirect(loginURL);
            return;
        }

        filterChain.doFilter(req, res);

    }


    @Override
    public void destroy() {

    }
}
