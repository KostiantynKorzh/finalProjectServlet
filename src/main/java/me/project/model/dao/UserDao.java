package me.project.model.dao;

public interface UserDao<T> extends Dao {
    T findByLoginAndPassword(String login,String password);
}
