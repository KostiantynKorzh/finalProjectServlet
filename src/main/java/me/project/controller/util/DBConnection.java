package me.project.controller.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection initConnection() throws ClassNotFoundException, SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";

        String dbName = "testing_app_db";
        String dbUsername = "root";
        String dbPassword = "root1234";

        Class.forName(dbDriver);

        return DriverManager.getConnection(dbURL + dbName,
               dbUsername, dbPassword);
    }

}
    