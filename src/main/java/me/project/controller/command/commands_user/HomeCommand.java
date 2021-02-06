package me.project.controller.command.commands_user;

import me.project.controller.View;
import me.project.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("HOME EXECUTE");
        return View.HOME_PAGE_USER;
    }
}