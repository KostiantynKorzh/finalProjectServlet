package me.project.controller.servlet;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.commands_admin.*;
import me.project.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {

    Map<String, Command> commands = new HashMap<>();
    String path;
    UserService userService = new UserService();

    @Override
    public void init() {
        commands.put("home", new HomeCommand());
        commands.put("tests", new GetTestsCommand());
        commands.put("users/", new GetUsersCommand(userService));
        commands.put("users/edit", new EditUserCommand());
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
        path = req.getRequestURI().replaceAll(".*/admin/", "");

        if (path.matches("users/delete/[0-9]+")) {
            commands.put(path, new DeleteUserCommand());
        }
        if (path.matches("users/addTests/[0-9]+.*")) {
            commands.put(path, new AddTestsToUserCommand());
        }
        if (path.matches("tests/delete/[0-9]+")) {
            commands.put(path, new DeleteTestCommand());
        }
        if (path.matches("tests/edit/[0-9]+")) {
            commands.put(path, new EditTestCommand());
        }

        Command command = commands.getOrDefault(path,
                (r) -> View.HOME_PAGE_ADMIN);
        String page = command.execute(req);
        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replace("redirect:", ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

}
