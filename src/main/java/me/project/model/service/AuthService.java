package me.project.model.service;

import me.project.model.dao.RoleDao;
import me.project.model.dao.UserDao;
import me.project.model.dao.factory.DaoFactory;
import me.project.model.dto.UserDTO;
import me.project.model.entity.enums.Role;
import me.project.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthService {

    UserDTO userDTO;
    DaoFactory daoFactory = DaoFactory.getInstance();

    public AuthService() {
    }

    public boolean login(String login, String password, HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user;
        try (UserDao userDao = daoFactory.createUserFactory()) {
            user = userDao.findByLoginAndPassword(login, password);
        }
        if (user != null) {
            Role role = Role.USER;
            try (RoleDao roleDao = daoFactory.createRoleFactory()) {
                role = roleDao.findByUser(user);
            }
            user.setRole(role);
            userDTO = new UserDTO(user);
            session.setAttribute("user", userDTO);
            return true;
        }
        return false;
    }

    public void signup(String firstName, String lastName, String login, String password) throws Exception {
        try (UserDao userDao = daoFactory.createUserFactory()) {
            User user = new User.Builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .login(login)
                    .password(password)
                    .role(Role.USER)
                    .build();
            userDao.create(user);
        } catch (Exception e) {
//            LOGGER.error("Can't create user in db");
            throw new Exception(e);
        }
    }

}
