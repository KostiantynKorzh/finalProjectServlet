package me.project.controller.command.commands_admin;

import me.project.controller.command.Command;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;

public class DeleteTestCommand implements Command {

    TestService testService = TestService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getRequestURI().replaceAll(".*/delete/", ""));
        testService.deleteTestById(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
