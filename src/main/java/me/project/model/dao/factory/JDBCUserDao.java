package me.project.model.dao.factory;

import me.project.model.dao.UserDao;
import me.project.model.entity.enums.Role;
import me.project.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        String query = "INSERT INTO users (first_name, last_name, email, password) values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getLogin());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // create method for user creation from result set
    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM users WHERE id=" + id + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            User user = null;
            while (resultSet.next()) {
                user = new User.Builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .login(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(Role.USER)
                        .build();
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            User user;
            while (resultSet.next()) {
                user = new User.Builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .login(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(Role.USER)
                        .build();

                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE users SET first_name=?, last_name=? WHERE id=?;";

        try (PreparedStatement preparedStmt = connection.prepareStatement(query)) {
            preparedStmt.setString(1, entity.getFirstName());
            preparedStmt.setString(2, entity.getLastName());
            preparedStmt.setLong(3, entity.getId());

            preparedStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User entity) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, entity.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {

        User user;

        String findUserByLoginQueryAndPassword = "SELECT * FROM users" +
                " WHERE email='" + login + "' AND password='" + password + "';";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(findUserByLoginQueryAndPassword);
            while (resultSet.next()) {

                Long id = resultSet.getLong(1);
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                user = new User.Builder()
                        .id(id)
                        .firstName(firstName)
                        .lastName(lastName)
                        .login(login)
                        .password(password)
                        .role(Role.USER)
                        .build();

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return null;
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
