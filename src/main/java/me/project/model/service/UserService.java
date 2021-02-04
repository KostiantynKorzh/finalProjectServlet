package me.project.model.service;

import me.project.model.dao.RoleDao;
import me.project.model.dao.UserDao;
import me.project.model.dao.factory.DaoFactory;
import me.project.model.entity.User;
import me.project.model.entity.enums.Role;

import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void deleteUser(Long id){
        try(UserDao userDao = daoFactory.createUserFactory()){
            userDao.deleteById(id);
        }
    }

    public void deleteUser(User user){
        try(UserDao userDao = daoFactory.createUserFactory()){
            userDao.delete(user);
        }
    }

    public void updateUser(User user) {
        try (UserDao userDao = daoFactory.createUserFactory()) {
            userDao.update(user);
        }
    }

    public User getUserById(Long id) {
        try (UserDao userDao = daoFactory.createUserFactory()) {
            return userDao.findById(id);
        }
    }

//    public List<User> getUsersBy

    public List<User> getUsers() {
        try (UserDao userDao = daoFactory.createUserFactory()) {
            List<User> users = userDao.findAll();
            Role role;
            try (RoleDao roleDao = daoFactory.createRoleFactory()) {
                for (User user : users) {
                    role = roleDao.findByUser(user);
                    user.setRole(role);
                }
            }
            return users;
        }
    }

}
