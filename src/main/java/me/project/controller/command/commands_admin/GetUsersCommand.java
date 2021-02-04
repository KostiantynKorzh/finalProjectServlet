package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.entity.User;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsersCommand implements Command {

    UserService userService;

    @Override
    public String execute(HttpServletRequest request) {
        userService = new UserService();
        List<User> users = userService.getUsers();
        request.setAttribute("users", users);
        return View.ALL_USERS_PAGE;
    }
}
