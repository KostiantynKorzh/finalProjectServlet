package me.project.model.dao;

import me.project.model.dto.ResultDTO;

import java.util.List;

public interface ResultDao extends Dao<ResultDTO> {
    List<ResultDTO> findAllResultsByUserId(Long id);
}
