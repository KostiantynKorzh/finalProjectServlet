package me.project.model.dao.factory;

import me.project.model.dao.UserDao;
import me.project.model.dto.UserDTO;
import me.project.model.entity.Role;
import me.project.model.entity.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class JDBCUserDao<User> implements UserDao<User> {

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public Set findAll() {

        return null;
    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public User findByLoginAndPassword(String login, String password) {

        User user;

        String findUserByLoginQuery = "SELECT * FROM users" +
                " WHERE email='" + login + "';";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(findUserByLoginQuery);
            while (resultSet.next()) {
                String passwordFromDB = resultSet.getString("password");
                if (password.equals(passwordFromDB)) {

                    String firstName= resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");

                    int userId = resultSet.getInt("id");
                    String findRoleByUserId = "SELECT role_id FROM user_roles WHERE user_id='" + userId + "';";
                    ResultSet resultSetRole = statement.executeQuery(findRoleByUserId);
                    resultSetRole.next();
                    int roleInt = resultSetRole.getInt(1);
                    Role role = Role.USER;
                    if (roleInt == 3) {
                        role = Role.ADMIN;
                    }

                    user = (User) new me.project.model.entity.User.Builder()
                            .firstName(firstName)
                            .lastName(lastName)
                            .login(login)
                            .password(password)
                            .role(role)
                            .build();

                    System.out.println("User from db = " + user);

//                    session.setAttribute("user", userDTO);

                    return user;
                }
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
