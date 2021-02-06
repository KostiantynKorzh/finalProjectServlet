package me.project.model.dao;

import me.project.model.dto.ResultDTO;

import java.util.List;

public interface ResultDao extends Dao<ResultDTO> {
    List<ResultDTO> findAllByUserId(Long id);
    List<ResultDTO> findAllByUserIdSortedByAndPaginated(Long id, String parameter, int page, int perPage);
}
