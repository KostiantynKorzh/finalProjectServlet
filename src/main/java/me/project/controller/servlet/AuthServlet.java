package me.project.controller.servlet;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.commands_auth.LoginCommand;
import me.project.controller.command.commands_auth.LogoutCommand;
import me.project.controller.command.commands_auth.SignupCommand;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/auth/*")
public class AuthServlet extends HttpServlet {

    Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put("login", new LoginCommand());
        commands.put("signup", new SignupCommand());
        commands.put("logout", new LogoutCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().replaceAll(".*/auth/", "");

        Command command = commands.getOrDefault(path,
                (r) -> View.LOGIN_PAGE);
        String page = command.execute(req);

        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replace("redirect:", ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
