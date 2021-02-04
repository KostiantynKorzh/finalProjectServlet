package me.project.model.dto;

import me.project.model.entity.enums.Role;
import me.project.model.entity.User;

public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(User user) {
        id = user.getId();
        login = user.getLogin();
        password = user.getPassword();
        role = user.getRole();
    }

    public UserDTO(Long id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserDTO(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public static class Builder {

        UserDTO userDTO;

        public Builder() {
            userDTO = new UserDTO();
        }

        public Builder id(Long id) {
            userDTO.setId(id);
            return this;
        }

        public Builder login(String login) {
            userDTO.setLogin(login);
            return this;
        }

        public Builder password(String password) {
            userDTO.setPassword(password);
            return this;
        }

        public Builder role(Role role) {
            userDTO.setRole(role);
            return this;
        }

        public UserDTO build() {
            return userDTO;
        }

    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
