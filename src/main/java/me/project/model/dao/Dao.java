package me.project.model.dao;

import java.util.List;
import java.util.Set;

public interface Dao<T> extends AutoCloseable {
    void create(T entity);
    T findById(Long id);
    List<T> findAll();
    List<T> findAllSortedBy(String parameter);
    void update(T entity);
    void delete(T entity);
    void close();
}
