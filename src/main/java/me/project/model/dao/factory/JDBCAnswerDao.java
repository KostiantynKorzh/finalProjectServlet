package me.project.model.dao.factory;

import me.project.model.dao.AnswerDao;
import me.project.model.entity.Answer;
import me.project.model.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerDao implements AnswerDao {

    private Connection connection;

    public JDBCAnswerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Answer entity) {

    }

    @Override
    public Answer findById(Long id) {
        return null;
    }

    @Override
    public List<Answer> findAllByTestId(Long testId) {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * " +
                "FROM answers " +
                "RIGHT JOIN questions " +
                "ON answers.question_id=questions.id " +
                "WHERE questions.test_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, testId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer answer = new Answer.Builder()
                        .id(resultSet.getLong("id"))
                        .questionId(resultSet.getLong("question_id"))
                        .answerText(resultSet.getString("answer_text"))
                        .isCorrect(resultSet.getBoolean("is_correct"))
                        .build();
                answers.add(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public List<Answer> findAll() {
        return null;
    }

    @Override
    public List<Answer> findAllSortedBy(String parameter) {
        return null;
    }

    @Override
    public void update(Answer entity) {

    }

    @Override
    public void delete(Answer entity) {

    }

    @Override
    public void close() {

    }

    @Override
    public void deleteById(Long id) {

    }
}
