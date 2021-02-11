package me.project.model.dao;


import me.project.model.entity.Result;

import java.util.List;

public interface ResultDao extends Dao<Result> {
    List<Result> findAllByUserId(Long id);
    List<Result> findAllByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage);
}
