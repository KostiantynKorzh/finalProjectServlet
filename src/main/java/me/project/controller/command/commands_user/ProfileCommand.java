package me.project.controller.command.commands_user;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.dto.UserDTO;
import me.project.model.dto.UserInfoDTO;
import me.project.model.entity.User;
import me.project.model.service.TestService;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProfileCommand implements Command {

    private final TestService testService = TestService.getInstance();
    private final UserService userService;

    public ProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        request.setAttribute("overall", testService.getPassedTestsCount(user.getId()));
        String avgGrade = String.format("%.2f", testService.getAverageGradeOfPassedTests(user.getId()));
        request.setAttribute("avgGrade", avgGrade);
        request.setAttribute("todo", testService.getRequiredTestsCount(user.getId()));
        User userTemp = userService.getUserById(user.getId());
        UserInfoDTO userToInfo = new UserInfoDTO(userTemp.getFirstName(), userTemp.getLastName(), userTemp.getLogin());
        request.setAttribute("userToInfo", userToInfo);
        return View.USER_PROFILE_PAGE;
    }
}
