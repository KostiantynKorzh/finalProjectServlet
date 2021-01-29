package me.project.model.dao.factory;

import me.project.model.dao.TestDao;
import me.project.model.dao.UserDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserFactory();

    public abstract TestDao createTestFactory();


    // ------ ABSTRACT FACTORY ------
    public static DaoFactory getInstance() throws NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }


}
