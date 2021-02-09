package me.project.model.dao.factory;

import me.project.model.dao.ResultDao;
import me.project.model.entity.Result;
import me.project.model.entity.Test;
import me.project.model.entity.enums.Difficulty;
import me.project.model.entity.enums.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCResultDao implements ResultDao {

    private Connection connection;

    public JDBCResultDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Result> findAllSortedBy(String parameter) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Result> findAllByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage) {
        String query = "SELECT tests.id, tests.title, tests.subject, " +
                "tests.difficulty, tests.duration, results.score, results.pass_timestamp " +
                "FROM results INNER JOIN tests " +
                "ON results.test_id = tests.id " +
                "WHERE results.user_id = ? " +
                "ORDER BY " + (parameter.equals("result") ? ("results.score") : ("tests." + parameter)) +
                " LIMIT " + perPage +
                " OFFSET " + page * perPage;
        List<Result> results = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Test test = new Test.Builder()
                        .id(resultSet.getLong("tests.id"))
                        .title(resultSet.getString("tests.title"))
                        .subject(Subject.valueOf(resultSet.getString("tests.subject")))
                        .difficulty(Difficulty.values()[resultSet.getInt("tests.difficulty")])
                        .duration(resultSet.getInt("tests.duration"))
                        .build();
                Result result = new Result.Builder()
                        .test(test)
                        .userId(id)
                        .score(resultSet.getInt("results.score"))
                        .passTimestamp(resultSet.getTimestamp("results.pass_timestamp"))
                        .build();

                results.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Result> findAllByUserId(Long id) {
//        String query = "SELECT test_id FROM results WHERE user_id= ?";
        String query = "SELECT tests.id, tests.title, tests.subject, " +
                "tests.difficulty, tests.duration, results.score, results.pass_timestamp " +
                "FROM results INNER JOIN tests " +
                "ON results.test_id = tests.id " +
                "WHERE results.user_id = ?";
        List<Result> results = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Test test = new Test.Builder()
                        .id(resultSet.getLong("tests.id"))
                        .title(resultSet.getString("tests.title"))
                        .subject(Subject.valueOf(resultSet.getString("tests.subject")))
                        .difficulty(Difficulty.values()[resultSet.getInt("tests.difficulty")])
                        .duration(resultSet.getInt("tests.duration"))
                        .build();
                Result result = new Result.Builder()
                        .test(test)
                        .userId(id)
                        .score(resultSet.getInt("results.score"))
                        .passTimestamp(resultSet.getTimestamp("results.pass_timestamp"))
                        .build();

                results.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void create(Result entity) {
        String query = "INSERT INTO results(user_id, test_id, score, pass_timestamp) " +
                "values(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, entity.getUserId());
            preparedStatement.setLong(2, entity.getTest().getId());
            preparedStatement.setInt(3, entity.getScore());
            preparedStatement.setTimestamp(4, entity.getPassTimestamp());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Result findById(Long id) {
        return null;
    }

    @Override
    public List<Result> findAll() {
        return null;
    }

    @Override
    public void update(Result entity) {

    }

    @Override
    public void delete(Result entity) {

    }

    @Override
    public void close() {

    }
}
