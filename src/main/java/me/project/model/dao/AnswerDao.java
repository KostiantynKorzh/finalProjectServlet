package me.project.model.dao;

import me.project.model.entity.Answer;
import me.project.model.entity.Question;

import java.util.List;

public interface AnswerDao extends Dao<Answer>{
    List<Answer> findAllByTestId(Long testId);
}
