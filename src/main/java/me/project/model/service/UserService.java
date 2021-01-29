package me.project.model.service;

import me.project.model.entity.User;
import me.project.controller.util.DBConnection;

import java.sql.Connection;

public class UserService {

    Connection con;

    public void addUser(User user){

        con = DBConnection

        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
    }

}
