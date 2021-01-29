package me.project.model.dao.factory;

import me.project.model.dao.TestDao;
import me.project.model.dao.UserDao;
import me.project.model.util.DBConnection;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = DBConnection.initConnection();

    public JDBCDaoFactory() throws NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
    }

    @Override
    public UserDao createUserFactory() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public TestDao createTestFactory() {
        return new JDBCTestDao();
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
