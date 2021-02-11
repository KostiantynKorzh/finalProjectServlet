package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.dto.ResultDTO;
import me.project.model.dto.UserDTO;
import me.project.model.entity.Result;
import me.project.model.entity.Test;
import me.project.model.entity.User;
import me.project.model.service.ResultService;
import me.project.model.service.TestService;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddTestsToUserCommand implements Command {

    TestService testService = TestService.getInstance();
    private final UserService userService;
    private final ResultService resultService;

    public AddTestsToUserCommand(UserService userService, ResultService resultService) {
        this.userService = userService;
        this.resultService = resultService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Long userId;
        if (request.getRequestURI().contains("/add/")) {
            userId = Long.valueOf(request.getRequestURI().replaceAll(".*/addTests/", "")
                    .replaceAll("/add/.*", ""));

            addTestToRequired(request, userId);

            return "redirect:/admin/users/addTests/" + userId;
        } else if (request.getRequestURI().contains("/remove/")) {
            userId = Long.valueOf(request.getRequestURI().replaceAll(".*/addTests/", "")
                    .replaceAll("/remove/.*", ""));
            Long testId = Long.valueOf(request.getRequestURI().replaceAll(".*/remove/", ""));
            testService.removeFromRequired(userId, testId);
            return "redirect:/admin/users/addTests/" + userId;
        } else {
            userId = Long.valueOf(request.getRequestURI().replaceAll(".*/addTests/", ""));

            getUserTests(request, userId);

            return View.ADD_TESTS_TO_USER_PAGE;
        }
    }

    private void getUserTests(HttpServletRequest request, Long userId) {
        List<Test> availableTests = testService.getAvailableTests(userId);
        request.setAttribute("availableTests", availableTests);

        List<Test> requiredTests = testService.getRequiredTests(userId);
        request.setAttribute("requiredTests", requiredTests);
        List<Result> passedTests = resultService.getResults(userId);

        request.setAttribute("passedTests", passedTests);
        User user = userService.getUserById(userId);

        request.setAttribute("userToAdd",
                user.getFirstName() + " " + user.getLastName());
        request.setAttribute("userId", userId);
    }

    private void addTestToRequired(HttpServletRequest request, Long userId) {
        Long testId = Long.valueOf(request.getRequestURI().replaceAll(".*/add/", ""));
        try {
            testService.makeTestsRequired(userId, testId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
