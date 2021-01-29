package me.project.model.util;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DataSource dataSource;

    public static DataSource initConnection() throws ClassNotFoundException, SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";

        String dbName = "testing_app_db";
        String dbUsername = "root";
        String dbPassword = "root1234";

        if(dataSource==null) {
            synchronized (DBConnection.class){
                if(dataSource==null){
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(dbURL+dbName);
                    ds.setUsername(dbUsername);
                    ds.setPassword(dbPassword);
                    ds.setDriverClassName(dbDriver);
                    ds.setMinIdle(3);
                    ds.setMaxIdle(15);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }

        return dataSource;
//
//        Class.forName(dbDriver);
//
//        return DriverManager.getConnection(dbURL + dbName,
//               dbUsername, dbPassword);
    }

}
    