package me.project.controller.command.commands_auth;

import me.project.controller.command.Command;
import me.project.controller.command.util.Validation;
import me.project.model.entity.Role;
import me.project.model.entity.User;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class SignupCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!Validation.isValidForm(firstName, lastName, email, password)) {
            return "/WEB-INF/view/signup.jsp";
        } else {
            UserService userService = new UserService();
            User user = new User.Builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .login(email)
                    .password(password)
                    .role(Role.USER)
                    .build();
//            userService.addUser(user, request);
            return "redirect:/auth/login";
        }
    }

}
