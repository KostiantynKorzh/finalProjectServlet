package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.controller.command.util.PaginationAndSorting;
import me.project.model.entity.User;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsersCommand implements Command {

    UserService userService;

    public GetUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        int numberOfRows = userService.getUsers().size();

        PaginationAndSorting.configurePageAndParameter(request, numberOfRows);

        List<User> users = userService.getUsersSortedByAndPaginated(
                PaginationAndSorting.getParameter(),
                PaginationAndSorting.getPage() - 1,
                PaginationAndSorting.PER_PAGE);
        request.setAttribute("users", users);
        return View.ALL_USERS_PAGE;
    }
}
