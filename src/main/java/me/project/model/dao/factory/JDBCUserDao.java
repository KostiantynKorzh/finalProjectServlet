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
    public void create(User entity) throws Exception {

        String queryUser = "INSERT INTO users (first_name, last_name, email, password, created) values (?, ?, ?, ?, NOW());";
        String queryRole = "INSERT INTO user_roles (user_id, role_id) values ((select MAX(id) FROM users), ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryUser)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getLogin());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryRole)) {
            preparedStatement.setInt(1, entity.getRole().ordinal() + 1);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
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
    public List<User> findAllSortedByAndPaginated(String parameter, int page, int perPage) {
        List<User> sortedAndPaginatedUsers = new ArrayList<>();
        String query = "SELECT * " +
                "FROM users " +
                "LEFT JOIN user_roles " +
                "ON users.id=user_roles.user_id " +
                "LEFT JOIN roles " +
                "ON user_roles.role_id=roles.id " +
                "WHERE roles.id=1 " +
                "ORDER BY users." + parameter + " " +
                "LIMIT " + perPage + " " +
                "OFFSET " + page * perPage;
        try (Statement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            User user;
            while (resultSet.next()) {
                user = new User.Builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .login(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(Role.valueOf(resultSet.getString("name").replace("ROLE_", "")))
                        .build();

                sortedAndPaginatedUsers.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedAndPaginatedUsers;
    }

    @Override
    public List<User> findAllSortedBy(String parameter) {
        List<User> sortedUsers = new ArrayList<>();
        String query = "SELECT * " +
                "FROM users " +
                "LEFT JOIN user_roles " +
                "ON users.id=user_roles.user_id " +
                "LEFT JOIN roles " +
                "ON user_roles.role_id=roles.id " +
                "WHERE roles.id=1 " +
                "ORDER BY users." + parameter;
        try (Statement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            User user;
            while (resultSet.next()) {
                user = new User.Builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .login(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(Role.valueOf(resultSet.getString("name").replace("ROLE_", "")))
                        .build();

                sortedUsers.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedUsers;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String query = "SELECT * " +
                "FROM users " +
                "LEFT JOIN user_roles " +
                "ON users.id=user_roles.user_id " +
                "LEFT JOIN roles " +
                "ON user_roles.role_id=roles.id " +
                "WHERE roles.id=1";
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
                        .role(Role.valueOf(resultSet.getString("name").replace("ROLE_", "")))
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
        String queryResult = "ALTER TABLE `testing_app_db`.`results` DROP FOREIGN KEY (`user_id`);" +
                "ALTER TABLE results ADD CONSTRAINT user_id_foreign FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;";
        String queryUser = "DELETE FROM users WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryResult)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryUser)) {
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
