package org.application.dao.interfaces;

import org.application.entities.*;

import java.util.List;

public interface UserDAO extends DAO<User> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);

    List<User> findByRole(Role role);

    List<User> findByStatus(UserStatus status);

    List<User> findBySpecialization(Specialization specialization);
}
