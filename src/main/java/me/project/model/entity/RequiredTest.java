package me.project.model.entity;

public class RequiredTest {

    private Long id;
    private User user;
    private Test test;

    public RequiredTest() {
    }

    public RequiredTest(User user, Test test) {
        this.user = user;
        this.test = test;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
