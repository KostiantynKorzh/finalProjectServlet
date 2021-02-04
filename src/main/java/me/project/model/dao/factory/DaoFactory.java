package me.project.model.dao.factory;

import me.project.model.dao.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserFactory();

    public abstract TestDao createTestFactory();

    public abstract RoleDao createRoleFactory();

    public abstract ResultDao createResultFactory();

    public abstract RequiredTestDao createRequiredTestFactory();


    // ------ ABSTRACT FACTORY ------ //
    public static DaoFactory getInstance(){
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
