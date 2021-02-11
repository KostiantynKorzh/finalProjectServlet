package me.project.model.entity;

public class RequiredTest {

    private Long id;
    private Long userId;
    private Long testId;

    public RequiredTest() {
    }

    public RequiredTest(Long userId, Long testId) {
        this.userId = userId;
        this.testId = testId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
