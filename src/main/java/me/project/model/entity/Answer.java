package me.project.model.entity;

public class Answer {

    private Long id;
    private Long questionId;
    private String answerText;
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(Long id, Long questionId, String answerText, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public static class Builder {

        Answer answer;

        public Builder() {
            answer = new Answer();
        }

        public Builder id(Long id) {
            answer.setId(id);
            return this;
        }

        public Builder questionId(Long questionId) {
            answer.setQuestionId(questionId);
            return this;
        }

        public Builder answerText(String text) {
            answer.setAnswerText(text);
            return this;
        }

        public Builder isCorrect(boolean isCorrect) {
            answer.setCorrect(isCorrect);
            return this;
        }

        public Answer build() {
            return answer;
        }

    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answer_text='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
