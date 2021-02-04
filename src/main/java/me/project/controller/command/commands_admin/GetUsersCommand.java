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
        String parameter = "id";
        int page = 1;
        int pages = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sorted") != null) {
            parameter = request.getParameter("sorted");
        }
        request.setAttribute("page", page);
        request.setAttribute("parameter", parameter);
        List<User> users;
        pages = (int) Math.ceil(userService.getUsers().size() * 1.0 / 2);
        request.setAttribute("pages", pages);
        System.out.println(parameter + " : " + (page - 1));
        users = userService.getUsersSortedByAndPaginated(parameter, page - 1, 2);
        request.setAttribute("users", users);
        return View.ALL_USERS_PAGE;
    }
}
