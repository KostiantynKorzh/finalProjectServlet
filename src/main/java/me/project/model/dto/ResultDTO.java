package me.project.model.dto;

import me.project.model.entity.Test;

import java.sql.Timestamp;

public class ResultDTO {

    private Test test;
    private Long userId;
    private Integer score;
    private Timestamp passTimestamp;

    public ResultDTO() {
    }


    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Timestamp getPassTimestamp() {
        return passTimestamp;
    }

    public void setPassTimestamp(Timestamp passTimestamp) {
        this.passTimestamp = passTimestamp;
    }


    public static class Builder {

        ResultDTO result;

        public Builder() {
            result = new ResultDTO();
        }

        public Builder userId(Long id) {
            result.setUserId(id);
            return this;
        }

        public Builder test(Test test) {
            result.setTest(test);
            return this;
        }

        public Builder score(Integer score) {
            result.setScore(score);
            return this;
        }

        public Builder passTimestamp(Timestamp passTimestamp) {
            result.setPassTimestamp(passTimestamp);
            return this;
        }

        public ResultDTO build() {
            return result;
        }

    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "test=" + test +
                ", userId=" + userId +
                ", score=" + score +
                ", passTimestamp=" + passTimestamp +
                '}';
    }
}
