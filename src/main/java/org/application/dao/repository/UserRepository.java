package org.application.dao.repository;

import org.application.models.Role;
import org.application.models.Specialization;
import org.application.models.User;
import org.application.models.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);

    List<User> findByRole(Role role);

    List<User> findByUserStatus(UserStatus status);

    List<User> findBySpecialization(Specialization specialization);
}
