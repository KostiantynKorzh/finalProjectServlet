package me.project.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/lang")
public class LangServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String sessionLang = (String) session.getAttribute("lang");

        if (sessionLang.equals("ua")) {
            session.setAttribute("lang", "en");
        } else {
            session.setAttribute("lang", "ua");
        }

        System.out.println("Lang servlet: " + sessionLang);

        String url = req.getHeader("Referer");
        resp.sendRedirect(req.getContextPath() + url);

    }
}
