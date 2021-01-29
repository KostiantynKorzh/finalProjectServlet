package me.project.controller.command;

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

        System.out.println("is valid: "+isValidForm(firstName, lastName, email, password));

        if (!isValidForm(firstName, lastName, email, password)) {
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
            userService.addUser(user);
            return "redirect:/WEB-INF/view/login.jsp";
        }
    }

    private boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    private boolean isValidForm(String firstName, String lastName, String login, String password) {
        System.out.println(isValidInput(firstName));
        System.out.println(isValidInput(lastName));
        System.out.println(isValidInput(login));
        System.out.println(isValidInput(password));
        return isValidInput(firstName) && isValidInput(lastName) &&
                isValidInput(login) && isValidInput(password);
    }

}
