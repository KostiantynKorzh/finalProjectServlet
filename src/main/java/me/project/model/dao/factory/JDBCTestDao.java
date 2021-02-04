package me.project.model.dao.factory;

import me.project.model.dao.TestDao;
import me.project.model.entity.Test;
import me.project.model.entity.User;
import me.project.model.entity.enums.Difficulty;
import me.project.model.entity.enums.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTestDao implements TestDao {

    private Connection connection;

    public JDBCTestDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Test entity) {

    }

    @Override
    public boolean setTestToRequired(User user, Test test) {
        return false;
    }

    @Override
    public Test findById(Long id) {
        String query = "SELECT * FROM tests WHERE id = ?";
        Test test = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                test = new Test.Builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .subject(Subject.valueOf(resultSet.getString("subject")))
                        .difficulty(Difficulty.values()[resultSet.getInt("difficulty")])
                        .duration(resultSet.getInt("duration"))
                        .created(resultSet.getTimestamp("created"))
                        .build();
            }
            return test;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Test> findAll() {
        List<Test> tests = new ArrayList<>();

        String query = "SELECT * FROM tests";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            Test test;
            while (resultSet.next()) {
                test = new Test.Builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .subject(Subject.valueOf(resultSet.getString("subject")))
                        .difficulty(Difficulty.values()[resultSet.getInt("difficulty")])
                        .duration(resultSet.getInt("duration"))
                        .created(resultSet.getTimestamp("created"))
                        .build();
                tests.add(test);
            }
            return tests;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Test entity) {

    }

    @Override
    public void delete(Test entity) {
    }

    @Override
    public List<Test> findAllSortedBy(String parameter) {
        return null;
    }
    @Override
    public List<Test> findAllRequiredTestsByUserId(Long id) {
        List<Test> requiredTests = new ArrayList<>();

        String query = "SELECT test_id FROM required_tests WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Test test = findById(resultSet.getLong(1));
                requiredTests.add(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requiredTests;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
