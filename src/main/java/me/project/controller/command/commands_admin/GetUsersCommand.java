package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.entity.User;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsersCommand implements Command {

    UserService userService;

    public GetUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String parameter = request.getParameter("sorted");
        List<User> users;
        if (parameter.equals("")) {
            users = userService.getUsers();
        } else {
            System.out.println("PARAMETER = " + parameter);
            users = userService.getUsersSortedBy(parameter);
        }
        request.setAttribute("users", users);
        return View.ALL_USERS_PAGE;
    }
}
