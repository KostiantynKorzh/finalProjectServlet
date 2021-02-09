package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.entity.User;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements Command {

    UserService userService;

    @Override
    public String execute(HttpServletRequest request) {
        userService = new UserService();
        Long id = Long.valueOf(request.getParameter("id"));
        User user = userService.getUserById(id);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        userService.updateUser(user);
        return "redirect:" + request.getHeader("Referer");
    }
}
