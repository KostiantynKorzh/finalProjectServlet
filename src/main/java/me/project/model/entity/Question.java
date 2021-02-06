package me.project.model.entity;

public class Question {

    private Long id;
    private Long testId;
    private String questionText;

    public Question() {
    }

    public Question(Long id, Long testId, String questionText) {
        this.id = id;
        this.testId = testId;
        this.questionText = questionText;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public static class Builder {

        Question question;

        public Builder() {
            question = new Question();
        }

        public Builder id(Long id) {
            question.setId(id);
            return this;
        }

        public Builder testId(Long testId) {
            question.setTestId(testId);
            return this;
        }

        public Builder questionText(String text) {
            question.setQuestionText(text);
            return this;
        }

        public Question build() {
            return question;
        }

    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", test_id=" + testId +
                ", questionText='" + questionText + '\'' +
                '}';
    }
}
