package org.application.dao.interfaces;

import org.application.entities.UserStatus;

public interface UserStatusDAO extends DAO<UserStatus> {
    UserStatus findByCode(String code);
}
