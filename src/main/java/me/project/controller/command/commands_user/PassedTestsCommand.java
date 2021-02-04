package me.project.controller.command.commands_user;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.dto.UserDTO;
import me.project.model.dto.ResultDTO;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PassedTestsCommand implements Command {

    TestService testService;

    @Override
    public String execute(HttpServletRequest request) {
        try {
            testService = TestService.getInstance();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("user");
            List<ResultDTO> passedTests = testService.getResults(user.getId());
            request.setAttribute("passedTests", passedTests);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return View.PASSED_TESTS_PAGE;
    }
}
