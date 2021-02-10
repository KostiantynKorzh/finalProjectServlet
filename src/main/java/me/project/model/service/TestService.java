package me.project.model.service;

import me.project.model.dao.*;
import me.project.model.dao.factory.DaoFactory;
import me.project.model.dto.CompleteTestDTO;
import me.project.model.dto.CreateTestDTO;
import me.project.model.dto.ResultDTO;
import me.project.model.dto.TestDTO;
import me.project.model.entity.*;
import me.project.model.entity.enums.Difficulty;
import me.project.model.entity.enums.Subject;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TestService {

    private static TestService testService;


    TestDao testDao = DaoFactory.getInstance().createTestFactory();
    ResultDao resultDao = DaoFactory.getInstance().createResultFactory();
    UserDao userDao = DaoFactory.getInstance().createUserFactory();
    RequiredTestDao requiredTestDao = DaoFactory.getInstance().createRequiredTestFactory();
    QuestionDao questionDao = DaoFactory.getInstance().createQuestionFactory();
    AnswerDao answerDao = DaoFactory.getInstance().createAnswerFactory();


    private TestService() {
    }

    public void passTest(Long userId, Long testId) throws Exception {
        User user = userDao.findById(userId);
        Test test = testDao.findById(testId);
        resultDao.create(createResultObject(userId, test, new Random().nextInt(100)));
        resultDao.close();
        requiredTestDao.delete(new RequiredTest(user, test));
        requiredTestDao.close();
    }

    public ResultDTO createResultObject(Long userId, Test test, int score) {
        return new ResultDTO.Builder()
                .userId(userId)
                .test(test)
                .score(score)
                .passTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public TestDTO getTestWithQuestionsAndAnswersByTestId(Long testId) {
        Test test = testDao.findById(testId);
        List<Question> questions = questionDao.findAllByTestId(testId);
        List<Answer> answers = answerDao.findAllByTestId(testId);
        return new TestDTO(test, questions, answers);
    }

    public void createAnswers(List<CreateTestDTO> createdTest) {
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

    public void createQuestions(List<CreateTestDTO> createdTest) {
        List<String> questions = new ArrayList<>();
        for (int i = 0; i < createdTest.size(); i++) {
            questions.add(createdTest.get(i).getQuestionText());
        }
        questionDao.createQuestions(questions);
    }

    public void createTest(List<CreateTestDTO> createdTest) throws Exception {
        Test test = new Test.Builder()
                .title(createdTest.get(0).getTestTitle())
                .subject(Subject.valueOf(createdTest.get(0).getTestSubject()))
                .difficulty(Difficulty.valueOf(createdTest.get(0).getTestDifficulty()))
                .duration(createdTest.get(0).getTestDuration())
                .build();
        testDao.create(test);
    }

    public void createFullTestWithQuestionsAndAnswers(List<CreateTestDTO> createdTest) throws Exception {
        createTest(createdTest);
        createQuestions(createdTest);
        createAnswers(createdTest);
    }

    public void checkCompletedTestAndCreateResult(Long userId, Long testId, List<CompleteTestDTO> completeTests) throws Exception {
        TestDTO testDTO = getTestWithQuestionsAndAnswersByTestId(testId);
        int correctAnswers = 0;
        for (Question question : testDTO.getQuestions()) {
            List<Long> correctAnswersForParticularQuestion =
                    testDTO.getAnswers()
                            .stream()
                            .filter(answer -> answer.getQuestionId().equals(question.getId()))
                            .filter(Answer::isCorrect)
                            .map(Answer::getId)
                            .collect(Collectors.toList());

            List<Long> chosenAnswersFromUser = completeTests.stream()
                    .filter(test -> test.getQuestionId().equals(question.getId()))
                    .flatMap(test -> Arrays.stream(test.getAnswers().clone()))
                    .filter(CompleteTestDTO.Answer::isChecked)
                    .map(CompleteTestDTO.Answer::getAnswerId)
                    .collect(Collectors.toList());


//            System.out.println("Question: " + question.getQuestionText());
//            System.out.println(correctAnswersForParticularQuestion);
//            System.out.println(chosenAnswersFromUser);
//            System.out.println(correctAnswersForParticularQuestion.size() == chosenAnswersFromUser.size() &&
//                    correctAnswersForParticularQuestion.containsAll(chosenAnswersFromUser));
            if (areAnswersMatch(correctAnswersForParticularQuestion, chosenAnswersFromUser)) {
                correctAnswers++;
            }
        }
        System.out.println(correctAnswers);
        System.out.println();
        int score = (int) (correctAnswers * 1.0 / testDTO.getQuestions().size() * 100);

        resultDao.create(createResultObject(userId, testDao.findById(testId), score));
        resultDao.close();
        User user = userDao.findById(userId);
        Test test = testDao.findById(testId);
        requiredTestDao.delete(new RequiredTest(user, test));
        requiredTestDao.close();
    }

    private boolean areAnswersMatch(List<Long> correctAnswersForParticularQuestion, List<Long> chosenAnswersFromUser) {
        return correctAnswersForParticularQuestion.size() == chosenAnswersFromUser.size() &&
                correctAnswersForParticularQuestion.containsAll(chosenAnswersFromUser);
    }

    public void removeFromRequired(Long userId, Long testId) {
        User user = userDao.findById(userId);
        Test test = testDao.findById(testId);
        requiredTestDao.delete(new RequiredTest(user, test));
        requiredTestDao.close();
    }

    public void makeTestsRequired(Long userId, Long testId) throws Exception {
        User user = userDao.findById(userId);
        Test test = testDao.findById(testId);
        requiredTestDao.create(new RequiredTest(user, test));
        requiredTestDao.close();
    }

    public List<Test> getAvailableTests(Long userId) {
        List<Test> allTests = testDao.findAll();
        List<Test> requiredTests = testDao.findAllRequiredTestsByUserId(userId);
        List<Test> passedTests = new ArrayList<>();
        resultDao.findAllByUserId(userId)
                .forEach(resultDTO -> passedTests.add(resultDTO.getTest()));
        allTests.removeAll(requiredTests);
        allTests.removeAll(passedTests);

        return allTests;
    }

    public List<Test> getTests() {
        return testDao.findAll();
    }

    public void deleteTestById(Long id) {
        testDao.deleteById(id);
    }

    public List<Test> getTestsSortedByAndPaginated(String parameter, int page, int perPage) {
        return testDao.findAllSortedByAndPaginated(parameter, page, perPage);
    }

    public List<Test> getRequiredTestsByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage) {
        return testDao.findAllByUserIdSortedByAndPaginated(id, parameter, page, perPage);
    }

    public List<Test> getRequiredTests(Long userId) {
        return testDao.findAllRequiredTestsByUserId(userId);
    }

    public Integer getRequiredTestsCount(Long userId) {
        return testDao.findAllRequiredTestsByUserId(userId).size();
    }

    public List<ResultDTO> getResults(Long userId) {
        return resultDao.findAllByUserId(userId);
    }

    public Double getAverageGradeOfPassedTests(Long userId) {
        AtomicReference<Double> average = new AtomicReference<>(0.0);
        List<ResultDTO> results = resultDao.findAllByUserId(userId);
        if (results.size() == 0) {
            return 0.0;
        }
        results.forEach(result -> average.updateAndGet(v -> v + result.getScore()));
        return average.get() / results.size();
    }

    public List<ResultDTO> getResultsByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage) {
        return resultDao.findAllByUserIdSortedByAndPaginated(id, parameter, page, perPage);
    }

    public Integer getPassedTestsCount(Long userId) {
        return resultDao.findAllByUserId(userId).size();
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
