package org.application.dao.interfaces;

import org.application.models.Role;
import org.application.models.Specialization;
import org.application.models.User;
import org.application.models.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserDAO extends DAO<User> {
    Page<User> getAll(Pageable pageable);

    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);

    List<User> findByRole(Role role);

    List<User> findByStatus(UserStatus status);

    List<User> findBySpecialization(Specialization specialization);
}
