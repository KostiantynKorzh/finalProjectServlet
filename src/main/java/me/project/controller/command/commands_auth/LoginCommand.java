package me.project.controller.command.commands_auth;

import me.project.controller.command.Command;
import me.project.controller.command.util.Validation;
import me.project.model.service.AuthService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    AuthService authService;

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (Validation.isValidForm(login, password)) {
            try {
                authService = new AuthService();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (authService.login(login, password, request)) {
                return "redirect:/home";
            }
        }
        return "/WEB-INF/view/login.jsp";

    }
}
