package me.project.model.service;

import me.project.model.dao.RequiredTestDao;
import me.project.model.dao.ResultDao;
import me.project.model.dao.TestDao;
import me.project.model.dao.UserDao;
import me.project.model.dao.factory.DaoFactory;
import me.project.model.dto.ResultDTO;
import me.project.model.entity.RequiredTest;
import me.project.model.entity.Test;
import me.project.model.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class TestService {

    private static TestService testService;


    TestDao testDao = DaoFactory.getInstance().createTestFactory();
    ResultDao resultDao = DaoFactory.getInstance().createResultFactory();
    UserDao userDao = DaoFactory.getInstance().createUserFactory();
    RequiredTestDao requiredTestDao = DaoFactory.getInstance().createRequiredTestFactory();


    private TestService() {
    }

    public void passTest(Long userId, Long testId) {
        User user = userDao.findById(userId);
        Test test = testDao.findById(testId);
        Random rand = new Random();
        resultDao.create(
                new ResultDTO.Builder()
                        .userId(userId)
                        .test(test)
                        .score(rand.nextInt(100))
                        .passTimestamp(new Timestamp(System.currentTimeMillis()))
                        .build());
        resultDao.close();
        requiredTestDao.delete(new RequiredTest(user, test));
        requiredTestDao.close();
    }

    public void removeFromRequired(Long userId, Long testId) {
        User user = userDao.findById(userId);
        Test test = testDao.findById(testId);
        requiredTestDao.delete(new RequiredTest(user, test));
        requiredTestDao.close();
    }

    public void makeTestsRequired(Long userId, Long testId) {
        User user = userDao.findById(userId);
        Test test = testDao.findById(testId);
        requiredTestDao.create(new RequiredTest(user, test));
        requiredTestDao.close();
    }

    public List<Test> getAvailableTests(Long userId) {
        List<Test> allTests = testDao.findAll();
        List<Test> requiredTests = testDao.findAllRequiredTestsByUserId(userId);
        List<Test> passedTests = new ArrayList<>();
        resultDao.findAllResultsByUserId(userId)
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

    public List<Test> getRequiredTests(Long userId) {
        return testDao.findAllRequiredTestsByUserId(userId);
    }

    public Integer getRequiredTestsCount(Long userId) {
        return testDao.findAllRequiredTestsByUserId(userId).size();
    }

    public List<ResultDTO> getResults(Long userId) {
        return resultDao.findAllResultsByUserId(userId);
    }

    public Double getAverageGradeOfPassedTests(Long userId) {
        AtomicReference<Double> average = new AtomicReference<>(0.0);
        List<ResultDTO> results = resultDao.findAllResultsByUserId(userId);
        if (results.size() == 0) {
            return 0.0;
        }
        results.forEach(result -> average.updateAndGet(v -> v + result.getScore()));
        return average.get() / results.size();
    }

    public Integer getPassedTestsCount(Long userId) {
        return resultDao.findAllResultsByUserId(userId).size();
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
