package me.project.model.entity;

public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Role role = Role.USER;

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String login, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static class Builder {

        private User user;

        public Builder() {
            user = new User();
        }

        public Builder firstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public Builder login(String login) {
            user.setLogin(login);
            return this;
        }

        public Builder password(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder role(Role role) {
            user.setRole(role);
            return this;
        }

        public User build() {
            return user;
        }

    }

}
