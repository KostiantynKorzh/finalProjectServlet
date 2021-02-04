package me.project.model.dao.factory;

import me.project.model.dao.RoleDao;
import me.project.model.entity.enums.Role;
import me.project.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCRoleDao implements RoleDao {


    private Connection connection;

    public JDBCRoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Role entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public Role findByUser(User user) {
        try (Statement statement = connection.createStatement()) {

            String findRoleByUserId = "SELECT role_id FROM user_roles WHERE user_id='" + user.getId() + "';";
            ResultSet resultSetRole = statement.executeQuery(findRoleByUserId);
            Role role = Role.USER;
            while (resultSetRole.next()) {
                int roleInt = resultSetRole.getInt(1);
                if (roleInt == 3) {
                    role = Role.ADMIN;
                }
            }
            return role;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Role.USER;
    }

    @Override
    public List<Role> findAllSortedBy(String parameter) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Role entity) {

    }

    @Override
    public void delete(Role entity) {

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
