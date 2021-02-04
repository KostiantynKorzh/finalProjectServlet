package me.project.controller.command.commands_auth;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.util.Validation;
import me.project.model.service.AuthService;

import javax.servlet.http.HttpServletRequest;

public class SignupCommand implements Command {

    AuthService authService;

    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!Validation.isValidForm(firstName, lastName, email, password)) {
            return View.SIGNUP_PAGE;
        } else {
            try {
                authService = new AuthService();
                authService.signup(firstName, lastName, email, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/auth/login";
        }
    }

}
