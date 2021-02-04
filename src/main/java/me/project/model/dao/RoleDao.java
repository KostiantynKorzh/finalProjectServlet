package me.project.model.dao;

import me.project.model.entity.enums.Role;
import me.project.model.entity.User;

public interface RoleDao extends Dao<Role> {
    Role findByUser(User user);
}
