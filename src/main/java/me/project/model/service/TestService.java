package me.project.model.service;

import me.project.model.dao.*;
import me.project.model.dao.factory.DaoFactory;
import me.project.model.dto.CreateTestDTO;
import me.project.model.dto.TestDTO;
import me.project.model.entity.Answer;
import me.project.model.entity.Question;
import me.project.model.entity.RequiredTest;
import me.project.model.entity.Test;
import me.project.model.entity.enums.Difficulty;
import me.project.model.entity.enums.Subject;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TestService {

    private static TestService testService;

    static final Logger LOGGER = Logger.getLogger(TestService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();

    private TestService() {
    }


    public TestDTO getTestWithQuestionsAndAnswersByTestId(Long testId) {
        try (TestDao testDao = daoFactory.createTestFactory();
             QuestionDao questionDao = daoFactory.createQuestionFactory();
             AnswerDao answerDao = daoFactory.createAnswerFactory()) {
            Test test = testDao.findById(testId);
            List<Question> questions = questionDao.findAllByTestId(testId);
            List<Answer> answers = answerDao.findAllByTestId(testId);
            return new TestDTO(test, questions, answers);
        }
    }

    public void createAnswers(List<CreateTestDTO> createdTest) {
        try (QuestionDao questionDao = daoFactory.createQuestionFactory();
             AnswerDao answerDao = daoFactory.createAnswerFactory()) {
            List<Answer> answers = new ArrayList<>();
            for (int i = 0; i < createdTest.size(); i++) {
                for (int j = 0; j < createdTest.get(i).getAnswers().length; j++) {
                    answers.add(new Answer.Builder()
                            .questionId(questionDao.findIdByQuestionText(createdTest.get(i).getQuestionText()))
                            .answerText(createdTest.get(i).getAnswers()[j].getAnswerText())
                            .isCorrect(createdTest.get(i).getAnswers()[j].isCorrect())
                            .build());
                }
            }
            answerDao.createAnswers(answers);
        }
    }

    public void createQuestions(List<CreateTestDTO> createdTest) {
        try (QuestionDao questionDao = daoFactory.createQuestionFactory()) {
            List<String> questions = new ArrayList<>();
            for (int i = 0; i < createdTest.size(); i++) {
                questions.add(createdTest.get(i).getQuestionText());
            }
            questionDao.createQuestions(questions);
        }
    }

    public void createTest(List<CreateTestDTO> createdTest) {
        try (TestDao testDao = daoFactory.createTestFactory()) {
            Test test = new Test.Builder()
                    .title(createdTest.get(0).getTestTitle())
                    .subject(Subject.valueOf(createdTest.get(0).getTestSubject()))
                    .difficulty(Difficulty.valueOf(createdTest.get(0).getTestDifficulty()))
                    .duration(createdTest.get(0).getTestDuration())
                    .build();
            testDao.create(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFullTestWithQuestionsAndAnswers(List<CreateTestDTO> createdTest) {
        createTest(createdTest);
        createQuestions(createdTest);
        createAnswers(createdTest);
    }


    public void removeFromRequired(Long userId, Long testId) {
        try (RequiredTestDao requiredTestDao = daoFactory.createRequiredTestFactory()) {
            requiredTestDao.delete(new RequiredTest(userId, testId));
        }
    }

    public void makeTestsRequired(Long userId, Long testId) throws Exception {
        try (RequiredTestDao requiredTestDao = daoFactory.createRequiredTestFactory()) {
            requiredTestDao.create(new RequiredTest(userId, testId));
        }
    }

    // find all available tests for particular user
    // available test = NOT passed, NOT required
    public List<Test> getAvailableTests(Long userId) {
        try (TestDao testDao = daoFactory.createTestFactory();
             ResultDao resultDao = daoFactory.createResultFactory()) {
            List<Test> allTests = testDao.findAll();
            List<Test> requiredTests = testDao.findAllRequiredTestsByUserId(userId);
            List<Test> passedTests = new ArrayList<>();
            resultDao.findAllByUserId(userId)
                    .forEach(resultDTO -> passedTests.add(resultDTO.getTest()));
            allTests.removeAll(requiredTests);
            allTests.removeAll(passedTests);

            return allTests;
        }
    }

    public List<Test> getTests() {
        try (TestDao testDao = daoFactory.createTestFactory()) {
            return testDao.findAll();
        }
    }

    public void deleteTestById(Long id) {
        try (TestDao testDao = daoFactory.createTestFactory()) {
            testDao.deleteById(id);
        }
    }

    public List<Test> getTestsSortedByAndPaginated(String parameter, int page, int perPage) {
        try (TestDao testDao = daoFactory.createTestFactory()) {
            return testDao.findAllSortedByAndPaginated(parameter, page, perPage);
        }
    }

    public List<Test> getRequiredTestsByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage) {
        try (TestDao testDao = daoFactory.createTestFactory()) {
            return testDao.findAllByUserIdSortedByAndPaginated(id, parameter, page, perPage);
        }
    }

    public List<Test> getRequiredTests(Long userId) {
        try (TestDao testDao = daoFactory.createTestFactory()) {
            return testDao.findAllRequiredTestsByUserId(userId);
        }
    }

    public Integer getRequiredTestsCount(Long userId) {
        try (TestDao testDao = daoFactory.createTestFactory()) {
            return testDao.findAllRequiredTestsByUserId(userId).size();
        }
    }

    public static TestService getInstance() {
        if (testService == null) {
            synchronized (TestService.class) {
                if (testService == null) {
                    testService = new TestService();
                }
            }
        }
        return testService;
    }

}
