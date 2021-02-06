package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;

public class GetTestsCommand implements Command {

    TestService testService;

    @Override
    public String execute(HttpServletRequest request) {
        int perPage = 2;
        String parameter = "id";
        int page = 1;
        int pages = 1;
        testService = TestService.getInstance();
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sorted") != null) {
            parameter = request.getParameter("sorted");
        }
        pages = (int) Math.ceil(testService.getTests().size() * 1.0 / perPage);
        request.setAttribute("pages", pages);
        if (page <= 1) {
            page = 1;
        }
        if (page > pages - 1) {
            page = pages;
        }
        request.setAttribute("parameter", parameter);
        request.setAttribute("page", page);
        request.setAttribute("pages", pages);

        request.setAttribute("tests",
                testService.getTestsSortedByAndPaginated(parameter, page - 1, perPage));
        return View.ALL_TESTS_PAGE;
    }
}
