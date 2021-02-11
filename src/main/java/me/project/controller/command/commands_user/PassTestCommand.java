package me.project.controller.command.commands_user;

import me.project.controller.command.Command;
import me.project.model.dto.UserDTO;
import me.project.model.service.ResultService;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PassTestCommand implements Command {

    ResultService resultService;

    public PassTestCommand(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = ((UserDTO) session.getAttribute("user")).getId();
        Long testId = Long.valueOf(request.getRequestURI().replaceAll(".*/pass/", ""));
        try {
            resultService.passTest(userId, testId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:" + request.getHeader("Referer");
    }
}
