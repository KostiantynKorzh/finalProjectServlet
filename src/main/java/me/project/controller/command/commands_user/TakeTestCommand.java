package me.project.controller.command.commands_user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.dto.CompleteTestDTO;
import me.project.model.dto.TestDTO;
import me.project.model.dto.UserDTO;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TakeTestCommand implements Command {
    TestService testService = TestService.getInstance();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public String execute(HttpServletRequest request) {
        Long testId = Long.valueOf(request.getRequestURI().replaceAll(".*/take/", "")
                .replaceAll("/complete", ""));
        if (request.getRequestURI().contains("/complete")) {

            List<CompleteTestDTO> tests = new ArrayList<>();
            try {
                tests = getCompletedTestedFromJSONRequest(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("user");
            try {
                testService.checkCompletedTestAndCreateResult(user.getId(), testId, tests);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/user/requiredTests/?sorted=id&page=1";
        } else {
            TestDTO test = testService.getTestWithQuestionsAndAnswersByTestId(testId);
            request.setAttribute("test", test);
            Date date = new Date();
            HttpSession session = request.getSession();
            if (session.getAttribute("test") == null ||
                    !(((TestDTO) (session.getAttribute("test"))).getTest().getId().equals(test.getTest().getId()))) {
                session.setAttribute("test", test);
                session.setAttribute("deadline", date.getTime() + test.getTest().getDuration()*1000);
            }
            return View.TAKE_TEST_PAGE_USER;
        }
    }

    private List<CompleteTestDTO> getCompletedTestedFromJSONRequest(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        Scanner requestBodyScanner = new Scanner(request.getInputStream());
        while (requestBodyScanner.hasNextLine()) {
            requestBody.append(requestBodyScanner.nextLine());
        }
        requestBodyScanner.close();

        return jsonMapper.readValue(requestBody.toString(), new TypeReference<List<CompleteTestDTO>>() {
        });
    }

}
