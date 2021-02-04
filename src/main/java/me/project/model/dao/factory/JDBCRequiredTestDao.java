package me.project.model.dao.factory;

import me.project.model.dao.RequiredTestDao;
import me.project.model.entity.RequiredTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class JDBCRequiredTestDao implements RequiredTestDao {

    private Connection connection;

    public JDBCRequiredTestDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(RequiredTest entity) {
        String query = "INSERT INTO required_tests(user_id, test_id) values(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, entity.getUser().getId());
            preparedStatement.setLong(2, entity.getTest().getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RequiredTest findById(Long id) {
        return null;
    }

    @Override
    public List<RequiredTest> findAll() {
        return null;
    }

    @Override
    public void update(RequiredTest entity) {

    }

    @Override
    public void delete(RequiredTest entity) {
        String query = "DELETE FROM required_tests WHERE user_id = ? AND test_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, entity.getUser().getId());
            preparedStatement.setLong(2, entity.getTest().getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }
}
