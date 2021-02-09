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
        testService = TestService.getInstance();
        int perPage = 2;
        String parameter = "id";
        int page = 1;
        int pages = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sorted") != null) {
            parameter = request.getParameter("sorted");
        }
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (page <= 1) {
            page = 1;
        }
        pages = (int) Math.ceil(testService.getPassedTestsCount(user.getId()) * 1.0 / perPage);
        request.setAttribute("pages", pages);
        if (page > pages - 1) {
            page = pages;
        }
        request.setAttribute("page", page);
        request.setAttribute("parameter", parameter);

        List<ResultDTO> passedTests = testService.getResultsByUserIdSortedByAndPaginated(user.getId(), parameter, page - 1, perPage);
        request.setAttribute("passedTests", passedTests);

        return View.PASSED_TESTS_PAGE;
    }
}
