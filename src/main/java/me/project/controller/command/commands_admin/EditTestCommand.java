package me.project.controller.command.commands_admin;

import me.project.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class EditTestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "redirect:" + request.getHeader("Referer");
    }
}
