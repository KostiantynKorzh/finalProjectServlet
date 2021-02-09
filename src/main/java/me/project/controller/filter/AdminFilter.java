package me.project.controller.filter;

import me.project.model.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        boolean loggedIn = session.getAttribute("user") != null;

        boolean isAdmin = false;

        if (loggedIn) {
            System.out.println(session.getAttribute("user"));
            isAdmin = ((UserDTO) session.getAttribute("user")).getRole().getName().equals("ADMIN");
        }

        if (req.getRequestURI().contains("/admin")) {
            if (!loggedIn || !isAdmin) {
                res.sendRedirect("/home");
                return;
            }
        }
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
