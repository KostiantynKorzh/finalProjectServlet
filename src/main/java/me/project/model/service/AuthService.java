package me.project.model.service;

import me.project.model.dao.UserDao;
import me.project.model.dao.factory.DaoFactory;
import me.project.model.dao.factory.JDBCUserDao;
import me.project.model.dto.UserDTO;
import me.project.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class AuthService {

    UserDTO userDTO;
    DaoFactory daoFactory = DaoFactory.getInstance();

    public AuthService() throws NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
    }


    public boolean login(String login, String password, HttpServletRequest req) {

        HttpSession session = req.getSession();

        UserDao<User> userDao = daoFactory.createUserFactory();

        User user = userDao.findByLoginAndPassword(login, password);

        System.out.println(user);

        if (user != null) {
            userDTO = new UserDTO(user);
            session.setAttribute("user", userDTO);
            return true;
        }

        return false;
    }

}
