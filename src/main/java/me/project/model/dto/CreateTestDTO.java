package me.project.model.dto;

import java.util.Arrays;

public class CreateTestDTO {

    private String testTitle;
    private String testSubject;
    private String testDifficulty;
    private String questionText;
    private Integer testDuration;
    private Answer[] answers;

    public CreateTestDTO() {
    }


    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getTestSubject() {
        return testSubject;
    }

    public void setTestSubject(String testSubject) {
        this.testSubject = testSubject;
    }

    public String getTestDifficulty() {
        return testDifficulty;
    }

    public void setTestDifficulty(String testDifficulty) {
        this.testDifficulty = testDifficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(Integer testDuration) {
        this.testDuration = testDuration;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public static class Answer {
        private String answerText;
        private boolean isCorrect;

        public Answer() {
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

        public void setIsCorrect(boolean correct) {
            isCorrect = correct;
        }

        @Override
        public String toString() {
            return "Answer{" +
                    "answerText='" + answerText + '\'' +
                    ", isCorrect=" + isCorrect +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CreateTestDTO{" +
                "testTitle='" + testTitle + '\'' +
                ", testSubject='" + testSubject + '\'' +
                ", testDifficulty='" + testDifficulty + '\'' +
                ", questionText='" + questionText + '\'' +
                ", testDuration=" + testDuration +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
