package me.project.model.dao.factory;

import me.project.model.dao.*;
import me.project.model.util.DBConnection;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = DBConnection.initConnection();

    @Override
    public UserDao createUserFactory() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public TestDao createTestFactory() {
        return new JDBCTestDao(getConnection());
    }

    @Override
    public RoleDao createRoleFactory() {
        return new JDBCRoleDao(getConnection());
    }

    @Override
    public ResultDao createResultFactory() {
        return new JDBCResultDao(getConnection());
    }

    @Override
    public RequiredTestDao createRequiredTestFactory() {
        return new JDBCRequiredTestDao(getConnection());
    }

    @Override
    public AnswerDao createAnswerFactory() {
        return new JDBCAnswerDao(getConnection());
    }

    @Override
    public QuestionDao createQuestionFactory() {
        return new JDBCQuestionDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
