package me.project.model.entity.enums;

public enum Role {
    USER, MODERATOR, ADMIN;

    public String getName() {
        return name();
    }
}