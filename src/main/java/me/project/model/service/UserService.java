package me.project.model.service;

import me.project.model.dao.ResultDao;
import me.project.model.dao.TestDao;
import me.project.model.dao.UserDao;
import me.project.model.dao.factory.DaoFactory;
import me.project.model.dto.CompleteTestDTO;
import me.project.model.dto.ResultDTO;
import me.project.model.entity.User;

import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void deleteUser(Long id) {
        try (UserDao userDao = daoFactory.createUserFactory()) {
            userDao.deleteById(id);
        }
    }

    public void deleteUser(User user) {
        try (UserDao userDao = daoFactory.createUserFactory()) {
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


    public List<User> getUsersSortedByAndPaginated(String parameter, int page, int perPage) {
        try (UserDao userDao = daoFactory.createUserFactory()) {
            return userDao.findAllSortedByAndPaginated(parameter, page, perPage);
        }
    }

    public List<User> getUsersSortedBy(String parameter) {
        try (UserDao userDao = daoFactory.createUserFactory()) {
            return userDao.findAllSortedBy(parameter);
        }
    }

    public List<User> getUsers() {
        try (UserDao userDao = daoFactory.createUserFactory()) {
            return userDao.findAll();
        }
    }


}
