package org.application.dao.repository;

import org.application.models.SpecializationSubject;
import org.application.models.SpecializationSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationSubjectRepository extends JpaRepository<SpecializationSubject, SpecializationSubjectId> {
    List<SpecializationSubject> findBySpecializationId(long id);
}
