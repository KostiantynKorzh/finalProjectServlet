package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
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
        pages = (int) Math.ceil(userService.getUsers().size() * 1.0 / perPage);
        request.setAttribute("pages", pages);
        if (page <= 1) {
            page = 1;
        }
        if (page > pages - 1) {
            page = pages;
        }
        request.setAttribute("page", page);
        request.setAttribute("parameter", parameter);
        List<User> users;

        users = userService.getUsersSortedByAndPaginated(parameter, page - 1, perPage);
        request.setAttribute("users", users);
        return View.ALL_USERS_PAGE;
    }
}
