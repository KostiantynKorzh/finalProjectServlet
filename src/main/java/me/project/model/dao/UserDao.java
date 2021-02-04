package me.project.model.dao;

import me.project.model.entity.User;

public interface UserDao extends Dao<User> {
    User findByLoginAndPassword(String login, String password);

    void deleteById(Long id);
}
