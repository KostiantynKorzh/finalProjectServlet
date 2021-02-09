package me.project.controller.command.commands_auth;

import me.project.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("user", null);
        return "redirect:/auth/login";
    }
}
