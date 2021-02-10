package me.project.controller.command.commands_auth;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.util.Validation;
import me.project.model.dto.UserDTO;
import me.project.model.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

//    static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    AuthService authService;

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (Validation.isValidForm(login, password)) {
            authService = new AuthService();
            if (authService.login(login, password, request)) {
                HttpSession session = request.getSession();
                UserDTO user = (UserDTO) session.getAttribute("user");
//                LOGGER.info("Successfully logged in " + user.getLogin());
                return user.getRole().getName().equals("ADMIN") ? "redirect:/admin/home" : "redirect:/user/home";
            }
//            LOGGER.error("BAD CREDENTIALS");
            request.setAttribute("message", "BAD CREDENTIALS");
        }
        return View.LOGIN_PAGE;

    }
}
