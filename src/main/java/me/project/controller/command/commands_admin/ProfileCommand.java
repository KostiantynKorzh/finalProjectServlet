package me.project.controller.command.commands_admin;

import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.dto.UserDTO;
import me.project.model.dto.UserInfoDTO;
import me.project.model.entity.User;
import me.project.model.service.TestService;
import me.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements Command {

    UserService userService;
    TestService testService;

    @Override
    public String execute(HttpServletRequest request) {
        userService = UserService.getInstance();
        testService = TestService.getInstance();
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        request.setAttribute("overallTests", testService.getTests().size());
        request.setAttribute("overallUsers", userService.getUsers().size());
        User userTemp = userService.getUserById(user.getId());
        UserInfoDTO userToInfo = new UserInfoDTO(userTemp.getFirstName(), userTemp.getLastName(), userTemp.getLogin());
        request.setAttribute("userToInfo", userToInfo);
        return View.ADMIN_PROFILE_PAGE;
    }
}
