package me.project.controller.command.commands_admin;

import me.project.controller.command.Command;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {

    UserService userService;

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getRequestURI().replaceAll(".*/delete/", ""));
        try {
            userService = new UserService();
            userService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/users/?sorted=";
    }
}
