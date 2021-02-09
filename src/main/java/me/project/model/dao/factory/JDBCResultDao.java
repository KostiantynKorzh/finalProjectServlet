package me.project.model.dao.factory;

import me.project.model.dao.ResultDao;
import me.project.model.dto.ResultDTO;
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
    public List<ResultDTO> findAllSortedBy(String parameter) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<ResultDTO> findAllByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage) {
        String query = "SELECT tests.id, tests.title, tests.subject, " +
                "tests.difficulty, tests.duration, results.score, results.pass_timestamp " +
                "FROM results INNER JOIN tests " +
                "ON results.test_id = tests.id " +
                "WHERE results.user_id = ? " +
                "ORDER BY " + (parameter.equals("result") ? ("results.score") : ("tests." + parameter)) +
                " LIMIT " + perPage +
                " OFFSET " + page * perPage;
        List<ResultDTO> results = new ArrayList<>();
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
                ResultDTO result = new ResultDTO.Builder()
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
    public List<ResultDTO> findAllByUserId(Long id) {
//        String query = "SELECT test_id FROM results WHERE user_id= ?";
        String query = "SELECT tests.id, tests.title, tests.subject, " +
                "tests.difficulty, tests.duration, results.score, results.pass_timestamp " +
                "FROM results INNER JOIN tests " +
                "ON results.test_id = tests.id " +
                "WHERE results.user_id = ?";
        List<ResultDTO> results = new ArrayList<>();
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
                ResultDTO result = new ResultDTO.Builder()
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
    public void create(ResultDTO entity) {
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
    public ResultDTO findById(Long id) {
        return null;
    }

    @Override
    public List<ResultDTO> findAll() {
        return null;
    }

    @Override
    public void update(ResultDTO entity) {

    }

    @Override
    public void delete(ResultDTO entity) {

    }

    @Override
    public void close() {

    }
}
