package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.entity.User;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements Command {

    UserService userService= new UserService();;

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        User user = userService.getUserById(id);

        updateUserName(user, request.getParameter("firstName"),request.getParameter("lastName"));

        return "redirect:" + request.getHeader("Referer");
    }

    private void updateUserName(User user, String firstName, String lastName){
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userService.updateUser(user);
    }

}
