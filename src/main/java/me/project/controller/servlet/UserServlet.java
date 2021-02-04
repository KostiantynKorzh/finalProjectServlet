package me.project.controller.servlet;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.commands_user.HomeCommand;
import me.project.controller.command.commands_user.PassTestCommand;
import me.project.controller.command.commands_user.PassedTestsCommand;
import me.project.controller.command.commands_user.RequiredTestsCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

    Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put("home", new HomeCommand());
        commands.put("requiredTests", new RequiredTestsCommand());
        commands.put("passedTests", new PassedTestsCommand());
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
        String path = req.getRequestURI().replaceAll(".*/user/", "");

        if (path.matches("requiredTests/pass/[0-9]+.*")) {
            commands.put(path, new PassTestCommand());
        }

        Command command = commands.getOrDefault(path,
                (r) -> View.USER_PAGE);
        String page = command.execute(req);
        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replace("redirect:", ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

}
