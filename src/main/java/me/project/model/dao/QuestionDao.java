package me.project.model.dao;

import me.project.model.entity.Question;

import java.util.List;

public interface QuestionDao extends Dao<Question> {
    List<Question> findAllByTestId(Long testId);
}
