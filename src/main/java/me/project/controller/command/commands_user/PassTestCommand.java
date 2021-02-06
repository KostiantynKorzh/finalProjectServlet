package me.project.controller.command.commands_user;

import me.project.controller.command.Command;
import me.project.model.dto.UserDTO;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PassTestCommand implements Command {

    TestService testService = TestService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        testService = TestService.getInstance();
        HttpSession session = request.getSession();
        Long userId = ((UserDTO) session.getAttribute("user")).getId();
        Long testId = Long.valueOf(request.getRequestURI().replaceAll(".*/pass/", ""));
        testService.passTest(userId, testId);

        return "redirect:" + request.getHeader("Referer");
    }
}
