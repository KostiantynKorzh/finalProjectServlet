package me.project.controller.command.commands_user;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.util.PaginationAndSorting;
import me.project.model.dto.UserDTO;
import me.project.model.dto.ResultDTO;
import me.project.model.entity.Result;
import me.project.model.service.ResultService;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PassedTestsCommand implements Command {

    ResultService resultService;

    public PassedTestsCommand(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        int numberOfRows = resultService.getPassedTestsCount(user.getId());

        PaginationAndSorting.configurePageAndParameter(request, numberOfRows);

        List<Result> passedTests = resultService.getResultsByUserIdSortedByAndPaginated(user.getId(),
                PaginationAndSorting.getParameter(),
                PaginationAndSorting.getPage() - 1,
                PaginationAndSorting.PER_PAGE);
        request.setAttribute("passedTests", passedTests);

        return View.PASSED_TESTS_PAGE;
    }
}
