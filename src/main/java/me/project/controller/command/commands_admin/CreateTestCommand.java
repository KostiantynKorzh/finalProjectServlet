package me.project.controller.command.commands_admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.project.controller.View;
import me.project.controller.command.Command;
import me.project.model.dto.CompleteTestDTO;
import me.project.model.dto.CreateTestDTO;
import me.project.model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateTestCommand implements Command {

    TestService testService = TestService.getInstance();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getRequestURI().contains("/complete")) {
            List<CreateTestDTO> tests;
            try {
                tests = getCreatedTestFromJSONRequest(request);
                testService.createFullTestWithQuestionsAndAnswers(tests);
            }  catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/admin/tests";
        }

        request.setAttribute("title", request.getParameter("title"));
        request.setAttribute("subject", request.getParameter("subject"));
        request.setAttribute("difficulty", request.getParameter("difficulty"));
        request.setAttribute("duration", request.getParameter("duration"));
        return View.CREATE_TEST_PAGE_ADMIN;
    }


    private List<CreateTestDTO> getCreatedTestFromJSONRequest(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        Scanner requestBodyScanner = new Scanner(request.getInputStream());
        while (requestBodyScanner.hasNextLine()) {
            requestBody.append(requestBodyScanner.nextLine());
        }
        requestBodyScanner.close();

        return jsonMapper.readValue(requestBody.toString(), new TypeReference<List<CreateTestDTO>>() {
        });
    }

}
