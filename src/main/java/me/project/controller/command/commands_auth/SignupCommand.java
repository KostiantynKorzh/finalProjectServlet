package me.project.controller.command.commands_auth;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.util.Validation;
import me.project.model.service.AuthService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignupCommand implements Command {

    AuthService authService;

    static final Logger LOGGER = Logger.getLogger(SignupCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!Validation.isValidForm(firstName, lastName, email, password)) {
            LOGGER.info("Not valid data");
            return View.SIGNUP_PAGE;
        } else {
            try {
                authService = new AuthService();
                authService.signup(firstName, lastName, email, password);
            } catch (Exception e) {
                LOGGER.error("Can't create user");
                request.setAttribute("message", "User with such email is already exists");
                return View.SIGNUP_PAGE;
            }
            return "redirect:/auth/login";
        }
    }

}
