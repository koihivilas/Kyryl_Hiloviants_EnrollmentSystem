package org.application.dao.repository;

import org.application.models.UserSubject;
import org.application.models.UserSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubjectRepository extends JpaRepository<UserSubject, UserSubjectId> {
    List<UserSubject> findByUserId(long id);
}
