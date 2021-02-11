package me.project.model.dao.factory;

import me.project.model.dao.QuestionDao;
import me.project.model.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCQuestionDao implements QuestionDao {

    private Connection connection;

    public JDBCQuestionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Question entity) {

    }

    @Override
    public Question findById(Long id) {
        return null;
    }

    @Override
    public List<Question> findAllByTestId(Long testId) {
        List<Question> questions = new ArrayList<>();

        String query = "SELECT * " +
                "FROM questions " +
                "WHERE questions.test_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, testId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question.Builder()
                        .id(resultSet.getLong("id"))
                        .testId(resultSet.getLong("test_id"))
                        .questionText(resultSet.getString("question_text"))
                        .build();
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public Long findIdByQuestionText(String questionText) {
        Long id = (long) -1;
        String query = "SELECT id " +
                "FROM questions " +
                "WHERE question_text = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, questionText);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void createQuestions(List<String> questionTexts) {
        String query = "INSERT " +
                "INTO questions(test_id, question_text) " +
                "values((select MAX(id) FROM tests), ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            for (String question : questionTexts) {
                preparedStatement.setString(1, question);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public List<Question> findAllSortedBy(String parameter) {
        return null;
    }

    @Override
    public void update(Question entity) {

    }

    @Override
    public void delete(Question entity) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {

    }
}
