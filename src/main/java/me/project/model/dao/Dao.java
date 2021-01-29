package me.project.model.dao;

import java.util.Set;

public interface Dao<T> extends AutoCloseable {
    void create(T entity);
    T findById(Long id);
    Set<T> findAll();
    void update(T entity);
    void delete(T entity);
    void close();
}
