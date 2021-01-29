package me.project.model.service;

import me.project.model.dto.UserDTO;
import me.project.model.entity.Role;
import me.project.model.entity.User;
import me.project.model.util.DBConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {

    UserDTO userDTO;
    Connection con;

//    public boolean addUser(User user, HttpServletRequest req) {
//
//        HttpSession session = req.getSession();
//
//        try {
//            con = DBConnection.initConnection();
//            Statement statement = con.createStatement();
//
//
//            String findUserByLoginQuery = "SELECT * FROM users" +
//                    " WHERE email='" + user.getLogin() + "';";
//
//
//            ResultSet resultSet = statement.executeQuery(findUserByLoginQuery);
//
//
////            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//            while (resultSet.next()) {
//                String passwordFromDB = resultSet.getString("password");
//                if (user.getPassword().equals(passwordFromDB)) {
//                    int userId = resultSet.getInt("id");
//                    String findRoleByUserId = "SELECT role_id FROM user_roles WHERE user_id='" + userId + "';";
//                    ResultSet resultSetRole = statement.executeQuery(findRoleByUserId);
//                    resultSetRole.next();
//                    int roleInt = resultSetRole.getInt(1);
//                    Role role = Role.USER;
//                    if (roleInt == 3) {
//                        role = Role.ADMIN;
//                    }
//
//                    userDTO = new UserDTO.Builder()
//                            .login(user.getLogin())
//                            .password(user.getPassword())
//                            .role(role)
//                            .build();
//
//                    session.setAttribute("user", userDTO);
//
//                    resultSet.close();
//                    return true;
//                }
//            }
//            resultSet.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

}
