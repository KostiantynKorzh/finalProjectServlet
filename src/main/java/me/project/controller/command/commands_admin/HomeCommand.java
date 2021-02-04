package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return View.HOME_PAGE_ADMIN;
    }
}
