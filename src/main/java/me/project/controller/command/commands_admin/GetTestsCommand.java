package me.project.controller.command.commands_admin;

import me.project.controller.command.Command;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;

public class GetTestsCommand implements Command {

    TestService testService;

    @Override
    public String execute(HttpServletRequest request) {
        try {
            testService = TestService.getInstance();
            request.setAttribute("tests", testService.getTests());
            return "/WEB-INF/view/admin/allTests.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin";
    }
}
