package me.project.model.dao;

import me.project.model.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    User findByLoginAndPassword(String login, String password);
    List<User> findAllSortedByAndPaginated(String parameter, int page, int perPage);

}
