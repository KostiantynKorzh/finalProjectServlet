package me.project.model.entity;

import me.project.model.entity.enums.Difficulty;
import me.project.model.entity.enums.Subject;

import java.sql.Timestamp;
import java.util.Objects;

public class Test {

    private Long id;
    private String title;
    private Subject subject;
    private Difficulty difficulty;
    private Integer duration;
    private Timestamp created;

    public Test() {
    }

    public Test(Long id, String title, Subject subject, Difficulty difficulty, Integer duration, Timestamp created) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.difficulty = difficulty;
        this.duration = duration;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public static class Builder {
        Test test;

        public Builder() {
            test = new Test();
        }

        public Builder id(Long id) {
            test.setId(id);
            return this;
        }

        public Builder title(String title) {
            test.setTitle(title);
            return this;
        }

        public Builder subject(Subject subject) {
            test.setSubject(subject);
            return this;
        }

        public Builder difficulty(Difficulty difficulty) {
            test.setDifficulty(difficulty);
            return this;
        }

        public Builder duration(Integer duration) {
            test.setDuration(duration);
            return this;
        }

        public Builder created(Timestamp created) {
            test.setCreated(created);
            return this;
        }

        public Test build() {
            return test;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subject, difficulty, duration, created);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subject=" + subject +
                ", difficulty=" + difficulty +
                ", duration=" + duration +
                ", created=" + created +
                '}';
    }
}
