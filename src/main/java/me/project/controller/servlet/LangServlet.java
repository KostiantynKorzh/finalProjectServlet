package me.project.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/lang/*")
public class LangServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String lang = req.getRequestURI().replaceAll(".*lang/", "");

        if (lang.equals("ua")) {
            session.setAttribute("lang", "ua");
        } else if (lang.equals("en")) {
            session.setAttribute("lang", "en");
        }

        String url = req.getHeader("Referer");
        resp.sendRedirect(req.getContextPath() + url);

    }
}
