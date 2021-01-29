package me.project.model.dto;

import me.project.model.entity.Role;
import me.project.model.entity.User;

public class UserDTO {

    private String login;
    private String password;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(User user) {
        login = user.getLogin();
        password = user.getPassword();
        role = user.getRole();
    }

    public UserDTO(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
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

}
