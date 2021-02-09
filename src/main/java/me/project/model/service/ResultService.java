package me.project.model.service;

import me.project.model.dao.*;
import me.project.model.dao.factory.DaoFactory;
import me.project.model.dto.CompleteTestDTO;
import me.project.model.dto.TestDTO;
import me.project.model.entity.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ResultService {

    private static ResultService resultService;

    TestService testService = TestService.getInstance();
    DaoFactory daoFactory = DaoFactory.getInstance();

    private ResultService() {
    }

    public void passTest(Long userId, Long testId) {
        try (TestDao testDao = daoFactory.createTestFactory();
             ResultDao resultDao = daoFactory.createResultFactory();
             RequiredTestDao requiredTestDao = daoFactory.createRequiredTestFactory()) {
            Test test = testDao.findById(testId);
            resultDao.create(createResultObject(userId, test, new Random().nextInt(100)));
            requiredTestDao.delete(new RequiredTest(userId, testId));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Result createResultObject(Long userId, Test test, int score) {
        return new Result.Builder()
                .userId(userId)
                .test(test)
                .score(score)
                .passTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    // checking test by creating lists of correct answers and chosen
    // answers for each question
    public void checkCompletedTestAndCreateResult(Long userId, Long testId, List<CompleteTestDTO> completeTests) {
        TestDTO testDTO = testService.getTestWithQuestionsAndAnswersByTestId(testId);
        int correctAnswers = 0;
        for (Question question : testDTO.getQuestions()) {
            List<Long> correctAnswersForParticularQuestion =
                    getListOfCorrectAnswersIdsForQuestion(testDTO, question.getId());

            List<Long> chosenAnswersFromUser =
                    getListOfChosenAnswersIdsFromUser(completeTests, question.getId());

            if (areAnswersMatch(correctAnswersForParticularQuestion, chosenAnswersFromUser)) {
                correctAnswers++;
            }
        }
        int score = (int) (correctAnswers * 1.0 / testDTO.getQuestions().size() * 100);

        try (ResultDao resultDao = daoFactory.createResultFactory();
             RequiredTestDao requiredTestDao = daoFactory.createRequiredTestFactory();
             TestDao testDao = daoFactory.createTestFactory()) {
            resultDao.create(createResultObject(userId, testDao.findById(testId), score));
            requiredTestDao.delete(new RequiredTest(userId, testId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Long> getListOfChosenAnswersIdsFromUser(List<CompleteTestDTO> completeTests, Long questionId) {
        return completeTests.stream()
                .filter(test -> test.getQuestionId().equals(questionId))
                .flatMap(test -> Arrays.stream(test.getAnswers().clone()))
                .filter(CompleteTestDTO.Answer::isChecked)
                .map(CompleteTestDTO.Answer::getAnswerId)
                .collect(Collectors.toList());
    }

    private List<Long> getListOfCorrectAnswersIdsForQuestion(TestDTO testDTO, Long questionId) {
        return testDTO.getAnswers()
                .stream()
                .filter(answer -> answer.getQuestionId().equals(questionId))
                .filter(Answer::isCorrect)
                .map(Answer::getId)
                .collect(Collectors.toList());
    }

    private boolean areAnswersMatch(List<Long> correctAnswersForParticularQuestion, List<Long> chosenAnswersFromUser) {
        return correctAnswersForParticularQuestion.size() == chosenAnswersFromUser.size() &&
                correctAnswersForParticularQuestion.containsAll(chosenAnswersFromUser);
    }


    public Double getAverageGradeOfPassedTests(Long userId) {
        try (ResultDao resultDao = daoFactory.createResultFactory()) {
            AtomicReference<Double> average = new AtomicReference<>(0.0);
            List<Result> results = resultDao.findAllByUserId(userId);
            if (results.size() == 0) {
                return 0.0;
            }
            results.forEach(result -> average.updateAndGet(v -> v + result.getScore()));
            return average.get() / results.size();
        }
    }

    public List<Result> getResults(Long userId) {
        try (ResultDao resultDao = daoFactory.createResultFactory()) {
            return resultDao.findAllByUserId(userId);
        }
    }


    public List<Result> getResultsByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage) {
        try (ResultDao resultDao = daoFactory.createResultFactory()) {
            return resultDao.findAllByUserIdSortedByAndPaginated(id, parameter, page, perPage);
        }
    }

    public Integer getPassedTestsCount(Long userId) {
        try (ResultDao resultDao = daoFactory.createResultFactory()) {
            return resultDao.findAllByUserId(userId).size();
        }
    }

    public static ResultService getInstance() {
        if (resultService == null) {
            synchronized (TestService.class) {
                if (resultService == null) {
                    resultService = new ResultService();
                }
            }
        }
        return resultService;
    }

}
