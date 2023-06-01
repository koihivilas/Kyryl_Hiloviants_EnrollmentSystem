package org.application.dao.repository;

import org.application.models.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    UserStatus findByCode(String code);
}
