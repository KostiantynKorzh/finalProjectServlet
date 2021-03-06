package me.project.controller.servlet;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.commands_user.*;
import me.project.model.service.ResultService;
import me.project.model.service.TestService;
import me.project.model.service.UserService;

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

    private Map<String, Command> commands = new HashMap<>();

    private Command passTestCommand;
    private Command takeTestCommand;

    private UserService userService;
    private ResultService resultService;


    @Override
    public void init() {

        userService = new UserService();
        resultService = new ResultService();

        commands.put("home", new HomeCommand());
        commands.put("requiredTests/", new RequiredTestsCommand());
        commands.put("passedTests/", new PassedTestsCommand(resultService));
        commands.put("profile", new ProfileCommand(userService, resultService));
        passTestCommand = new PassTestCommand(resultService);
        takeTestCommand = new TakeTestCommand(resultService);
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

        initWithRegex(path);

        Command command = commands.getOrDefault(path,
                (r) -> View.HOME_PAGE_USER);
        String page = command.execute(req);
        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replace("redirect:", ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    private void initWithRegex(String path) {
        if (path.matches("requiredTests/pass/[0-9]+.*")) {
            if (!commands.containsKey(path)) {
                commands.put(path, passTestCommand);
            }
        }
        if (path.matches("requiredTests/take/[0-9]+.*")) {
            if (!commands.containsKey(path)) {
                commands.put(path, takeTestCommand);
            }
        }
    }

}
