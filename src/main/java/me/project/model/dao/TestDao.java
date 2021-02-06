package me.project.model.dao;

import me.project.model.entity.Test;
import me.project.model.entity.User;

import java.util.List;

public interface TestDao extends Dao<Test> {
    List<Test> findAllRequiredTestsByUserId(Long id);

    boolean setTestToRequired(User user, Test test);
    List<Test> findAllSortedByAndPaginated(String parameter, int page, int perPage);

    List<Test> findAllByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage);

    
}
