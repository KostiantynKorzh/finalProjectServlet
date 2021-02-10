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

    private final Map<String, Command> commands = new HashMap<>();
    private UserService userService;
    private DeleteUserCommand deleteUserCommand;
    private AddTestsToUserCommand addTestsToUserCommand;
    private DeleteTestCommand deleteTestCommand;
    private CreateTestCommand createTestCommand;

    @Override
    public void init() {
        userService = new UserService();

        commands.put("home", new HomeCommand());
        commands.put("tests/", new GetTestsCommand());
        commands.put("users/", new GetUsersCommand(userService));
        commands.put("users/edit", new EditUserCommand());
        commands.put("profile", new ProfileCommand());

        deleteUserCommand = new DeleteUserCommand();
        addTestsToUserCommand = new AddTestsToUserCommand();
        deleteTestCommand = new DeleteTestCommand();
        createTestCommand = new CreateTestCommand();

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
        String path = req.getRequestURI().replaceAll(".*/admin/", "");

        initWithRegex(path);

        Command command = commands.getOrDefault(path,
                (r) -> View.HOME_PAGE_ADMIN);

        String page = command.execute(req);

        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replace("redirect:", ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    private void initWithRegex(String path) {
        if (path.matches("users/delete/[0-9]+")) {
            if (!commands.containsKey(path)) {
                commands.put(path, deleteUserCommand);
            }
        }
        if (path.matches("users/addTests/[0-9]+.*")) {
            if (!commands.containsKey(path)) {
                commands.put(path, addTestsToUserCommand);
            }
        }
        if (path.matches("tests/delete/[0-9]+")) {
            if (!commands.containsKey(path)) {
                commands.put(path, deleteTestCommand);
            }
        }
        if (path.matches("createTest.*")) {
            if (!commands.containsKey(path)) {
                commands.put(path, createTestCommand);
            }
        }
    }

}
