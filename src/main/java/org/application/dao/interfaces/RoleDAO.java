package org.application.dao.interfaces;

import org.application.entities.Role;

public interface RoleDAO extends DAO<Role> {
    Role findByCode(String code);
}
