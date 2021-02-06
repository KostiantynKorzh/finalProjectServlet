package me.project.model.dto;

import me.project.model.entity.Answer;
import me.project.model.entity.Question;
import me.project.model.entity.Test;

import java.util.List;

public class TestDTO {

    private Test test;
    private List<Question> questions;
    private List<Answer> answers;

    public TestDTO() {
    }

    public TestDTO(Test test, List<Question> questions, List<Answer> answers) {
        this.test = test;
        this.questions = questions;
        this.answers = answers;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
