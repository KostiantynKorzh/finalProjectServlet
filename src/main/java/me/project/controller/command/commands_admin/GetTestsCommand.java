package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.util.PaginationAndSorting;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;

public class GetTestsCommand implements Command {

    TestService testService;

    @Override
    public String execute(HttpServletRequest request) {

        testService = TestService.getInstance();

        int numberOfRows = testService.getTests().size();

        PaginationAndSorting.configurePageAndParameter(request, numberOfRows);

        request.setAttribute("tests", testService.getTestsSortedByAndPaginated(
                PaginationAndSorting.getParameter(),
                PaginationAndSorting.getPage() - 1,
                PaginationAndSorting.PER_PAGE));

        return View.ALL_TESTS_PAGE;
    }
}
