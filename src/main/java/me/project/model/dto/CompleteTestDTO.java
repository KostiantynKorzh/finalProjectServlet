package me.project.model.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompleteTestDTO {

    private Long questionId;
    private Answer[] answers;

    public CompleteTestDTO() {
    }

    public CompleteTestDTO(Long questionId, Answer[] answers) {
        this.questionId = questionId;
        this.answers = answers;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public static class Answer {
        Long answerId;
        boolean isChecked;

        public Answer() {
        }

        public Answer(Long answerId, boolean isChecked) {
            this.answerId = answerId;
            this.isChecked = isChecked;
        }


        public Long getAnswerId() {
            return answerId;
        }

        public void setAnswerId(Long answerId) {
            this.answerId = answerId;
        }

        @JsonGetter("isChecked")
        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }

        @Override
        public String toString() {
            return "Answer{" +
                    "answerId=" + answerId +
                    ", isChecked=" + isChecked +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CompleteTestDTO{" +
                "questionId=" + questionId +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
